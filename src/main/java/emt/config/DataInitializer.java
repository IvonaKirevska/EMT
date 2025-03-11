package emt.config;

import jakarta.annotation.PostConstruct;
import emt.model.Author;
import emt.model.Country;
import emt.model.Book;
import emt.model.enums.Category;
import org.springframework.stereotype.Component;
import emt.repository.AuthorRepository;
import emt.repository.BookRepository;
import emt.repository.CountryRepository;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(CountryRepository countryRepository,
                           AuthorRepository authorRepository,
                           BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        Country macedonia = countryRepository.save(new Country("Macedonia", "Europe"));
        Country usa = countryRepository.save(new Country("USA", "North America"));
        Country uk = countryRepository.save(new Country("UK", "Europe"));

        Author author1 = authorRepository.save(new Author("Petre", "Andreevski", macedonia));
        Author author2 = authorRepository.save(new Author("Stephen", "King", usa));
        Author author3 = authorRepository.save(new Author("J.K.", "Rowling", uk));

        bookRepository.save(new Book("Pirej", Category.DRAMA, author1, 3));
        bookRepository.save(new Book("The Shining", Category.THRILLER, author2, 5));
        bookRepository.save(new Book("Harry Potter", Category.FANTASY, author3, 7));
    }
}
