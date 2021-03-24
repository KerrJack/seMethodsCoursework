package com.napier.seMethodsCoursework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33070");
    }

    @Test
    void printPopulationTest()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.Code ="CHN";
        country.Name ="China";
        country.Continent ="Asia";
        country.Region ="Eastern Asia";
        country.Capital = 1891;
        country.Population = 1277558000;
        countries.add(country);

        app.printPopulations(countries);
    }

}