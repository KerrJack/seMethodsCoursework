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