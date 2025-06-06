package emt.web;

import emt.model.domain.Book;
import emt.model.domain.User;
import emt.model.domain.Wishlist;
import emt.model.dto.UpdateBookDto;
import emt.model.dto.WishlistDto;
import emt.repository.UserRepository;
import emt.service.application.WishlistApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "Wishlist API", description = "Endpoints for managing the wishlist")
public class WishlistController {
    private final WishlistApplicationService wishlistApplicationService;

    public WishlistController(WishlistApplicationService wishlistApplicationService, UserRepository userRepository) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    @PostMapping("/add-book/{id}")
    @Operation(summary = "Add book to wishlist", description = "Adds a book to the wishlist for the logged in user")
    @ApiResponses(value = {@ApiResponse(
            responseCode = "200", description = "Book added to wishlist successfully"
    ), @ApiResponse(
            responseCode = "400", description = "Invalid request"
    ), @ApiResponse(responseCode = "404", description = "Book not found")})
    public ResponseEntity<WishlistDto> addBookToWishlist(@PathVariable Long id, Authentication authentication){
        try {
            User user = (User) authentication.getPrincipal();
            return wishlistApplicationService.addBookToWishlist(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get all books from wishlist", description = "Retrieves a list of all books from the wishlist.")
    @GetMapping
    public ResponseEntity<List<UpdateBookDto>> listAllBooksInWishlist(){
        return null;
    }

}