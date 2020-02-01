/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process_Package;

import Database_Package.DBConnectivity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *An enquiry made by a customer which can be responded to.
 * 
 * @author Adem Aybar
 * @version     (25/03/2018) version 1.1
 * @since       (22/03/2018) version 1
 */
public class Enquiry {
    
    /**The unique id for this enquiry.*/
    int enquiryID;
    /**The enquiry made by the customer.*/
    String enquiry;
    /**A response to the enquiry made by an employee.*/
    String response;
    /** The ID for the job this enquiry is made on.*/
    String jobID;
    /**The database connection for this object.*/
    DBConnectivity DB;
    
    /**
     * Constructor for making a new enquiry.
     * 
     * <p>
     * A new enquiry is made and its information save to the database.
     * </p>
     * 
     * @param enquiry an enquiry made by a customer on a specific job
     * @param job_code The ID for the job this enquiry is made about.
     */
    public Enquiry(String enquiry, String job_code) throws SQLException{
        
        this.enquiry = enquiry;
        this.jobID = job_code;
        DB = new DBConnectivity();
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            
            con.setAutoCommit(false);
            
            ResultSet rs = DB.read("SELECT enquiry_ID FROM enquiry ORDER BY enquiry_ID DESC LIMIT 1");
            try {
                enquiryID =  1;
                while (rs.next()){
                    enquiryID = rs.getInt("payment_ID") + 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
            }

            DB.write("INSERT INTO enquiry (enquiry_ID, comment, job_code) VALUES (" + enquiryID + ", '" + enquiry + "', '" + jobID + "')");
            con.commit();
        }catch (SQLException ex) {
	
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            con.setAutoCommit(true);
            DB.closeConnection();
        }  
    }
    /**
     * Constructor for retrieving a current enquiry.
     * 
     * <p>
     * Data is read from the database for a current enquiry and that data is used to construct the object.
     * </p>
     * @param enquiryID the unique ID for the wanted enquiry.
     */
    public Enquiry(int enquiryID){
        
        DB = new DBConnectivity();
    
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);

            ResultSet rs =DB.read("SELECT * FROM enquiry WHERE enquiry_id = " + enquiryID);
            try {
                rs.first();
                this.enquiryID = enquiryID;
                this.enquiry = rs.getString("comment");
                this.response = rs.getString("response");
                this.jobID = rs.getString("job_code");
            } catch (SQLException ex) {
                Logger.getLogger(Enquiry.class.getName()).log(Level.SEVERE, null, ex);
            }
            con.commit();
        }catch (SQLException ex) {
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Enquiry.class.getName()).log(Level.SEVERE, null, ex);
            }
            DB.closeConnection();
        }   
    }
    
    /**
     * Respond to an enquiry.
     * @param response Staff response to customer enquiry.
     */
    public void respond(String response) throws SQLException{
        this.response = response;
        
        DB.connect();
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);
            DB.write("UPDATE enquiry SET response = '" + response + "' WHERE enquiry_id = " + enquiryID);
            con.commit();
        }catch (SQLException ex) {
	
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }finally {
            con.setAutoCommit(true);
            DB.closeConnection();
        }
    }
    
    /**
     * Gets the Enquiry ID.
     * @return The unique ID for this enquiry.
     */
    public int getEnquiryID(){
      return enquiryID;  
    }
    
    /**
     * Gets the enquiry.
     * @return customer enquiry.
     */
    public String getEnquiry(){
        return enquiry;
    }
    
    /**
     * Gets the response.
     * @return staff response to enquiry.
     */
    public String getResponse(){
        return response;
    }
}
