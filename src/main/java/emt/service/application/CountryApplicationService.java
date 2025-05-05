package emt.service.application;

import emt.model.domain.Country;
import emt.model.dto.CreateCountryDto;
import emt.model.dto.UpdateCountryDto;
import emt.model.views.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    List<UpdateCountryDto> findAll();
    Optional<UpdateCountryDto> save(CreateCountryDto createCountryDto);
    Optional<UpdateCountryDto> findById(Long id);
    Optional<UpdateCountryDto> update(Long id, CreateCountryDto createCountryDto);
    void deleteById(Long id);

    List<AuthorsPerCountryView> findAllAuthorsPerCountry();
    AuthorsPerCountryView findAuthorsPerCountry(Long id);
    void refreshMaterializedView();
}
