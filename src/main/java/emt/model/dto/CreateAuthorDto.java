package emt.model.dto;

import emt.model.domain.Author;
import emt.model.domain.Country;

public record CreateAuthorDto(Long country, String name, String surname) {
    public Author toAuthor(Country country){
        return new Author(country,name,surname);
    }
}
