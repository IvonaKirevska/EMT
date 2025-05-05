package emt.service.domain;

import emt.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(Author author);
    Optional<Author> update(Long id, Author author);
    void delete(Long id);
}
