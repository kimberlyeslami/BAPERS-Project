/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports_Package;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Shows how to construct a Report object and its associated variables and methods.
 * 
 * @author      Felice Gregorio
 * @version     (11/04/2018) version 2.3
 * @since       (10/03/2018) version 1
 */
public abstract class Report {
    /** The Report Name.  */
    private String reptName;
    /** The starting date range for the report. */
    private Date start;
    /** The ending date range for the report. */
    private Date end;
    
    /**
    * Constructor for a new Report object.
    * <p>
    * This is the constructor used when creating and generating a new Report.
    * It is needed to create an instance of this class.
    * 
    * @param reptName The name of the report.
    * @param start The starting date range of the report.
    * @param end The ending date range of the report.
    * 
    */
    public void initialise(String reptName, Date start, Date end) {
        this.reptName = reptName;
        this.start = start;
        this.end = end;
    }

    /**
    * Generate report.
    * <p>
    * This is an abstract function which runs SQL queries specific to a report 
    * and returns an ArrayList of ResultSets which store result sets relevant to the report being generated. 
    * 
    * @param write
    * @return Returns an ArrayList of ResultSets containing ResultSets relevant to the report.
    */
    public abstract ArrayList<ResultSet> generate(boolean write);
    
    /**
    * Get Report Name.
    * <p>
    * This function retrieves the name of the report.
    * 
    * @return Returns the report name.
    * 
    */
    public String getReptName() {
        return reptName;
    }

    /**
    * Set Report Name.
    * <p>
    * This function sets/changes the report name.
    * 
    * @param reptName The report name to be set.
    * 
    */
    public void setReptName(String reptName) {
        this.reptName = reptName;
    }

    /**
    * Get start date range.
    * <p>
    * This function retrieves the starting date range for the report.
    * 
    * @return Returns stating date range.
    * 
    */
    public Date getStart() {
        return start;
    }

    /**
    * Set start date range.
    * <p>
    * This function sets/changes the starting date range for the report.
    * 
    * @param start The starting date range to be set.
    * 
    */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
    * Get end date range.
    * <p>
    * This function retrieves the ending date range for the report.
    * 
    * @return Returns ending date range.
    * 
    */
    public Date getEnd() {
        return end;
    }

    /**
    * Set end date range.
    * <p>
    * This function sets/changes the ending date range for the report.
    * 
    * @param end The ending date range to be set.
    * 
    */
    public void setEnd(Date end) {
        this.end = end;
    }
}
