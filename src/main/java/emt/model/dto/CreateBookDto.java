package emt.model.dto;

import emt.model.domain.Author;
import emt.model.domain.Book;
import emt.model.domain.enums.Category;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDto(String name, String category, Long authorId, Integer availableCopies) {

    public static CreateBookDto from(Book book){
        return new CreateBookDto(book.getName(), book.getCategory().name(), book.getAuthor().getId(), book.getAvailableCopies());
    }

    public static List<CreateBookDto> from(List<Book> books){
        return books.stream().map(CreateBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Category category, Author author){
        return new Book(name, category, author, availableCopies);
    }
}
