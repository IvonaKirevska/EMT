package emt.service.application;

import emt.model.dto.CreateAuthorDto;
import emt.model.dto.UpdateAuthorDto;
import emt.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    List<UpdateAuthorDto> findAll();
    Optional<UpdateAuthorDto> findById(Long id);
    Optional<UpdateAuthorDto> save(CreateAuthorDto createAuthorDto);
    Optional<UpdateAuthorDto> update(Long id, CreateAuthorDto createAuthorDto);
    void delete(Long id);

    List<BooksPerAuthorView> findAllBooksPerAuthor();
    BooksPerAuthorView findBooksPerAuthor(Long id);
    void refreshMaterializedView();

}
