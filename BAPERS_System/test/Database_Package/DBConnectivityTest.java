/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database_Package;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test class for testing methods belonging to the DBConnectivity class.
 * 
 * @author      Felice Gregorio
 * @version     (15/03/2018) version 1.1
 * @since       (14/03/2018) version 1
 */
public class DBConnectivityTest {
    /** The connectivity to the database.  */
    DBConnectivity myDB = new DBConnectivity();
    
    public DBConnectivityTest() {}
    
    /**
     * Test of connect method, of class DBConnectivity.
     * @throws java.sql.SQLException
     */
    @Test
    public void testConnect() throws SQLException {
        System.out.println("open");
        myDB.connect();
        
        assertTrue(myDB.getConn().isValid(0));
    }

    /**
     * Test of closeConnection method, of class DBConnectivity.
     * @throws java.sql.SQLException
     */
    @Test
    public void testCloseConnection() throws SQLException {
        System.out.println("close");
        myDB.connect();
        myDB.closeConnection();
        
        assertTrue(myDB.getConn().isClosed());
    }

    /**
     * Test of read method, of class DBConnectivity.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRead() throws SQLException {
        System.out.println("read");
        String query = "SELECT * FROM Customers LIMIT 1";
        myDB.connect();
        ResultSet result = myDB.read(query);
        
        //Checks if the details were correctly read from the database
        if(result.next()){
            assertEquals("ACC0001", result.getString("customer_ID"));
            assertEquals("Jane", result.getString("first_name"));
            assertEquals("Doe", result.getString("last_name"));
            assertEquals("22 Lincln Road", result.getString("address"));
            assertEquals("London", result.getString("city"));
            assertEquals("United Kingdom", result.getString("country"));
            assertEquals("N9 8DR", result.getString("postcode"));
            assertEquals("JD@hotmail.com", result.getString("email"));
            assertEquals("07935467253", result.getString("phone_num"));
            assertEquals("Normal", result.getString("account_type"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of write method, of class DBConnectivity.
     * @throws java.sql.SQLException
     */
    @Test
    public void testWrite() throws SQLException {
        System.out.println("write");
        String query = "INSERT INTO Customers(customer_ID, first_name, last_name, address, "
                + "city, country, postcode, email, phone_num, account_type) "
                + "VALUES('ACC0007', 'Gregory', 'House', '23 Drum Road', 'Manchester', "
                + "'United Kingdom', 'M1 1AD', 'GH@btinternet.com', '02093766781', 'Valued')";
        String query2 = "SELECT * FROM Customers WHERE customer_ID = 'ACC0007'";
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        myDB.write(query);
        ResultSet result = myDB.read(query2);
        if (result.next()){
            assertEquals("ACC0007", result.getString("customer_ID"));
            assertEquals("Gregory", result.getString("first_name"));
            assertEquals("House", result.getString("last_name"));
            assertEquals("23 Drum Road", result.getString("address"));
            assertEquals("Manchester", result.getString("city"));
            assertEquals("United Kingdom", result.getString("country"));
            assertEquals("M1 1AD", result.getString("postcode"));
            assertEquals("GH@btinternet.com", result.getString("email"));
            assertEquals("02093766781", result.getString("phone_num"));
            assertEquals("Valued", result.getString("account_type"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of execStoredProc method, of class DBConnectivity.
     * @throws java.sql.SQLException
     */
    @Test
    public void testExecStoredProc() throws SQLException {
        System.out.println("execStoredProc");
        String storedProc = "{ call spDayShift1(?, ?) }";
        Date start = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        Date end = new Date(new GregorianCalendar(2018, 01, 24).getTimeInMillis());
        myDB.connect();
        ResultSet result = myDB.execStoredProc(storedProc, null, start, end);
        if (result.next()){
            assertEquals(start, result.getDate("Date"));
            assertEquals("Copy Room", result.getString("Department"));
            assertEquals(120, result.getDouble("Time"), 0.0);
            assertEquals(120, result.getDouble("Total"), 0.0);
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }
    
    /**
     * Test of backup method, of class DBConnectivity.
     */
    @Test
    public void testBackup(){
        myDB.connect();
        myDB.backup(false);
        myDB.closeConnection();
    }
    
    /**
     * Test of restore method, of class DBConnectivity.
     */
    @Test
    public void testRestore(){
        myDB.connect();
        myDB.restore("/Users/FMG97/BAPERS_Backup/BAPERS_bkup_20180330_141553.sql");
        myDB.closeConnection();
    }
}
