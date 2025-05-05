package emt.service.application;

import emt.model.dto.UpdateBookDto;
import emt.model.dto.WishlistDto;

import java.util.List;
import java.util.Optional;

public interface WishlistApplicationService {
    List<UpdateBookDto> listAllBooksInWishlist(Long wishlistId);
    Optional<WishlistDto> addBookToWishlist(String userId, Long bookId);
    Optional<WishlistDto> rentWishlist(String userId);
}
