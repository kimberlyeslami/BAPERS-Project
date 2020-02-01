/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import java.sql.SQLException;
import org.junit.Test;

/**
 * A test class for testing methods belonging to the Alert class.
 * 
 * @author      Felice Gregorio
 * @version     (28/03/2018) version 1
 * @since       (28/03/2018) version 1
 */
public class AlertTest {
    
    public AlertTest() {
    }
    /**
     * Test of genNewJobAlert method, of class Alert.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGenNewJobAlert() throws SQLException {
        System.out.println("genNewJobAlert");
        Alert instance = new Alert();
        instance.genNewJobAlert();
    }

    /**
     * Test of genLateTaskAlert method, of class Alert.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGenLateTaskAlert() throws SQLException {
        System.out.println("genLateTaskAlert");
        int taskID = 1;
        String jobCode = "ABN54";
        Alert instance = new Alert();
        instance.genLateTaskAlert();
    }

    /**
     * Test of genLatePayAlert method, of class Alert.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGenLatePayAlert() throws SQLException {
        System.out.println("genLatePayAlert");
        String custID = "ACC0001";
        int invoiceID = 1;
        Alert instance = new Alert();
        instance.genLatePayAlert();
    }

    /**
     * Test of genLetterAlert method, of class Alert.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGenLetterAlert() throws SQLException {
        System.out.println("genLetterAlert");
        Alert instance = new Alert();
        instance.genLetterAlert();
    }

    /**
     * Test of genDeadlineApproachingAlert method, of class Alert.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGenDeadlineApproachingAlert() throws SQLException {
        System.out.println("genDeadlineApproachingAlert");
        Alert instance = new Alert();
        instance.genDeadlineApproachingAlert();
    }
    
    /**
     * Test of genPriorityChangedAlert method, of class Alert.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGenPriorityChangedAlert() throws SQLException {
        System.out.println("genPriorityChangedAlert");
        Alert instance = new Alert();
        instance.genPriorityChangedAlert();
    }
}
