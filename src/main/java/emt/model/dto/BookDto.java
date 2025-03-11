package emt.model.dto;

import lombok.Data;

@Data
public class BookDto {
    public String name;
    public String category;
    public Long authorId;
    public Integer availableCopies;

    public BookDto(){}

    public BookDto(String name, String category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }
}
