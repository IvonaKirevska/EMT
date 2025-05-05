package emt.service.domain;

import emt.model.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(Book book);
    Optional<Book> update(Long id, Book book);
    void delete(Long id);
    Optional<Book> markedAsRead(Long id);
}
