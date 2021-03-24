/**
 * Description: This class contains all the unit tests for the methods in the main App.java
 *              class.
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
        country.code ="CHN";
        country.name ="China";
        country.continent ="Asia";
        country.region ="Eastern Asia";
        country.capital = 1891;
        country.population = 1277558000;
        countries.add(country);

        app.printPopulations(countries);
    }
}
