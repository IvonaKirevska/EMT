package emt.model.dto;
import emt.model.domain.Author;
import emt.model.domain.Book;
import emt.model.domain.enums.Category;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateBookDto(String name, String category, Long authorId, Integer availableCopies) {
    public static UpdateBookDto from(Book book){
        return new UpdateBookDto(book.getName(), book.getCategory().name(), book.getAuthor().getId(), book.getAvailableCopies());
    }

    public static List<UpdateBookDto> from(List<Book> books){
        return books.stream().map(UpdateBookDto::from).collect(Collectors.toList());
    }

    public Book toBook(Category category, Author author){
        return new Book(name, category, author, availableCopies);
    }
}
