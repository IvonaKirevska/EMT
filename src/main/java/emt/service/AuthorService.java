package emt.service;

import emt.model.Author;
import emt.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
}
