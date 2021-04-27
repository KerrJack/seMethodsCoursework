/**
 *  Date Created: 07/02/2021
 *  Authors:    Kerr Jack   -   40440876 (last updated
 *              Christopher Rhodes  -   40432612
 *              Rory Owens  -   40439757
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

public class App {
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        if (args.length < 1)
        {
            a.connect("localhost:33070");
        }
        else
        {
            a.connect(args[0]);
        }

        // Get information and load into ArrayLists
        ArrayList<Country> country = a.getpoplargetosmall();
        ArrayList<Country> reportTwo = a.getReportTwo();
        ArrayList<Country> reportThree = a.getReportThree();
        ArrayList<Country> reportFour = a.getReportFour();
        ArrayList<Country> reportFive = a.getReportFive();
        ArrayList<Country> reportSix = a.getReportSix();

        // Display results
        a.printPopulations(country);
        a.printPopulations(reportTwo);
        a.printPopulations(reportThree);
        a.printPopulations(reportFour);
        a.printPopulations(reportFive);
        a.printPopulations(reportSix);

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

    // method for all countries in the world - large to small
    public ArrayList<Country> getpoplargetosmall()
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

    // method for all countries in a continent - large to small
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
             * Prints a list of countries.
             * @param country The list of country to print.
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
}
