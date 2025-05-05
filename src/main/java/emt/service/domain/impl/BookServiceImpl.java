package emt.service.domain.impl;

import emt.model.domain.Book;
import emt.model.domain.enums.Category;
import emt.service.domain.AuthorService;
import org.springframework.stereotype.Service;
import emt.repository.BookRepository;
import emt.service.domain.BookService;

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
    public Optional<Book> save(Book book) {
        //Optional<Author> authorOptional = authorRepository.findById(book.authorId);
        if (book.getAvailableCopies()!=null && authorService.findById(book.getAuthor().getId()).isPresent() && book.getCategory()!=null && book.getName()!=null && book.getAuthor().getId()!=null){
            Category category=Category.valueOf((book.getCategory().name()));
            return Optional.of(bookRepository.save(new Book(authorService.findById(book.getAuthor().getId()).get(), category, book.getName())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        //Optional<Author> authorOptional = authorRepository.findById(book.authorId);
        return bookRepository.findById(id)
                .map(existingBook->{
                    if (book.getName()!=null){
                        existingBook.setName(book.getName());
                    }
                    if (book.getCategory()!=null){
                        existingBook.setCategory(Category.valueOf(book.getCategory().name()));
                    }
                    if (book.getAuthor()!=null && authorService.findById(book.getAuthor().getId()).isPresent()){
                        existingBook.setAuthor(authorService.findById(book.getAuthor().getId()).get());
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
                    book.setTimesRented(book.getTimesRented()+1);
                    return bookRepository.save(book);
                });
    }
}
