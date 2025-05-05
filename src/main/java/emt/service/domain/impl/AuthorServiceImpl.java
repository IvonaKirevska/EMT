package emt.service.domain.impl;

import emt.model.domain.Author;
import emt.repository.AuthorRepository;
import emt.service.domain.AuthorService;
import emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(Author author) {
        if (author.getCountry()!=null && author.getName()!=null && author.getSurname()!=null && countryService.findById(author.getCountry().getId()).isPresent()){
            return Optional.of(authorRepository.save(new Author(countryService.findById(author.getCountry().getId()).get(), author.getSurname(), author.getName())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        return authorRepository.findById(id)
                .map(existingAuthor->{
                    if (author.getName()!=null){
                        existingAuthor.setName(author.getName());
                    }
                    if (author.getName()!=null){
                        existingAuthor.setSurname(author.getSurname());
                    }
                    if (author.getCountry()!=null && countryService.findById(author.getCountry().getId()).isPresent()){
                        existingAuthor.setCountry(countryService.findById(author.getCountry().getId()).get());
                    }
                    return authorRepository.save(existingAuthor);
                });
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
