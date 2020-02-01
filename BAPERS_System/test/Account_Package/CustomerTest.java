/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import Database_Package.DBConnectivity;
import Process_Package.Job;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test class for testing methods belonging to the Customer class.
 * 
 * @author      Felice Gregorio
 * @version     (29/03/2018) version 2.1
 * @since       (13/03/2018) version 1
 */
public class CustomerTest {
    /** The connectivity to the database.  */
    private final DBConnectivity myDB = new DBConnectivity();
    
    public CustomerTest() {}
    
    /**
     * Test of constructor for existing customer, of class Customer.
     * <p>
     * This test ensures that when constructing a Customer object for an existing customer with the second constructor, 
     * the data needed to initialise the object is correctly extracted from the database, if it is a "Valued" customer.
     * 
     * @throws java.sql.SQLException
     */
    @Test
    public void testExistingCustConstructor() throws SQLException {
        System.out.println("secondConstructor");
        Customer cust = new Customer("ACC0002");
        
        assertEquals("Peter", cust.getF_name());
        assertEquals("Smith", cust.getS_name());
        assertEquals("36 Turin Lane", cust.getAddress());
        assertEquals("London", cust.getCity());
        assertEquals("United Kingdom", cust.getCountry());
        assertEquals("E12 5SR", cust.getPostcode());
        assertEquals("Peter.S@yahoo.co.uk", cust.getEmail());
        assertEquals("02083645778", cust.getPhone_num());
        assertEquals("Valued", cust.getAcc_type());
        assertEquals(new Date(new GregorianCalendar(2018, 02, 10).getTimeInMillis()), cust.getNextPayment());
        assertEquals(0, cust.getOutstandingBalance(), 0.0);
        assertEquals(0, cust.getMonthlyJobs().size(), 0);
    }
    
    /**
     * Test of getAcc_type method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetAcc_type() throws SQLException {
        System.out.println("getAcc_type");
        Customer cust = new Customer("ACC2330", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String expResult = "Normal";
        String result = cust.getAcc_type();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setAcc_type method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAcc_type() throws SQLException {
        System.out.println("setAcc_type");
        Customer cust = new Customer("ACC2331", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String acc_type = "Valued";
        cust.setAcc_type(acc_type);
        
        assertEquals(acc_type, cust.getAcc_type());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT account_type FROM Customers WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(acc_type, rs.getString("account_type"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setPhone_num method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPhone_num() throws SQLException {
        System.out.println("setPhone_num");
        Customer cust = new Customer("ACC2332", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String phone_num = "02094675836";
        cust.setPhone_num(phone_num);
        
        assertEquals(phone_num, cust.getPhone_num());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT phone_num FROM Customers WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(phone_num, rs.getString("phone_num"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setEmail method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetEmail() throws SQLException {
        System.out.println("setEmail");
        Customer cust = new Customer("ACC2333", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String email = "fmg97@outlook.co.uk";
        cust.setEmail(email);
        
        assertEquals(email, cust.getEmail());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT email FROM Customers WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(email, rs.getString("email"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setPostcode method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPostcode() throws SQLException {
        System.out.println("setPostcode");
        Customer cust = new Customer("ACC2334", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String postcode = "IT2 7TS";
        cust.setPostcode(postcode);
        
        assertEquals(postcode, cust.getPostcode());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT postcode FROM Customers WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(postcode, rs.getString("postcode"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setCountry method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetCountry() throws SQLException {
        System.out.println("setCountry");
        Customer cust = new Customer("ACC2335", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String country = "Italy";
        cust.setCountry(country);
        
        assertEquals(country, cust.getCountry());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT country FROM Customers WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(country, rs.getString("country"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setCity method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetCity() throws SQLException {
        System.out.println("setCity");
        Customer cust = new Customer("ACC2336", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String city = "Manchester";
        cust.setCity(city);
        
        assertEquals(city, cust.getCity());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT city FROM Customers WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(city, rs.getString("city"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setAddress method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAddress() throws SQLException {
        System.out.println("setAddress");
        Customer cust = new Customer("ACC2337", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String address = "57 Tramway Avenue";
        cust.setAddress(address);
        
        assertEquals(address, cust.getAddress());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT address FROM Customers WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(address, rs.getString("address"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setS_name method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetS_name() throws SQLException {
        System.out.println("setS_name");
        Customer cust = new Customer("ACC2338", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String s_name = "Cambridge";
        cust.setS_name(s_name);
        
        assertEquals(s_name, cust.getS_name());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT last_name FROM Customers WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(s_name, rs.getString("last_name"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setF_name method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetF_name() throws SQLException {
        System.out.println("setF_name");
        Customer cust = new Customer("ACC2339", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String f_name = "Mark";
        cust.setF_name(f_name);
        
        assertEquals(f_name, cust.getF_name());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT first_name FROM Customers WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(f_name, rs.getString("first_name"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

      /**
     * Test of upgradeAccount method, of class Customer.
     * @throws java.text.ParseException
     * @throws java.sql.SQLException
     */
    @Test
    public void testUpgradeAccount() throws ParseException, SQLException {
        System.out.println("upgradeAccount");
        Customer cust = new Customer("ACC2340", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        Date paymentDue = new Date(new GregorianCalendar(2018, 04, 20).getTimeInMillis());
        double outPayment = 0;
        String status = "Active";
        ArrayList<Job> jobs = new ArrayList<>();
        cust.upgradeAccount(paymentDue, outPayment, status, jobs);
        
        assertEquals(jobs, cust.getMonthlyJobs());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT * FROM ValuedCustomer WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(cust.getCustID(), rs.getString("customer_ID"));
            assertEquals(paymentDue, rs.getDate("payment_due"));
            assertEquals(outPayment, rs.getDouble("outstanding_balance"), 0);
            assertEquals(status, rs.getString("status_of_account"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }
    
    /**
     * Test of downgradeAccount method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testDowngradeAccount() throws SQLException {
        System.out.println("downgradeAccount");
        Customer cust = new Customer("ACC0002");
        cust.downgradeAccount();
        
        assertEquals("Normal", cust.getAcc_type());
        assertNull(cust.getNextPayment());
        assertEquals(0, cust.getOutstandingBalance(), 0);
        assertNull(cust.getStatus());
        assertTrue(cust.getMonthlyJobs().isEmpty());
        
        //Checks if the details were correctly updated in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT * FROM ValuedCustomer WHERE customer_ID = '" + cust.getCustID() + "'");
        ResultSet rs2 = myDB.read("SELECT account_type FROM Customers WHERE customer_ID = '" + cust.getCustID() + "'");
        assertTrue(!rs.next());
        if (rs2.next()){
            assertEquals(cust.getAcc_type(), rs2.getString("account_type"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of addMonthlyJob method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddMonthlyJob() throws SQLException {
        System.out.println("addMonthlyJob");
        Customer cust = new Customer("ACC2341", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        cust.upgradeAccount(new Date(new GregorianCalendar(2018, 04, 15).getTimeInMillis()), 0, "Acitve", new ArrayList<>());
        Date deadline= new Date(new GregorianCalendar(2018, 01, 24, 18, 30).getTimeInMillis());
        Job job = new Job("None", "Normal", new Timestamp(System.currentTimeMillis()), new Timestamp(deadline.getTime()), cust.getCustID(), new ArrayList<>());
        cust.addMonthlyJob(job);
        
        assertEquals(1, cust.getMonthlyJobs().size());
    }

    /**
     * Test of emptyMonthlyJobs method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testEmptyMonthlyJobs() throws SQLException {
        System.out.println("emptyMonthlyJobs");
        Customer cust = new Customer("ACC2342", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(new Job("B108"));
        cust.upgradeAccount(new Date(new GregorianCalendar(2018, 04, 15).getTimeInMillis()), 0, "Acitve", jobs);
        cust.emptyMonthlyJobs();
        
        assertTrue(cust.getMonthlyJobs().isEmpty());
    }

    /**
     * Test of setNextPayment method, of class Customer.
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    @Test
    public void testSetNextPayment() throws SQLException, ParseException {
        System.out.println("setNextPayment");
        Customer cust = new Customer("ACC2343", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        cust.upgradeAccount(new Date(new GregorianCalendar(2018, 04, 15).getTimeInMillis()), 0, "Acitve", new ArrayList<>());
        Date newPymntDate = new Date(new GregorianCalendar(2018, 05, 15).getTimeInMillis());
        cust.setNextPayment();
        
        assertEquals(newPymntDate, cust.getNextPayment());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT payment_due FROM ValuedCustomer WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(newPymntDate, rs.getDate("payment_due"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setOutstandingPayment method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetOutstandingPayment() throws SQLException {
        System.out.println("setOutstandingPayment");
        Customer cust = new Customer("ACC2344", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        cust.upgradeAccount(new Date(new GregorianCalendar(2018, 04, 15).getTimeInMillis()), 0, "Acitve", new ArrayList<>());
        double outstandingPayment = 100;
        cust.setOutstandingBalance(outstandingPayment);
        
        assertEquals(outstandingPayment, cust.getOutstandingBalance(), 0);
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT outstanding_balance FROM ValuedCustomer WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(outstandingPayment, rs.getDouble("outstanding_balance"), 0);
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setStatus method, of class Customer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetStatus() throws SQLException {
        System.out.println("setStatus");
        Customer cust = new Customer("ACC2345", "Felice", "Gregorio", "22 Green Lane", "London", 
                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        cust.upgradeAccount(new Date (new GregorianCalendar(2018, 04, 15).getTimeInMillis()), 0, "Acitve", new ArrayList<>());
        String status = "In Default";
        cust.setStatus(status);
        
        assertEquals(status, cust.getStatus());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT status_of_account FROM ValuedCustomer WHERE customer_ID = '" + cust.getCustID() + "'");
        if (rs.next()){
            assertEquals(status, rs.getString("status_of_account"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

//    /**
//     * Test of setMonthlyJobs method, of class Customer.
//     * @throws java.sql.SQLException
//     */
//    @Test
//    public void testSetMonthlyJobs() throws SQLException {
//        System.out.println("setMonthlyJobs");
//        Customer cust = new Customer("ACC2346", "Felice", "Gregorio", "22 Green Lane", "London", 
//                                        "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
//        cust.upgradeAccount(new Date(new GregorianCalendar(2018, 04, 15).getTimeInMillis()), 0, "Acitve", new ArrayList<>());
//        ArrayList<Job> monthlyJobs = new ArrayList<>();
//        Date deadline= new Date(new GregorianCalendar(2018, 01, 24, 18, 30).getTimeInMillis());
//        monthlyJobs.add(new Job("None", "Normal", new Timestamp(System.currentTimeMillis()), new Timestamp(deadline.getTime()), cust.getCustID(), new ArrayList<>()));
//        cust.setMonthlyJobs(monthlyJobs);
//        
//        assertEquals(monthlyJobs, cust.getMonthlyJobs());
//    }  
}
