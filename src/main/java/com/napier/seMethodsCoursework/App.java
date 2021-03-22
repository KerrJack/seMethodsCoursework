package com.napier.seMethodsCoursework;

import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get Employee
        // Extract employee salary information
        ArrayList<Country> country = a.getpoplargetosmall();
        // Display results
        a.printPopulations(country);

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
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
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
                // Wait for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
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
            String strSelect = "SELECT Name, Population FROM country ORDER BY population DESC";

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract country information
            ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next())
            {
                Country cntry = new Country();
                cntry.Name = rset.getString("Name");
                cntry.Population = rset.getInt("Population");
                country.add(cntry);

            }
            return country;
        }
        catch (Exception e)
        {
            //Error message to be sent to output if no population details are found
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }

    }
    /**
     * Prints a list of countries.
     * @param country The list of countries to print.
     */
    public void printPopulations(ArrayList<Country> country)
    {
        System.out.println("There are " + country.size() + " countries in total");
        // Print header
        System.out.println(String.format("%-10s %-15s", "Name", "Population"));
        // Loop over all countries in the list
        for (Country cntry : country)
        {
            String cntry_string =
                    String.format("%-10s %-15s",
                            cntry.Name, cntry.Population);
            System.out.println(cntry_string);
        }
    }
}
