/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports_Package;

import System_Package.Writer;
import Database_Package.DBConnectivity;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Shows how to construct an Individual Performance Report object and its associated variables and methods.
 * 
 * @author      Felice Gregorio
 * @version     (11/04/2018) version 2.3
 * @since       (26/03/2018) version 1
 */
public class IndividualPerformanceReport extends Report{
    /** The connectivity to the database. */
    private final DBConnectivity myDB = new DBConnectivity();

    /**
    * Constructor for a new Individual Performance Report object.
    * <p>
    * This is the constructor used when creating and generating a new Individual Performance Report.
    * It is needed to create an instance of this class.
    * 
    * @param start The starting date range of the report.
    * @param end The ending date range of the report.
    * 
    */
    public IndividualPerformanceReport(Date start, Date end) {
        super.initialise("Individual Performance Report", start, end);
    }

    /**
    * Generate Individual Performance Report.
    * <p>
    * This is a concrete method which runs the SQL query use to generate an Individual Performance Report 
    * and returns an ArrayList of ResultSets which stores the ResultSet generated by the query. 
    * 
    * @return Returns an ArrayList of ResultSets containing ResultSets relevant to the report.
    */
    @Override
    public ArrayList<ResultSet> generate(boolean write) {
        ArrayList<ResultSet> rss = new ArrayList<>();
        myDB.connect();
        rss.add(myDB.execStoredProc("{ call spIndividualPerformanceReport(?, ?) }", null, getStart(), getEnd()));
        if(write){
            Writer rw = new Writer("generatedReports.txt");
            rw.writeGeneratedReport("Individual Performance Report", getStart(), getEnd(), null);
        }
        
        return rss;
    }

    /**
    * Get Database Connectivity.
    * <p>
    * This function retrieves the database connectivity object used to generate this report.
    * 
    * @return Returns the database connectivity object.
    * 
    */
    public DBConnectivity getMyDB() {
        return myDB;
    }
}
