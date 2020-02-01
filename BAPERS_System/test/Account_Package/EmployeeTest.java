/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import Database_Package.DBConnectivity;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test class for testing methods belonging to the Employee class.
 * 
 * @author      Felice Gregorio
 * @version     (21/03/2018) version 1.1
 * @since       (18/03/2018) version 1
 */
public class EmployeeTest {
    /** The connectivity to the database.  */
    private final DBConnectivity myDB = new DBConnectivity();
    
    public EmployeeTest() {}

    /**
     * Test of constructor for existing employee, of class Employee.
     * <p>
     * This test ensures that when constructing an Employee object for an existing employee with the second constructor, 
     * the data needed to initialise the object is correctly extracted from the database.
     * 
     * @throws java.sql.SQLException
     */
    @Test
    public void testExistingCustConstructor() throws SQLException {
        System.out.println("secondConstructor");
        Employee emp = new Employee("BH1234");
        
        assertEquals("Billy", emp.getF_name());
        assertEquals("House", emp.getS_name());
        assertEquals("55 Bouncers Road", emp.getAddress());
        assertEquals("London", emp.getCity());
        assertEquals("United Kingdom", emp.getCountry());
        assertEquals("E1 9IO", emp.getPostcode());
        assertEquals("Billy_H@hotmail.co.uk", emp.getEmail());
        assertEquals("07864755268", emp.getPhone_num());
        assertEquals("Olivia21", emp.getPassword());
        assertEquals("What is your pets name?", emp.getSecurityQuestion());
        assertEquals("Tommy", emp.getSecurityAnswer());
    }
    
    /**
     * Test of getRole method, of class Employee.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        String expResult = "Receptionist";
        String result = emp.getRole();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setRole method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetRole() throws SQLException {
        System.out.println("setRole");
        String role = "Technition";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setRole(role);
        
        assertEquals(role, emp.getRole());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT job_role FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(role, rs.getString("job_role"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of getSecurityQuestion method, of class Employee.
     */
    @Test
    public void testGetSecurityQuestion() {
        System.out.println("getSecurityQuestion");
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        String expResult = "What is your pets name?";
        String result = emp.getSecurityQuestion();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setSecurityQuestion method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetSecurityQuestion() throws SQLException {
        System.out.println("setSecurityQuestion");
        String securityQuestion = "What is your mothers maiden name?";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setSecurityQuestion(securityQuestion);
        
        assertEquals(securityQuestion, emp.getSecurityQuestion());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT security_question FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(securityQuestion, rs.getString("security_question"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of getSecurityAnswer method, of class Employee.
     */
    @Test
    public void testGetSecurityAnswer() {
        System.out.println("getSecurityAnswer");
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        String expResult = "Fluffy";
        String result = emp.getSecurityAnswer();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setSecurityAnswer method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetSecurityAnswer() throws SQLException {
        System.out.println("setSecurityAnswer");
        String securityAnswer = "Mary Jane";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setSecurityAnswer(securityAnswer);
        
        assertEquals(securityAnswer, emp.getSecurityAnswer());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT security_answer FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(securityAnswer, rs.getString("security_answer"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
        
    }

    /**
     * Test of getPassword method, of class Employee.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        String expResult = "Password123";
        String result = emp.getPassword();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPassword() throws SQLException {
        System.out.println("setPassword");
        String password = "Pass321";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setPassword(password);
        
        assertEquals(password, emp.getPassword());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT password FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(password, rs.getString("password"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setPhone_num method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPhone_num() throws SQLException {
        System.out.println("setPhone_num");
        String phone_num = "07986637521";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setPhone_num(phone_num);
        
        assertEquals(phone_num, emp.getPhone_num());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT phone_num FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(phone_num, rs.getString("phone_num"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setEmail method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetEmail() throws SQLException {
        System.out.println("setEmail");
        String email = "Holly.H@gmail.com";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setEmail(email);
        
        assertEquals(email, emp.getEmail());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT email FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(email, rs.getString("email"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setPostcode method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPostcode() throws SQLException {
        System.out.println("setPostcode");
        String postcode = "SW2 7HI";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setPostcode(postcode);
        
        assertEquals(postcode, emp.getPostcode());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT postcode FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(postcode, rs.getString("postcode"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setCountry method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetCountry() throws SQLException {
        System.out.println("setCountry");
        String country = "France";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setCountry(country);
        
        assertEquals(country, emp.getCountry());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT country FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(country, rs.getString("country"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setCity method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetCity() throws SQLException {
        System.out.println("setCity");
        String city = "Paris";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setCity(city);
        
        assertEquals(city, emp.getCity());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT city FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(city, rs.getString("city"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setAddress method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetAddress() throws SQLException {
        System.out.println("setAddress");
        String address = "34 Terror Road";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setAddress(address);
        
        assertEquals(address, emp.getAddress());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT address FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(address, rs.getString("address"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setS_name method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetS_name() throws SQLException {
        System.out.println("setS_name");
        String s_name = "Smith";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setS_name(s_name);
        
        assertEquals(s_name, emp.getS_name());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT last_name FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(s_name, rs.getString("last_name"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of setF_name method, of class Employee.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetF_name() throws SQLException {
        System.out.println("setF_name");
        String f_name = "Gabriella";
        Employee emp = new Employee("HH12", "Holly", "Hill", "64 Tank Road", "London", "United Kingdom", "E2 4TO", 
                "HH@talktalk.net", "02037667812", "Receptionist", "What is your pets name?", "Fluffy", "Password123");
        emp.setF_name(f_name);
        
        assertEquals(f_name, emp.getF_name());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT first_name FROM Employee WHERE employee_ID = '" + emp.getEmpID() + "'");
        if (rs.next()){
            assertEquals(f_name, rs.getString("first_name"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }
    
}
