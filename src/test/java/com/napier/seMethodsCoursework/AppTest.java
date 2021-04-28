/**
 * Description: This class contains all the unit tests.
 *
 *
 */
package com.napier.seMethodsCoursework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    static App app;

    @BeforeAll
    static void init()
    {
        // Creates instance of App
        app = new App();
    }

    // This checks what happens when null is passed into printPopulations
    @Test
    void printPopulationTestNull()
    {
        app.printPopulations(null);
    }

    // This tests what happens when countries is empty
    @Test
    void printPopulationTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        app.printPopulations(countries);
    }

    // This tests what happens when you try to print the list with a null value
    @Test
    void printPopulationTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        app.printPopulations(countries);
    }

    // This is testing for normal conditions with all non-NULL values
    @Test
    void printPopulationTest()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.setCode("CHN");
        country.setCountryName("China");
        country.setCountryContinent("Asia");
        country.setCountryRegion("Eastern Asia");
        country.setCountryCapital(1891);
        country.setCountryPopulation(1277558000);
        countries.add(country);

        app.printPopulations(countries);
    }

    // This checks what happens when null is passed into printReports
    @Test
    void printReportsTestNull()
    {
        app.printReports(null);
    }

    @Test
    void printReportsTestEmpty()
    {
        ArrayList<Country_City> country_city = new ArrayList<Country_City>();
        app.printReports(country_city);
    }

    @Test
    void printReportsTestContainsNull()
    {
        ArrayList<Country_City> country_city = new ArrayList<Country_City>();
        country_city.add(null);
        app.printReports(country_city);
    }
    @Test
    void printReportsTest()
    {
        ArrayList<Country_City> country_cities = new ArrayList<Country_City>();
        Country_City country_city = new Country_City();
        country_city.setCityName_city("Mumbai (Bombay)");
        country_city.setCountryName_country("India");
        country_city.setDistrictName_city("Maharashtra");
        country_city.setCityPopulation_city(10500000);

        country_cities.add(country_city);

        app.printReports(country_cities);
    }
}
