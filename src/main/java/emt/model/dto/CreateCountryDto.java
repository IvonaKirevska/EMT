package emt.model.dto;

import emt.model.domain.Country;

public record CreateCountryDto(String name, String continent) {
    public Country toCountry(){
        return new Country(name, continent);
    }
}
