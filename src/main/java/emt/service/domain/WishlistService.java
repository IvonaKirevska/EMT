package emt.service.domain;

import emt.model.domain.Wishlist;
import emt.model.domain.Book;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    List<Book> listAllBooksInWishlist(Long wishlistId);
    Optional<Wishlist> addBookToWishlist(String userId, Long BookId);
    Optional<Wishlist> rentWishlist(String userId);
}
