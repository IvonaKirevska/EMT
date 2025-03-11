package emt.service.impl;

import emt.model.Author;
import emt.model.Book;
import emt.model.dto.BookDto;
import emt.model.enums.Category;
import emt.service.AuthorService;
import org.springframework.stereotype.Service;
import emt.repository.AuthorRepository;
import emt.repository.BookRepository;
import emt.service.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }



    @Override
    public Optional<Book> save(BookDto book) {
        //Optional<Author> authorOptional = authorRepository.findById(book.authorId);
        if (book.getAvailableCopies()!=null && authorService.findById(book.getAuthorId()).isPresent() && book.getCategory()!=null && book.getName()!=null && book.getAuthorId()!=null){
            Category category=Category.valueOf((book.getCategory()));
            return Optional.of(bookRepository.save(new Book(authorService.findById(book.getAuthorId()).get(), category, book.getName())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Long id, BookDto book) {
        //Optional<Author> authorOptional = authorRepository.findById(book.authorId);
        return bookRepository.findById(id)
                .map(existingBook->{
                    if (book.getName()!=null){
                        existingBook.setName(book.getName());
                    }
                    if (book.getCategory()!=null){
                        existingBook.setCategory(Category.valueOf(book.getCategory()));
                    }
                    if (book.getAuthorId()!=null && authorService.findById(book.getAuthorId()).isPresent()){
                        existingBook.setAuthor(authorService.findById(book.getAuthorId()).get());
                    }
                    if (book.getAvailableCopies()!=null){
                        existingBook.setAvailableCopies(book.getAvailableCopies());
                    }
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> markedAsRead(Long id) {
        return bookRepository.findById(id)
                .filter(book -> book.getAvailableCopies()>0)
                .map(book -> {
                    book.setAvailableCopies(book.getAvailableCopies()-1);
                    return bookRepository.save(book);
                });
    }
}
