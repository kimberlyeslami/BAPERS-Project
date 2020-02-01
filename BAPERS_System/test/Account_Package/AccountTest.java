/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test class for testing methods belonging to the Account class.
 * 
 * @author      Felice Gregorio
 * @version     (29/03/2018) version 2
 * @since       (12/03/2018) version 1
 */
public class AccountTest {
    
    public AccountTest() {}

    /**
     * Test of constructor for existing account, of class Account.
     * <p>
     * This test ensures that when constructing an Account object for an existing account with the second constructor, 
     * the data needed to initialise the object is correctly extracted from the database.
     * 
     */
    @Test
    public void testExistingAccConstructor() throws SQLException {
        System.out.println("secondConstructor");
        Account acc = new Account("ACC0002");
        
        assertEquals("Peter", acc.getF_name());
        assertEquals("Smith", acc.getS_name());
        assertEquals("36 Turin Lane", acc.getAddress());
        assertEquals("London", acc.getCity());
        assertEquals("United Kingdom", acc.getCountry());
        assertEquals("E12 5SR", acc.getPostcode());
        assertEquals("Peter.S@yahoo.co.uk", acc.getEmail());
        assertEquals("02083645778", acc.getPhone_num());
    }
    
    /**
     * Test of getF_name method, of class Account.
     */
    @Test
    public void testGetF_name() {
        System.out.println("getF_name");
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String expResult = "Felice";
        String result = acc.getF_name();
        assertEquals(expResult, result);
    }

    /**
     * Test of setF_name method, of class Account.
     */
    @Test
    public void testSetF_name() {
        System.out.println("setF_name");
        String newF_name = "Gregory";
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        acc.setF_name(newF_name);
        assertEquals(newF_name, acc.getF_name());
    }

    /**
     * Test of getS_name method, of class Account.
     */
    @Test
    public void testGetS_name() {
        System.out.println("getS_name");
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String expResult = "Gregorio";
        String result = acc.getS_name();
        assertEquals(expResult, result);
    }

    /**
     * Test of setS_name method, of class Account.
     */
    @Test
    public void testSetS_name() {
        System.out.println("setS_name");
        String newS_name = "House";
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        acc.setS_name(newS_name);
        assertEquals(newS_name, acc.getS_name());
    }

    /**
     * Test of getAddress method, of class Account.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String expResult = "22 Green Lane";
        String result = acc.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Account.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String newAddress = "123 Princes Avenue";
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        acc.setAddress(newAddress);
        assertEquals(newAddress, acc.getAddress());
    }

    /**
     * Test of getCity method, of class Account.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String expResult = "London";
        String result = acc.getCity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCity method, of class Account.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String newCity = "Rome";
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        acc.setCity(newCity);
        assertEquals(newCity, acc.getCity());
    }

    /**
     * Test of getCountry method, of class Account.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String expResult = "United Kingdom";
        String result = acc.getCountry();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCountry method, of class Account.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        String newCountry = "Italy";
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        acc.setCountry(newCountry);
        assertEquals(newCountry, acc.getCountry());
    }

    /**
     * Test of getPostcode method, of class Account.
     */
    @Test
    public void testGetPostcode() {
        System.out.println("getPostcode");
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String expResult = "N9 8DR";
        String result = acc.getPostcode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPostcode method, of class Account.
     */
    @Test
    public void testSetPostcode() {
        System.out.println("setPostcode");
        String newPostcode = "E12 5DA";
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        acc.setPostcode(newPostcode);
        assertEquals(newPostcode, acc.getPostcode());
    }

    /**
     * Test of getEmail method, of class Account.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String expResult = "felice.g@hotmail.com";
        String result = acc.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Account.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String newEmail = "fmg97@gmail.com";
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        acc.setEmail(newEmail);
        assertEquals(newEmail, acc.getEmail());
    }

    /**
     * Test of getPhone_num method, of class Account.
     */
    @Test
    public void testGetPhone_num() {
        System.out.println("getPhone_num");
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        String expResult = "07960805243";
        String result = acc.getPhone_num();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPhone_num method, of class Account.
     */
    @Test
    public void testSetPhone_num() {
        System.out.println("setPhone_num");
        String newPhone_num = "02083764561";
        Account acc = new Account("Felice", "Gregorio", "22 Green Lane", "London", 
                                "United Kingdom", "N9 8DR", "felice.g@hotmail.com", "07960805243");
        acc.setPhone_num(newPhone_num);
        assertEquals(newPhone_num, acc.getPhone_num());
    }
    
}
