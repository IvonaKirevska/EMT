package emt.service.application.impl;

import emt.model.dto.CreateCountryDto;
import emt.model.dto.UpdateCountryDto;
import emt.model.views.AuthorsPerCountryView;
import emt.repository.AuthorsPerCountryViewRepository;
import emt.service.application.CountryApplicationService;
import emt.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;

    public CountryApplicationServiceImpl(CountryService countryService, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.countryService = countryService;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }

    @Override
    public List<UpdateCountryDto> findAll() {
        return UpdateCountryDto.from(countryService.findAll());
    }

    @Override
    public Optional<UpdateCountryDto> save(CreateCountryDto createCountryDto) {
        return countryService.save(createCountryDto.toCountry())
                .map(UpdateCountryDto::from);
    }

    @Override
    public Optional<UpdateCountryDto> findById(Long id) {
        return countryService.findById(id).map(UpdateCountryDto::from);
    }

    @Override
    public Optional<UpdateCountryDto> update(Long id, CreateCountryDto createCountryDto) {
        return countryService.update(id, createCountryDto.toCountry())
                .map(UpdateCountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }

    @Override
    public List<AuthorsPerCountryView> findAllAuthorsPerCountry() {
        return authorsPerCountryViewRepository.findAll();
    }

    @Override
    public AuthorsPerCountryView findAuthorsPerCountry(Long id) {
        return authorsPerCountryViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        authorsPerCountryViewRepository.refreshMaterializedView();
    }
}
