package emt.service.application.impl;

import emt.model.dto.UpdateBookDto;
import emt.model.dto.WishlistDto;
import emt.service.application.WishlistApplicationService;
import emt.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {

    private final WishlistService wishlistService;

    public WishlistApplicationServiceImpl(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public List<UpdateBookDto> listAllBooksInWishlist(Long wishlistId) {
        return UpdateBookDto.from(wishlistService.listAllBooksInWishlist(wishlistId));
    }

    @Override
    public Optional<WishlistDto> addBookToWishlist(String userId, Long bookId) {
        return wishlistService.addBookToWishlist(userId, bookId)
                .map(WishlistDto::from);
    }

    @Override
    public Optional<WishlistDto> rentWishlist(String userId) {
        return wishlistService.rentWishlist(userId)
                .map(WishlistDto::from);
    }
}
