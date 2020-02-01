/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports_Package;

import java.sql.Date;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test class for testing methods belonging to the Report abstract class.
 * 
 * @author      Felice Gregorio
 * @version     (28/03/2018) version 1
 * @since       (28/03/2018) version 1
 */
public class ReportTest {
    
    public ReportTest() {
    }

    /**
     * Test of getReptName method, of class Report.
     */
    @Test
    public void testGetReptName() {
        System.out.println("getReptName");
        Date start = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Date end = new Date(new GregorianCalendar(2018, 01, 25).getTimeInMillis());
        Report report = new IndividualReport("ACC0001", start, end);
        String expResult = "Individual Report";
        String result = report.getReptName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setReptName method, of class Report.
     */
    @Test
    public void testSetReptName() {
        System.out.println("setReptName");
        String reptName = "Individual Rept";
        Date start = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Date end = new Date(new GregorianCalendar(2018, 01, 25).getTimeInMillis());
        Report report = new IndividualReport("ACC0001", start, end);
        report.setReptName(reptName);
        
        assertEquals(reptName, report.getReptName());
    }

    /**
     * Test of getStart method, of class Report.
     */
    @Test
    public void testGetStart() {
        System.out.println("getStart");
        Date start = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Date end = new Date(new GregorianCalendar(2018, 01, 25).getTimeInMillis());
        Report report = new IndividualReport("ACC0001", start, end);
        Date result = report.getStart();
        
        assertEquals(start, result);
    }

    /**
     * Test of setStart method, of class Report.
     */
    @Test
    public void testSetStart() {
        System.out.println("setStart");
        Date start = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Date end = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Report report = new IndividualReport("ACC0001", start, end);
        Date newStart = new Date(new GregorianCalendar(2018, 01, 23).getTimeInMillis());
        report.setStart(newStart);
        
        assertEquals(newStart, report.getStart());
    }

    /**
     * Test of getEnd method, of class Report.
     */
    @Test
    public void testGetEnd() {
        System.out.println("getEnd");
        Date start = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Date end = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Report report = new IndividualReport("ACC0001", start, end);
        Date result = report.getEnd();
        
        assertEquals(end, result);
    }

    /**
     * Test of setEnd method, of class Report.
     */
    @Test
    public void testSetEnd() {
        System.out.println("setEnd");
        Date start = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Date end = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Report report = new IndividualReport("ACC0001", start, end);
        Date newEnd = null;
        report.setEnd(newEnd);
        
        assertEquals(newEnd, report.getEnd());
    }
}
