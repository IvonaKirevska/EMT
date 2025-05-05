//package emt.config;
//
//import emt.repository.AuthorsPerCountryViewRepository;
//import emt.repository.BooksPerAuthorViewRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MaterializedViewRefresher {
//    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
//    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;
//
//    public MaterializedViewRefresher(BooksPerAuthorViewRepository booksPerAuthorViewRepository, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
//        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
//        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
//    }
//
//
//    @PostConstruct
//    public void init() {
//        booksPerAuthorViewRepository.refreshMaterializedView();
//        authorsPerCountryViewRepository.refreshMaterializedView();
//    }
//}

