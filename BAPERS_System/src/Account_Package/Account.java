/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import Database_Package.DBConnectivity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Shows how to construct an Account object and its associated variables and methods.
 * 
 * @author      Felice Gregorio
 * @version     (29/03/2018) version 2.1
 * @since       (10/03/2018) version 1
 */
public class Account {
    /** The customer's First Name. */
    private String f_name;
    /** The customer's Surname. */
    private String s_name;
    /** The customer's Address. */
    private String address;
    /** The City the customer lives in. */
    private String city;
    /** The Country the customer lives in. */
    private String country;
    /** The customer's Postcode. */
    private String postcode;
    /** The customer's e-mail. */
    private String email;
    /** The customer's Contact Number. */
    private String phone_num;

    /**
    * Constructor for a new Account object.
    * <p>
    * This is the constructor used when creating a new Account.
    * It is needed to create an instance of an Employee and Customer Account.
    * 
    * @param f_name The customer or employee first name.
    * @param s_name The customer or employee surname.
    * @param address The customer or employee address.
    * @param city The city the customer or employee lives in.
    * @param country The country the customer or employee lives in.
    * @param postcode The customer or employee postcode.
    * @param email The customer or employee email address.
    * @param phone_num The customer or employee contact number.
    * 
    */
    public Account(String f_name, String s_name, String address, String city, 
                    String country, String postcode, String email, String phone_num) {
        this.f_name = f_name;
        this.s_name = s_name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
        this.email = email;
        this.phone_num = phone_num;
    }
    
    /**
    * Constructor for an existing Account object.
    * <p>
    * This is the constructor used when creating an Account object for an already existing account.
    * It is needed to create an instance of an existing Employee and Customer Account.
    * 
    * @param id The customer or employee ID.
    * 
    */
    public Account(String id){
        ResultSet rs;
        DBConnectivity myDB = new DBConnectivity();
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            if (id.startsWith("ACC")){
                rs = myDB.read("SELECT * FROM Customers WHERE customer_ID = '" + id + "'");
                while (rs.next()){
                    f_name = rs.getString("first_name");
                    s_name = rs.getString("last_name");
                    address = rs.getString("address");
                    city = rs.getString("city");
                    country = rs.getString("country");
                    postcode = rs.getString("postcode");
                    email = rs.getString("email");
                    phone_num = rs.getString("phone_num");
                }
            } else {
                rs = myDB.read("SELECT * FROM Employee WHERE employee_ID = '" + id + "'");
                while(rs.next()){
                    f_name = rs.getString("first_name");
                    s_name = rs.getString("last_name");
                    address = rs.getString("address");
                    city = rs.getString("city");
                    country = rs.getString("country");
                    postcode = rs.getString("postcode");
                    email = rs.getString("email");
                    phone_num = rs.getString("phone_num");
                }
            }
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
    * Get First Name.
    * <p>
    * This function retrieves the customer or employee first name.
    * 
    * @return Returns the customer or employee first name.
    * 
    */
    public String getF_name() {
        return f_name;
    }

    /**
    * Set First Name.
    * <p>
    * This function sets the customer or employee first name.
    * 
    * @param f_name The first name to be set.
    */
    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    /**
    * Get Surname.
    * <p>
    * This function retrieves the customer or employee surname.
    * 
    * @return Returns the customer or employee surname.
    * 
    */
    public String getS_name() {
        return s_name;
    }

    /**
    * Set Surname.
    * <p>
    * This function sets the customer or employee surname.
    * 
    * @param s_name The surname to be set.
    */
    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    /**
    * Get Address.
    * <p>
    * This function retrieves the customer or employee address.
    * 
    * @return Returns the customer or employee address.
    * 
    */
    public String getAddress() {
        return address;
    }

    /**
    * Set Address.
    * <p>
    * This function sets the customer or employee address.
    * 
    * @param address The address to be set.
    */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
    * Get City.
    * <p>
    * This function retrieves the city that the customer or employee lives in.
    * 
    * @return Returns the city the customer or employee lives in.
    * 
    */
    public String getCity() {
        return city;
    }

    /**
    * Set City.
    * <p>
    * This function sets the city the customer or employee lives in.
    * 
    * @param city The city to be set.
    */
    public void setCity(String city) {
        this.city = city;
    }

    /**
    * Get Country.
    * <p>
    * This function retrieves the country that the customer or employee lives in.
    * 
    * @return Returns the country the customer or employee lives in.
    * 
    */
    public String getCountry() {
        return country;
    }

    /**
    * Set Country.
    * <p>
    * This function sets the country the customer or employee lives in.
    * 
    * @param country The country to be set.
    */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
    * Get Postcode.
    * <p>
    * This function retrieves the customer or employee postcode.
    * 
    * @return Returns the customer or employee postcode.
    * 
    */
    public String getPostcode() {
        return postcode;
    }

    /**
    * Set Postcode.
    * <p>
    * This function sets the customer or employee postcode.
    * 
    * @param postcode The postcode to be set.
    */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
    * Get E-mail.
    * <p>
    * This function retrieves the customer or employee e-mail.
    * 
    * @return Returns the customer or employee e-mail.
    * 
    */
    public String getEmail() {
        return email;
    }

    /**
    * Set E-Mail.
    * <p>
    * This function sets the customer or employee e-mail.
    * 
    * @param email The e-mail to be set.
    */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
    * Get Phone Number.
    * <p>
    * This function retrieves the customer or employee contact Number.
    * 
    * @return Returns the customer or employee contact Number.
    * 
    */
    public String getPhone_num() {
        return phone_num;
    }

    /**
    * Set Phone Number.
    * <p>
    * This function sets the customer or employee contact number.
    * 
    * @param phone_num The contact number to be set.
    */
    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }    
}
