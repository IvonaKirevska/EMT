package emt.model.dto;

import emt.model.domain.Country;

import java.util.List;
import java.util.stream.Collectors;

public record UpdateCountryDto(String name, String continent) {
    public static UpdateCountryDto from(Country country) {
        return new UpdateCountryDto(country.getName(), country.getContinent());
    }

    public static List<UpdateCountryDto> from(List<Country> categories) {
        return categories.stream().map(UpdateCountryDto::from).collect(Collectors.toList());
    }
}
