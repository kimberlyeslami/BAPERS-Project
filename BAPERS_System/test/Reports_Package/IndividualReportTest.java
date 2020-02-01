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
 * A test class for testing methods belonging to the IndividualReport class.
 * 
 * @author      Felice Gregorio
 * @version     (11/04/2018) version 1.1
 * @since       (28/03/2018) version 1
 */
public class IndividualReportTest {
    
    public IndividualReportTest() {
    }

    /**
     * Test of generate method, of class IndividualReport.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGenerate() throws SQLException {
        System.out.println("generate");
        Date start = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Date end = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        IndividualReport report = new IndividualReport("ACC0001", start, end);
        ArrayList<ResultSet> result = report.generate(true);
        
        assertEquals(1, result.size(), 0.0);
        
        ResultSet rs = result.get(0);
        
        while(rs.next()){
            assertEquals("Jane", rs.getString("First Name"));
            assertEquals("Doe", rs.getString("Surname"));
            assertEquals("ABN54", rs.getString("Job Code"));
            assertEquals("None", rs.getString("Special Instructions"));
            assertEquals(new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis()), rs.getDate("Date Accepted"));
            assertEquals(new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis()), rs.getDate("Deadline Date"));
            assertEquals(19, rs.getDouble("Price"), 0.0);
            assertEquals(19, rs.getDouble("Total Price"), 0.0);
            assertEquals(19, rs.getDouble("Total Cost"), 0.0);
        }
        
        report.getMyDB().closeConnection();
    }
    
}
