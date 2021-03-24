package com.napier.seMethodsCoursework;

import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect("localhost:33070");
        // Get Country
        // Extract employee salary information
        ArrayList<Country> country = a.getpoplargetosmall();
        ArrayList<Country> reportTwo = a.getReportTwo();
        // Display results
        a.printPopulations(country);
        a.printPopulations(reportTwo);

        // Disconnect from database
        a.disconnect();
    }
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
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
                System.out.println("Error closing connection to database");
            }
        }
    }
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
                cntry.Code = rset.getString("Code");
                cntry.Name = rset.getString("Name");
                cntry.Continent = rset.getString("Continent");
                cntry.Region = rset.getString("Region");
                cntry.Capital = rset.getInt("Capital");
                cntry.Population = rset.getInt("Population");

                country.add(cntry);

            }
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

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
            // Extract employee information
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cntry = new Country();
                cntry.Code = rset.getString("Code");
                cntry.Name = rset.getString("Name");
                cntry.Continent = rset.getString("Continent");
                cntry.Region = rset.getString("Region");
                cntry.Capital = rset.getInt("Capital");
                cntry.Population = rset.getInt("Population");

                country.add(cntry);

            }
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }
    /**
     * Prints a list of employees.
     * @param country The list of employees to print.
     */
    public void printPopulations(ArrayList<Country> country)
    {

        // Check country is not null
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
                    String.format("%-10s %-45s %-15s %-26s %-15s %-15s", cntry.Code, cntry.Name, cntry.Continent, cntry.Region, cntry.Population, cntry.Capital );
            System.out.println(cntry_string);
        }
    }
}
