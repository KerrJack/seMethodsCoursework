/**
 *  Date Created: 07/02/2021
 *  Authors:    Kerr Jack   -   40440876 (Last updated - 28/04/2021)
 *              Christopher Rhodes  -   40432612 (Last updated - 03/03/2021)
 *              Rory Owens  -   40439757 (Last updated - 28/04/2021)
 *  Description: This class is the main app that contains all the methods and the functionality
 *               of the program. This also contains the code that allows us to connect to the
 *               database.
 *
 * Copyright 2016-2017 SparklingComet @ http://shanerx.org
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *            http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.napier.seMethodsCoursework;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 *
 *
 */
public class App {
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        if (args.length < 1)
        {
            // Connect to database
            a.connect("localhost:33070");
        }
        else
        {
            a.connect(args[0]);
        }

        // Get information and load into ArrayLists type Country
        ArrayList<Country> reportOne = a.getReportOne();
        ArrayList<Country> reportTwo = a.getReportTwo();
        ArrayList<Country> reportThree = a.getReportThree();
        ArrayList<Country> reportFour = a.getReportFour();
        ArrayList<Country> reportFive = a.getReportFive();
        ArrayList<Country> reportSix = a.getReportSix();


        // New ArrayList type Country_City
        ArrayList<Country_City> reportSeven = a.getReportSeven();
        ArrayList<Country_City> reportEight = a.getReportEight();
        ArrayList<Country_City> reportNine = a.getReportNine();
        ArrayList<Country_City> reportTen = a.getReportTen();
        ArrayList<Country_City> reportEleven = a.getReportEleven();
        ArrayList<Country_City> reportTwelve = a.getReportTwelve();
        ArrayList<Country_City> reportThirteen = a.getReportThirteen();
        ArrayList<Country_City> reportFourteen = a.getReportFourteen();
        ArrayList<Country_City> reportFifteen = a.getReportFifteen();
        ArrayList<Country_City> reportSixteen = a.getReportSixteen();
        ArrayList<Country_City> reportSeventeen = a.getReportSeventeen();
        ArrayList<Country_City> reportEighteen = a.getReportEighteen();
        ArrayList<Country_City> reportNineteen = a.getReportNineteen();
        ArrayList<Country_City> reportTwenty = a.getReportTwenty();
        ArrayList<Country_City> reportTwentyOne = a.getReportTwentyOne();

        // New ArrayList type Continent_Population
        ArrayList<Continent_Population> reportTwentyTwo = a.getReportTwentyTwo();

        // Display results for type Country
        a.printPopulations(reportOne);
        a.printPopulations(reportTwo);
        a.printPopulations(reportThree);
        a.printPopulations(reportFour);
        a.printPopulations(reportFive);
        a.printPopulations(reportSix);

        // Display results for type Country_City
        a.printReports(reportSeven);
        a.printReports(reportEight);
        a.printReports(reportNine);
        a.printReports(reportTen);
        a.printReports(reportEleven);
        a.printReports(reportTwelve);
        a.printReports(reportThirteen);
        a.printReports(reportFourteen);
        a.printReports(reportFifteen);
        a.printReports(reportSixteen);
        a.printReports(reportSeventeen);
        a.printReports(reportEighteen);
        a.printReports(reportNineteen);
        a.printReports(reportTwenty);
        a.printReports(reportTwentyOne);

        // Display results for type Continent_Population
        a.printPopulationReportsContinent(reportTwentyTwo);


        // Disconnect from database
        a.disconnect();
    }
    // Connection to MySQL database.

    private Connection con = null;


    // Connect to the MySQL database
    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            // Error message if drive is not loading
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Loop that continues to try and make a connection
        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to world database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                // Error message if it cant connect to database
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }


    //  Disconnect from the MySQL database.
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                //Error message if connection to database is not made
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Issue 1 : As a statistician for the government I want to produce a report of all countries
     * in the world organised by largest population to smallest to assess successful methods for
     * vaccine rollout.
     *
     * **/
    public ArrayList<Country> getReportOne()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cntry = new Country();
                // Get column names from country table
                cntry.setCode(rset.getString("Code"));
                cntry.setCountryName(rset.getString("Name"));
                cntry.setCountryContinent(rset.getString("Continent"));
                cntry.setCountryRegion(rset.getString("Region"));
                cntry.setCountryCapital(rset.getInt("Capital"));
                cntry.setCountryPopulation(rset.getInt("Population"));

                country.add(cntry);

            }
            return country;
        }
        catch (Exception e)
        {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 2 : As a statistician for the government I want to produce a report of all the countries in a
     * continent organised by largest population to smallest to assess successful methods for vaccine rollout.
     *
     * **/
    public ArrayList<Country> getReportTwo()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent='Asia' ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> country = new ArrayList<>();
            while (rset.next())
            {
                Country cntry = new Country();
                // Get column names from country table
                cntry.setCode(rset.getString("Code"));
                cntry.setCountryName(rset.getString("Name"));
                cntry.setCountryContinent(rset.getString("Continent"));
                cntry.setCountryRegion(rset.getString("Region"));
                cntry.setCountryCapital(rset.getInt("Capital"));
                cntry.setCountryPopulation(rset.getInt("Population"));
                country.add(cntry);

            }
            return country;
        }
        catch (Exception e)
        {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 3 : As a statistician for the government I want to produce a report of all the
     * countries in a region organised by largest population to smallest to assess successful
     * methods for vaccine rollout
     *
     * **/
    public ArrayList<Country> getReportThree()
    {
        try
        {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Region='Central Africa' ORDER BY Population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cntry = new Country();
                // Get column names from country table
                cntry.setCode(rset.getString("Code"));
                cntry.setCountryName(rset.getString("Name"));
                cntry.setCountryContinent(rset.getString("Continent"));
                cntry.setCountryRegion(rset.getString("Region"));
                cntry.setCountryCapital(rset.getInt("Capital"));
                cntry.setCountryPopulation(rset.getInt("Population"));
                country.add(cntry);

            }
            return country;
        }
        catch (Exception e)
        {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 4 : As a statistician for the government I want to produce a report of the top (N)
     * populated countries in the world where I will give the value to (N)
     *
     * **/
    public ArrayList<Country> getReportFour()
    {
        try
        {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital FROM country ORDER BY Population DESC LIMIT 10";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cntry = new Country();
                // Get column names from country table
                cntry.setCode(rset.getString("Code"));
                cntry.setCountryName(rset.getString("Name"));
                cntry.setCountryContinent(rset.getString("Continent"));
                cntry.setCountryRegion(rset.getString("Region"));
                cntry.setCountryCapital(rset.getInt("Capital"));
                cntry.setCountryPopulation(rset.getInt("Population"));

                country.add(cntry);

            }
            return country;
        }
        catch (Exception e)
        {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 5 : As a statistician for the government I want to produce a report of the top (N)
     * populated countries in a continent where I will give the value to (N)
     *
     * **/
    public ArrayList<Country>getReportFive() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE Continent = 'Asia' ORDER BY Population DESC LIMIT 10";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country = new ArrayList<Country>();

            while (rset.next()) {
                Country cntry = new Country();
                // Get column names from country table
                cntry.setCode(rset.getString("Code"));
                cntry.setCountryName(rset.getString("Name"));
                cntry.setCountryContinent(rset.getString("Continent"));
                cntry.setCountryRegion(rset.getString("Region"));
                cntry.setCountryCapital(rset.getInt("Capital"));
                cntry.setCountryPopulation(rset.getInt("Population"));

                country.add(cntry);

            }
            return country;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }

    }


    /**
     * Issue 6 : As a statistician for the government I want to produce a report of the top (N)
     * populated countries in a region where I will give the value to (N)
     *
     * **/
    public ArrayList<Country>getReportSix() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital FROM country WHERE region = 'Southern Europe' ORDER BY Population DESC LIMIT 10";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> country = new ArrayList<Country>();

            while (rset.next()) {
                Country cntry = new Country();
                // Get column names from country table
                cntry.setCode(rset.getString("Code"));
                cntry.setCountryName(rset.getString("Name"));
                cntry.setCountryContinent(rset.getString("Continent"));
                cntry.setCountryRegion(rset.getString("Region"));
                cntry.setCountryCapital(rset.getInt("Capital"));
                cntry.setCountryPopulation(rset.getInt("Population"));

                country.add(cntry);

            }
            return country;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 7 : As a statistician for the government I want to produce a report of all
     * the cities in the world organised by largest population to smallest
     *
     * **/
    public ArrayList<Country_City>getReportSeven() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.countrycode = country.code ORDER BY city.population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 8 : As a statistician for the government  I want to produce a report of all the
     * cities in a region organised by largest population to smallest
     *
     * **/
    public ArrayList<Country_City>getReportEight() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.countrycode = country.code AND country.region = 'Western Africa' ORDER BY city.population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 9 : As a statistician for the government  I want to produce a report of all
     * the cities in a country organised by largest population to smallest
     *
     * **/
    public ArrayList<Country_City>getReportNine() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.countrycode = country.code AND country.name = 'France' ORDER BY city.population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 10 : As a statistician for the government  I want to produce a report
     * of all the cities in a district organised by largest population to smallest
     *
     * **/
    public ArrayList<Country_City>getReportTen() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.countrycode = country.code AND city.district = 'New York' ORDER BY city.population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 11 : As a statistician for the government  I want to produce a report of the top (N)
     * populated cities in the world where I will give the value of (N)
     *
     * **/
    public ArrayList<Country_City>getReportEleven() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.countrycode = country.code ORDER BY city.population DESC LIMIT 5";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 12 : As a statistician for the government  I want to produce a report of the top (N)
     * populated cities in a continent where I will give the value of (N)
     *
     * **/
    public ArrayList<Country_City>getReportTwelve() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.countrycode = country.code AND country.continent = 'Europe' ORDER BY city.population DESC LIMIT 5";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 13 : As a statistician for the government  I want to produce a report of the top (N)
     * populated cities in a region where I will give the value of (N)
     *
     * **/
    public ArrayList<Country_City>getReportThirteen() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.countrycode = country.code AND country.region = 'Central America' ORDER BY city.population DESC LIMIT 5";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 14 : As a statistician for the government  I want to produce a report of the top (N)
     * populated cities in a country where I will give the value of (N)
     *
     * **/
    public ArrayList<Country_City>getReportFourteen() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.countrycode = country.code AND country.name = 'China' ORDER BY city.population DESC LIMIT 5";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 15 : As a statistician for the government  I want to produce a report of the top (N)
     * populated cities in a district where I will give the value of (N)
     *
     * **/
    public ArrayList<Country_City>getReportFifteen() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.countrycode = country.code AND city.district = 'Delhi' ORDER BY city.population DESC LIMIT 5";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 16 : As a statistician for the government  I want to produce a report of all the
     * capital cities in the world organised by largest population to smallest
     *
     * **/
    public ArrayList<Country_City>getReportSixteen() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.ID = country.capital ORDER BY city.population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 17 : As a statistician for the government  I want to produce a report of all the
     * capital cities in a continent organised by largest population to smallest
     *
     * **/
    public ArrayList<Country_City>getReportSeventeen() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.ID = country.capital AND country.continent = 'Africa' ORDER BY city.population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 18 : As a statistician for the government  I want to produce a report of all the
     * capital cities in a region organised by largest population to smallest
     *
     * **/
    public ArrayList<Country_City>getReportEighteen() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.ID = country.capital AND country.region = 'Middle East' ORDER BY city.population DESC";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 19 : As a statistician for the government  I want to produce a report of the top (N)
     * populated capital cities in the world where I will provide the value (N)
     *
     * **/
    public ArrayList<Country_City>getReportNineteen() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.ID = country.capital ORDER BY city.population DESC LIMIT 5";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 20 : As a statistician for the government  I want to produce a report of the top (N)
     * populated capital cities in a continent where I will provide the value (N)
     *
     * **/
    public ArrayList<Country_City>getReportTwenty() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.ID = country.capital AND country.continent = 'South America' ORDER BY city.population DESC LIMIT 5";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 21 : As a statistician for the government  I want to produce a report of the top (N)
     * populated capital cities in a region where I will provide the value (N)
     *
     * **/
    public ArrayList<Country_City>getReportTwentyOne() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "SELECT city.Name, country.Name, city.district, city.population FROM city, country WHERE city.ID = country.capital AND country.region = 'British Islands' ORDER BY city.population DESC LIMIT 5";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country_City> country_city = new ArrayList<>();

            while (rset.next()) {
                Country_City cntry_city = new Country_City();
                // Get column names from country table
                cntry_city.setCityName_city(rset.getString("city.Name"));
                cntry_city.setCountryName_country(rset.getString("country.Name"));
                cntry_city.setDistrictName_city(rset.getString("city.District"));
                cntry_city.setCityPopulation_city(rset.getInt("city.Population"));

                country_city.add(cntry_city);

            }
            return country_city;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Issue 22 : As a statistician for the government  I want to produce a report of the
     * population of people, people living in cities and people not living in cities in each continent
     *
     * **/
    public ArrayList<Continent_Population>getReportTwentyTwo() {
        try {
            Statement stmt = con.createStatement();

            String strSelect =
                    "select query1.Continent, query1.population_of_people, query2.people_living_in_cities, ROUND(((query2.people_living_in_cities/query1.population_of_people) * 100),0) AS percentage_in_cities,(query1.population_of_people - query2.people_living_in_cities) as people_not_living_in_cities, ROUND((((query1.population_of_people - query2.people_living_in_cities)/query1.population_of_people)* 100),0) AS percentage_not_in_cities from\n" +
                            "(select country.Continent, SUM(country.Population) as population_of_people\n" +
                            "from country\n" +
                            "group by country.Continent) as query1\n" +
                            "inner join \n" +
                            "(select country.Continent, SUM(city.Population) as people_living_in_cities\n" +
                            "from country\n" +
                            "left join city on country.Code = city.CountryCode\n" +
                            "group by country.Continent) as query2 on query2.Continent = query1.Continent\n";

            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Continent_Population> continent_populations = new ArrayList<>();

            while (rset.next()) {
                Continent_Population continent_population = new Continent_Population();
                // Get column names from country table
                continent_population.setCountryContinent_continent(rset.getString("Continent"));
                continent_population.setTotalPopulation_continent(rset.getLong("population_of_people"));
                continent_population.setTotalCityPopulation_continent(rset.getLong("people_living_in_cities"));
                continent_population.setCityPopulationPercentage_continent(rset.getLong("percentage_in_cities"));
                continent_population.setNotInCityPopulation_continent(rset.getLong("people_not_living_in_cities"));
                continent_population.setNotInCityPopulationPercentage_continent(rset.getLong("percentage_not_in_cities"));

                continent_populations.add(continent_population);

            }
            return continent_populations;
        } catch (Exception e) {
            // Error message if no information can be gathered
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }





    /**
     * Prints reports for countries.
     * @param country The list which contains the country information to print.
     */
    public void printPopulations(ArrayList<Country> country)
    {

        // Checks country is not null
        if (country == null)
        {
            System.out.println("No countries");
            return;
        }
        // Print header
        System.out.println("\n");
        System.out.println(String.format("%-10s %-45s %-15s %-26s %-15s %-15s", "Code","Name", "Continent", "Region","Population", "Capital"));
        // Loop over all countries in the list
        for (Country cntry : country)
        {
            if (cntry == null)
                continue;
            String cntry_string =
                    String.format("%-10s %-45s %-15s %-26s %-15s %-15s", cntry.getCode(), cntry.getCountryName(), cntry.getCountryContinent(), cntry.getCountryRegion(), cntry.getCountryPopulation(), cntry.getCountryCapital() );
            System.out.println(cntry_string);
        }
    }

    /**
     * Prints reports for countries.
     * @param country_cities The list which contains the country and city information to print.
     */
    public void printReports(ArrayList<Country_City> country_cities)
    {

        // Checks country is not null
        if (country_cities == null)
        {
            System.out.println("No countries");
            return;
        }
        // Print header
        System.out.println("\n");
        System.out.println(String.format("%-45s %-45s %-25s %-15s", "City Name","Country Name", "District", "Population"));
        // Loop over all countries in the list
        for (Country_City country_city : country_cities)
        {
            if (country_city == null)
                continue;
            String cntry_city_string =
                    String.format("%-45s %-45s %-25s %-15s",  country_city.getCityName_city(), country_city.getCountryName_country(), country_city.getDistrictName_city(), country_city.getCityPopulation_city() );
            System.out.println(cntry_city_string);
        }
    }

    public void printPopulationReportsContinent(ArrayList<Continent_Population> continent_populations)
    {

        // Checks country is not null
        if (continent_populations == null)
        {
            System.out.println("No countries");
            return;
        }
        // Print header
        System.out.println("\n");
        System.out.println(String.format("%-20s %-20s %-20s %-35s %-25s %-30s", "Continent","Total Population", "City Population", "Percentage of people in cities","Out Of City Population", "Percentage of people not in cities" ));
        // Loop over all countries in the list
        for (Continent_Population continent_population : continent_populations)
        {
            if (continent_population == null)
                continue;
            String cont_pop_string =
                    String.format("%-20s %-20s %-20s %-35s %-25s %-30s", continent_population.getCountryContinent_continent(), continent_population.getTotalPopulation_continent(), continent_population.getTotalCityPopulation_continent(), continent_population.getCityPopulationPercentage_continent(), continent_population.getNotInCityPopulation_continent(), continent_population.getNotInCityPopulationPercentage_continent() );
            System.out.println(cont_pop_string);
        }
    }
}
