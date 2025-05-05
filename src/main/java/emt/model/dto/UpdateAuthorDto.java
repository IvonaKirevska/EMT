package emt.model.dto;

import emt.model.domain.Author;
import emt.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateAuthorDto(Long country, String name, String surname) {
    public static UpdateAuthorDto from(Author author){
        return new UpdateAuthorDto(author.getCountry().getId(), author.getName(), author.getSurname());
    }

    public static List<UpdateAuthorDto> from(List<Author> authors){
        return authors.stream().map(UpdateAuthorDto::from).collect(Collectors.toList());
    }

    public Author toAuthor(Country country){
        return new Author(country,name,surname);
    }
}
