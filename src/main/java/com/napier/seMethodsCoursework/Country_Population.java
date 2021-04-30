package com.napier.seMethodsCoursework;

public class Country_Population {

    //initialise getter and setter for Country
    private String country_country;

    public String getCountry_country() {
        return country_country;
    }

    public void setCountry_country(String country_country) {
        this.country_country = country_country;
    }


    //initialise getter and setter for country population
    private Long totalPopulation_country;

    public Long getTotalPopulation_country() {
        return totalPopulation_country;
    }

    public void setTotalPopulation_country(Long totalPopulation_country) {
        this.totalPopulation_country = totalPopulation_country;
    }


    //initialise getter and setter for city in country population
    private Long totalCityPopulation_country;

    public Long getTotalCityPopulation_country() {
        return totalCityPopulation_country;
    }

    public void setTotalCityPopulation_country(Long totalCityPopulation_country) {
        this.totalCityPopulation_country = totalCityPopulation_country;
    }

    //initialise getter and setter for city in country population percentage
    private Long cityPopulationPercentage_country;

    public Long getCityPopulationPercentage_country() {
        return cityPopulationPercentage_country;
    }

    public void setCityPopulationPercentage_country(Long cityPopulationPercentage_country) {
        this.cityPopulationPercentage_country = cityPopulationPercentage_country;
    }



    //initialise getter and setter for not in city country population
    private Long notInCityPopulation_country;

    public Long getNotInCityPopulation_country() {
        return notInCityPopulation_country;
    }

    public void setNotInCityPopulation_country(Long notInCityPopulation_country) {
        this.notInCityPopulation_country = notInCityPopulation_country;
    }


    //initialise getter and setter for not in city country population percentage
    private Long notInCityPopulationPercentage_country;

    public Long getNotInCityPopulationPercentage_country() {
        return notInCityPopulationPercentage_country;
    }

    public void setNotInCityPopulationPercentage_country(Long notInCityPopulationPercentage_country) {
        this.notInCityPopulationPercentage_country = notInCityPopulationPercentage_country;
    }
}
