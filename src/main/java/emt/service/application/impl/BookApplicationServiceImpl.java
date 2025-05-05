package emt.service.application.impl;

import emt.model.domain.Author;
import emt.model.domain.enums.Category;
import emt.model.dto.CreateBookDto;
import emt.model.dto.UpdateBookDto;
import emt.service.application.BookApplicationService;
import emt.service.domain.AuthorService;
import emt.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public List<UpdateBookDto> findAll() {
        return bookService.findAll().stream().map(UpdateBookDto::from).toList();
    }

    @Override
    public Optional<UpdateBookDto> findById(Long id) {
        return bookService.findById(id).map(UpdateBookDto::from);
    }

    @Override
   public Optional<UpdateBookDto> save(CreateBookDto createBookDto) {
        Optional<Author> author=authorService.findById(createBookDto.authorId());
        Category category=Category.valueOf(createBookDto.category());

        if (author.isPresent()){
            return bookService.save(createBookDto.toBook(category, author.get()))
                    .map(UpdateBookDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UpdateBookDto> update(Long id, CreateBookDto createBookDto) {
        Optional<Author> author=authorService.findById(createBookDto.authorId());
        Category category=Category.valueOf(createBookDto.category());

        return bookService.update(id, createBookDto.toBook(category, author.orElse(null)))
                .map(UpdateBookDto::from);
    }

    @Override
    public void delete(Long id) {
        bookService.delete(id);
    }

    @Override
    public Optional<UpdateBookDto> markedAsRead(Long id) {
        return bookService.markedAsRead(id).map(UpdateBookDto::from);
    }
}
