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
 * A test class for testing methods belonging to the IndividualPerformanceReport class.
 * 
 * @author      Felice Gregorio
 * @version     (11/04/2018) version 1.1
 * @since       (28/03/2018) version 1
 */
public class IdividualPerformanceReportTest {
    
    public IdividualPerformanceReportTest() {
    }

    /**
     * Test of generate method, of class IndividualPerformanceReport.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGenerate() throws SQLException {
        System.out.println("generate");
        Date start = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Date end = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        IndividualPerformanceReport report = new IndividualPerformanceReport(start, end);
        ArrayList<ResultSet> result = report.generate(true);
        
        assertEquals(1, result.size(), 0.0);
        
        ResultSet rs = result.get(0);
        
        rs.next();
        assertEquals("Billy", rs.getString("First Name"));
        assertEquals("House", rs.getString("Surname"));
        assertEquals("ABN54", rs.getString("Job Code"));
        assertEquals(3, rs.getInt("Task ID"));
        assertEquals("Packing Department", rs.getString("Department"));
        assertEquals(new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis()), rs.getDate("Date"));
        assertEquals(java.sql.Time.valueOf("18:30:00"), rs.getTime("Start Time"));
        assertEquals(30, rs.getDouble("Time Taken"), 0.0);
        assertEquals(30, rs.getDouble("Total"), 0.0);
        assertEquals(210, rs.getDouble("Total Effort"), 0.0);
        
        rs.next();
        assertEquals("James", rs.getString("First Name"));
        assertEquals("Wilson", rs.getString("Surname"));
        assertEquals("ABN54", rs.getString("Job Code"));
        assertEquals(2, rs.getInt("Task ID"));
        assertEquals("Development Area", rs.getString("Department"));
        assertEquals(new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis()), rs.getDate("Date"));
        assertEquals(java.sql.Time.valueOf("02:30:00"), rs.getTime("Start Time"));
        assertEquals(60, rs.getDouble("Time Taken"), 0.0);
        assertEquals(60, rs.getDouble("Total"), 0.0);
        assertEquals(210, rs.getDouble("Total Effort"), 0.0);
        
        rs.next();
        assertEquals("Maggie", rs.getString("First Name"));
        assertEquals("Brown", rs.getString("Surname"));
        assertEquals("ABN54", rs.getString("Job Code"));
        assertEquals(1, rs.getInt("Task ID"));
        assertEquals("Copy Room", rs.getString("Department"));
        assertEquals(new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis()), rs.getDate("Date"));
        assertEquals(java.sql.Time.valueOf("12:30:00"), rs.getTime("Start Time"));
        assertEquals(120, rs.getDouble("Time Taken"), 0.0);
        assertEquals(120, rs.getDouble("Total"), 0.0);
        assertEquals(210, rs.getDouble("Total Effort"), 0.0);
        
        report.getMyDB().closeConnection();
    }
    
}
