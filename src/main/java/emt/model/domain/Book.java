package emt.model.domain;

import jakarta.persistence.*;
import emt.model.domain.enums.Category;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Author author;
    private Integer availableCopies;
    private Integer timesRented=0;

    public Book(Long id, String name, Category category, Author author, Integer availableCopies, Integer timesRented) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.timesRented=timesRented;
    }

    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book() {

    }

    public Book(Author author, Category category, String name) {
        this.author=author;
        this.category=category;
        this.name=name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setTimesRented(Integer timesRented) {
        this.timesRented = timesRented;
    }

    public Integer getTimesRented() {
        return timesRented;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }
}
