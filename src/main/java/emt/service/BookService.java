package emt.service;

import emt.model.dto.BookDto;

import emt.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(BookDto book);
    Optional<Book> update(Long id, BookDto bookDto);
    void delete(Long id);
    Optional<Book> markedAsRead(Long id);
}
