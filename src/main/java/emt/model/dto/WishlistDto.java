package emt.model.dto;

import emt.model.domain.Wishlist;

import java.util.List;

public record WishlistDto(Long id, UpdateUserDto userDto, List<UpdateBookDto> books) {
    public static WishlistDto from(Wishlist wishlist) {
        return new WishlistDto(
                wishlist.getId(),
                UpdateUserDto.from(wishlist.getUser()),
                wishlist.getBooks() != null ? wishlist.getBooks().stream().map(UpdateBookDto::from).toList() : List.of()
        );
    }
}
