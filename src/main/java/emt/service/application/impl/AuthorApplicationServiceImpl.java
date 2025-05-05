package emt.service.application.impl;

import emt.model.domain.Country;
import emt.model.dto.CreateAuthorDto;
import emt.model.dto.UpdateAuthorDto;
import emt.model.views.BooksPerAuthorView;
import emt.repository.BooksPerAuthorViewRepository;
import emt.service.application.AuthorApplicationService;
import emt.service.domain.AuthorService;
import emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService, BooksPerAuthorViewRepository booksPerAuthorViewRepository) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
    }

    @Override
    public List<UpdateAuthorDto> findAll() {
        return UpdateAuthorDto.from(authorService.findAll());
    }

    @Override
    public Optional<UpdateAuthorDto> findById(Long id) {
        return authorService.findById(id).map(UpdateAuthorDto::from);
    }

    @Override
    public Optional<UpdateAuthorDto> save(CreateAuthorDto createAuthorDto) {
        Optional<Country> country=countryService.findById(createAuthorDto.country());

        if (country.isPresent()){
            return authorService.save(createAuthorDto.toAuthor(country.get()))
                    .map(UpdateAuthorDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UpdateAuthorDto> update(Long id, CreateAuthorDto createAuthorDto) {
        Optional<Country> country=countryService.findById(createAuthorDto.country());

        return authorService.update(id,
                createAuthorDto.toAuthor(country.orElse(null)))
                .map(UpdateAuthorDto::from);
    }

    @Override
    public void delete(Long id) {
        authorService.delete(id);
    }

    @Override
    public List<BooksPerAuthorView> findAllBooksPerAuthor() {
        return booksPerAuthorViewRepository.findAll();
    }

    @Override
    public BooksPerAuthorView findBooksPerAuthor(Long id) {
        return booksPerAuthorViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorViewRepository.refreshMaterializedView();
    }
}
