/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import Database_Package.DBConnectivity;
import System_Package.Writer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Shows how to construct a Reminder Letter object and its associated variables and methods.
 * 
 * @author      Felice Gregorio
 * @version     (29/03/2018) version 2.1
 * @since       (20/03/2018) version 1
 */
public class ReminderLetter {
    /** BIPL Contact Details.  */
    private String biplContact;
    /** The Customer ID.  */
    private String custID;
    /** The Customer Name.  */
    private String custName;
    /** The Customer Address.  */
    private String custAddress;
    /** The Invoice ID the reminder letter is generated for.  */
    private int invoiceID;
    /** The list of jobs associated with the invoice.  */
    private ArrayList<String> jobCodes;
    /** The total cost of the invoice.  */
    private double cost;
    /** The date of the invoice.  */
    private Date invoiceDate;
    /** The current date.  */
    private LocalDate currentDate;
    /** The Format dates should be in. */
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    /** The letter message.  */
    private String message;
    /** The connectivity to the database.  */
    private final DBConnectivity myDB = new DBConnectivity();
    /** The alert to be generated.  */
    private Alert alert = new Alert();

    /**
    * Constructor for a new Reminder Letter object.
    * <p>
    * This is the constructor used when creating a new Reminder Letter for a customer.
    * It is needed to create an instance of this class.
    * 
    * @param custID The customer ID of the customer for which the reminder letter is generated for.
    * @param invoiceID The invoice ID for the invoice the reminder letter is referring to.
    * 
    */
    public ReminderLetter(String custID, int invoiceID) {
        this.custID = custID;
        this.invoiceID = invoiceID;
        currentDate = LocalDate.now();
        jobCodes = new ArrayList<>();
        biplContact = "The Lab\nBloomsbury's Image Processing Laboratory\n2, Wynytt Street, London, EC1V 7HU\n"
                      + "Phone: 020 7235 7534";
        this.message = "";
        
        myDB.connect();
        
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            ResultSet rs = myDB.read("SELECT first_name, last_name, address, city, country, postcode FROM Customers WHERE customer_ID = '" + custID + "'");
            ResultSet rs2 = myDB.read("SELECT job_code, Invoice.date, totalCost FROM Job INNER JOIN Invoice USING (invoice_ID) WHERE invoice_ID = '" + invoiceID + "'");
            try {
                while (rs.next()) {
                    custName = rs.getString("first_name") + " " + rs.getString("last_name");
                    custAddress = custName + ",\n" + rs.getString("address") + ",\n" +  rs.getString("city") + ",\n" 
                                + rs.getString("country") + ",\n" + rs.getString("postcode");
                }

                while (rs2.next()) {
                    jobCodes.add(rs2.getString("job_code"));
                    cost = rs2.getDouble("totalCost");
                    invoiceDate = rs2.getDate("date");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ReminderLetter.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }    
    }

    /**
    * Generate first reminder letter.
    * <p>
    * This function generates the first reminder letter for a customer and writes the letter to a file. 
    * 
    */
    public void generateFirstLetter(){
        message = "Dear " + custName + ",\n" +
                "According to our records, it appears that we have not yet received payment of the above invoice, "
                + "which was posted to you on " + new SimpleDateFormat("dd MMMM yyyy").format(invoiceDate) + ", for photographic work in out laboratory.\n" +
                "We would appreciate payment at your earliest convenience.\n" +
                "If you have already sent a payment to us recently, please accept our apologies.\n" +
                "Yours Sincerely,\n"
                + "G.Lancaster";
        
        String firstLetter = custAddress + "\n\n" + biplContact + "\n\n" + currentDate.format(dateFormat) + "\n\n" 
                + "REMINDER - Invoice No. " + invoiceID + "\n" + "Job Code(s): ";
        
        for (int i = 0; i < jobCodes.size(); ++i){
            if (i == 0){
                firstLetter += jobCodes.get(i);
            } else {
                firstLetter += ", " + jobCodes.get(i);
            }
        }
        
        firstLetter += " Total Amount: " + cost + "\n\n" + message;
        
        myDB.connect();
        
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE ValuedCustomer SET firstletter_alert = true, firstletter_date = CURRENT_TIMESTAMP WHERE customer_ID = '" + custID + "'");
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
                Logger.getLogger(ReminderLetter.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        } 
        
        Writer writer = new Writer(custID + "_FirstReminder.txt");
        writer.writeLetter(firstLetter);
    }
    
    /**
    * Generate second reminder letter.
    * <p>
    * This function generates the second reminder letter for a customer and writes the letter to a file. 
    * 
    */
    public void generateSecondLetter(){
        message = "Dear " + custName + ",\n" +
                "It appears that we still have not received payment of the above invoice, "
                + "which was posted to you on " + new SimpleDateFormat("dd MMMM yyyy").format(invoiceDate) + ", for photographic work in out laboratory, "
                + "despite a reminder letter posted to you 1 month later.\n" +
                "Unless you pay the outstanding amount in full within SEVEN DAYS, or contact us with proposals for repayment, "
                + "we will have no option but to refer the matter to out solicitor.\n" +
                "Please send payment immediately to avoid further action.\n" +
                "Yours Sincerely,\n"
                + "G.Lancaster";
        
        String secondLetter = custAddress + "\n\n" + biplContact + "\n\n" + currentDate.format(dateFormat) + "\n\n" 
                + "REMINDER - Invoice No. " + invoiceID + "\n" + "Job Code(s): ";
        
        for (int i = 0; i < jobCodes.size(); ++i){
            if (i == 0){
                secondLetter += jobCodes.get(i);
            } else {
                secondLetter += ", " + jobCodes.get(i);
            }
        }
        
        secondLetter += " Total Amount: " + cost + "\n\n" + message;
        
        myDB.connect();
        
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE ValuedCustomer SET secondletter_alert = true, secondletter_date = CURRENT_TIMESTAMP WHERE customer_ID = '" + custID + "'");
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
                Logger.getLogger(ReminderLetter.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
        
        Writer writer = new Writer(custID + "_SecondReminder.txt");
        writer.writeLetter(secondLetter);
    }

    /**
    * Get BIPL Contact information.
    * <p>
    * This function retrieves the contact information for BIPL.
    * 
    * @return Returns the BIPL contact information.
    * 
    */
    public String getBiplContact() {
        return biplContact;
    }

    /**
    * Set BIPL Contact information.
    * <p>
    * This function sets/changes the contact information for BIPL.
    * 
    * @param biplContact The BIPL contact details to be set.
    * 
    */
    public void setBiplContact(String biplContact) {
        this.biplContact = biplContact;
    }

    /**
    * Get reminder letter message.
    * <p>
    * This function retrieves the message in the reminder letter.
    * 
    * @return Returns the reminder letter message.
    * 
    */
    public String getMessage() {
        return message;
    }

    /**
    * Set reminder letter message.
    * <p>
    * This function sets/changes the message in the reminder letter.
    * 
    * @param message The message to be set.
    * 
    */
    public void setMessage(String message) {
        this.message = message;
    }
}
