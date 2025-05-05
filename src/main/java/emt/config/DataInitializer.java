package emt.config;

import emt.model.domain.User;
import emt.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import emt.model.domain.Author;
import emt.model.domain.Country;
import emt.model.domain.Book;
import emt.model.domain.enums.Category;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import emt.repository.AuthorRepository;
import emt.repository.BookRepository;
import emt.repository.CountryRepository;
import emt.model.domain.enums.Role;

@Component
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CountryRepository countryRepository,
                           AuthorRepository authorRepository,
                           BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Country macedonia = countryRepository.save(new Country("Macedonia", "Europe"));
        Country usa = countryRepository.save(new Country("USA", "North America"));
        Country uk = countryRepository.save(new Country("UK", "Europe"));

        Author author1 = authorRepository.save(new Author(macedonia, "Petre", "Andreevski"));
        Author author2 = authorRepository.save(new Author(usa, "Stephen", "King"));
        Author author3 = authorRepository.save(new Author(uk, "J.K.", "Rowling"));

        bookRepository.save(new Book("Pirej", Category.DRAMA, author1, 3));
        bookRepository.save(new Book("The Shining", Category.THRILLER, author2, 5));
        bookRepository.save(new Book("Harry Potter", Category.FANTASY, author3, 7));

        userRepository.save(new User(
                "lib",
                passwordEncoder.encode("lib"),
                "Lib",
                "Lib",
                Role.ROLE_LIBRARIAN
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));
    }
}
