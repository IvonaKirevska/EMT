package emt.model.domain;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Book> books;

    public Wishlist(){}

    public Wishlist(User user, List<Book> books) {
        this.user = user;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
