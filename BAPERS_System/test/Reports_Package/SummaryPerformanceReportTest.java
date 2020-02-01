/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports_Package;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test class for testing methods belonging to the SummaryPerformanceReport class.
 * 
 * @author      Felice Gregorio
 * @version     (11/04/2018) version 1.1
 * @since       (28/03/2018) version 1
 */
public class SummaryPerformanceReportTest {

    /**
     * Test of generate method, of class SummaryPerformanceReport.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGenerate() throws SQLException {
        System.out.println("generate");
        Date start = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Date end = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        SummaryPerformanceReport report = new SummaryPerformanceReport(start, end);
        ArrayList<ResultSet> result = report.generate(true);
        
        assertEquals(4, result.size());
        
        //Checks the contents of the Day Shift 1 table
        ResultSet rs = result.get(0);
        
        while (rs.next()){
            assertEquals(new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis()), rs.getDate("Date"));
            assertEquals("Copy Room", rs.getString("Department"));
            assertEquals(120, rs.getDouble("Time"), 0.0);
            assertEquals(120, rs.getDouble("Total"), 0.0);
        }
        
        //Checks the contents of the Day Shift 2 table
        rs = result.get(1);
        
        while (rs.next()){
            assertEquals(new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis()), rs.getDate("Date"));
            assertEquals("Packing Department", rs.getString("Department"));
            assertEquals(30, rs.getDouble("Time"), 0.0);
            assertEquals(30, rs.getDouble("Total"), 0.0);
        }
        
        //Checks the contents of the Night Shift 1 table
        rs = result.get(2);
        
        while (rs.next()){
            assertEquals(new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis()), rs.getDate("Date"));
            assertEquals("Development Area", rs.getString("Department"));
            assertEquals(60, rs.getDouble("Time"), 0.0);
            assertEquals(60, rs.getDouble("Total"), 0.0);
        }
        
        //Checks the contents of the For Period table
        rs = result.get(3);
        
        rs.next();
        assertEquals("Copy Room", rs.getString("Department"));
        assertEquals(120, rs.getDouble("Day Shift 1 Total"), 0.0);
        assertEquals(0, rs.getDouble("Day Shift 2 Total"), 0.0);
        assertEquals(0, rs.getDouble("Night Shift 1 Total"), 0.0);
        assertEquals(120, rs.getDouble("Total"), 0.0);
        
        rs.next();
        assertEquals("Development Area", rs.getString("Department"));
        assertEquals(0, rs.getDouble("Day Shift 1 Total"), 0.0);
        assertEquals(0, rs.getDouble("Day Shift 2 Total"), 0.0);
        assertEquals(60, rs.getDouble("Night Shift 1 Total"), 0.0);
        assertEquals(60, rs.getDouble("Total"), 0.0);
        
        rs.next();
        assertEquals("Packing Department", rs.getString("Department"));
        assertEquals(0, rs.getDouble("Day Shift 1 Total"), 0.0);
        assertEquals(30, rs.getDouble("Day Shift 2 Total"), 0.0);
        assertEquals(0, rs.getDouble("Night Shift 1 Total"), 0.0);
        assertEquals(30, rs.getDouble("Total"), 0.0);
        
        report.getMyDB().closeConnection();
    }
    
}
