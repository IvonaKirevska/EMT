package emt.web;

import emt.model.dto.CreateBookDto;
import emt.model.dto.UpdateBookDto;
import emt.security.JwtConstants;
import emt.service.application.BookApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import emt.service.domain.BookService;
import emt.model.domain.Book;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book api", description = "Endpoints for managing books")
public class BookController {

    private final BookApplicationService bookApplicationService;

    public BookController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @GetMapping
    @Operation(summary = "List all books", description = "Retrieves a list of all the books")
    public ResponseEntity<?> listAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookApplicationService.findAll());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID", description = "Find a book by its ID")
    public ResponseEntity<?> findBookById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookApplicationService.findById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new book", description = "Creates a new book on the given BookDto")
    public ResponseEntity<?> addBook(@RequestBody CreateBookDto bookDto) {
        return ResponseEntity.ok(bookApplicationService.save(bookDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an existing book", description = "Updates a book by its ID")
    public ResponseEntity<?> editBook(@RequestBody CreateBookDto bookDto, @PathVariable Long id) {
        return ResponseEntity.ok(bookApplicationService.update(id, bookDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book by its ID")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookApplicationService.delete(id);
        return ResponseEntity.ok().build();
    }

    public String extractTokenFromRequest(HttpServletRequest request){
        String headerValue = request.getHeader(JwtConstants.HEADER);
        return headerValue.substring(JwtConstants.TOKEN_PREFIX.length());
    }

    @PatchMapping("/rent/{id}")
    @Operation(summary = "Rent a book", description = "Rents a book by its ID")
    public ResponseEntity<UpdateBookDto> markAsRented(@PathVariable Long id){
        return bookApplicationService.markedAsRead(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}




