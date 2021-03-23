package com.napier.seMethodsCoursework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private App testee;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    //@Test TODO - start and stop DB docker container before and after test
    void getpoplargetosmall() {
        testee = new App();
        testee.connect();
        List<Country> countries = new ArrayList<Country>();
        countries = testee.getpoplargetosmall();

        assertTrue(countries.size() >= 0);
    }

    @Test
    void printPopulations() {
        testee = new App();
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.Name = "Scotland";
        country.Population = 5000000;
        countries.add(country);
        testee.printPopulations(countries);

        assertEquals("There are 1 countries in total\nName       Population     \nScotland   5000000", outputStreamCaptor.toString()
                .trim());

    }
}