package emt.service.application;

import emt.model.dto.CreateBookDto;
import emt.model.dto.UpdateBookDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<UpdateBookDto> findAll();
    Optional<UpdateBookDto> findById(Long id);
    Optional<UpdateBookDto> save(CreateBookDto createBookDto);
    Optional<UpdateBookDto> update(Long id, CreateBookDto createBookDto);
    void delete(Long id);
    Optional<UpdateBookDto> markedAsRead(Long id);
}
