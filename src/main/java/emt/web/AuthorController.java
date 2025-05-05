package emt.web;

import emt.model.dto.CreateAuthorDto;
import emt.service.application.AuthorApplicationService;
import emt.service.application.CountryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
@Tag(name="Author api", description = "Endpoits for managing authors")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;
    private final CountryApplicationService countryApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService, CountryApplicationService countryApplicationService) {
        this.authorApplicationService = authorApplicationService;
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping
    @Operation(summary = "List all authors", description = "Retrieves a list of all the authors")
    public ResponseEntity<?> listAllAuthors() {
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by ID", description = "Finds an author by its ID")
    public ResponseEntity<?> listAuthor(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "Adds a new author", description = "Creates a new author based on the given AuthorDto")
    public ResponseEntity<?> addAuthor(@RequestBody CreateAuthorDto authorDto) {
        return ResponseEntity.ok(authorApplicationService.save(authorDto));
    }


    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an existing author", description = "Updates an author by its ID")
    public ResponseEntity<?> editAuthor(@RequestBody CreateAuthorDto authorDto, @PathVariable Long id) {
        return ResponseEntity.ok(authorApplicationService.update(id, authorDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an author", description = "Deletes an author by its ID")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        authorApplicationService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/per-country")
    public ResponseEntity<?> findAllNumberOfAuthorsPerCountry() {
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findAllAuthorsPerCountry());
    }

    @GetMapping("/per-country/{id}")
    public ResponseEntity<?> findNumberOfAuthorsPerCountry(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findAuthorsPerCountry(id));
    }
}
