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
 *A Payment made to the system and associated details.
 * 
 * @author Adem Aybar
 * @version     (01/04/2018) version 1.2
 * @since       (24/03/2018) version 1
 */
public class Payment {
    
    /**The database connection for this object.*/
    protected DBConnectivity DB;  
    /**Unique ID For The payment.*/
    protected int  paymentID;
    /**Type of Payment.*/
    protected String typeOfPayment;
    /**Amount Paid.*/
    private double amount;
    /**ID for Associated Invoice*/
    private int invoiceID;
    
    /**
     * Constructor for when a new payment is made.
     * 
     * <p>
     * The class is constructed with given parameters for type of payment and amount.
     * A unique ID is generated and the payment is stored to the database.
     * </p>
     * 
     * @param jobCode The ID for a job this payment is made for.
     * @param amount The amount paid.
     * @throws java.sql.SQLException
     */
    public Payment(String jobCode,  double amount) throws SQLException {
        
        DB = new DBConnectivity();
        
        this.amount = amount;
        this.typeOfPayment = "Cash";
        
        //find invoice
        DB.connect();
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);
            ResultSet rs = DB.read("SELECT invoice_ID FROM job WHERE job_code = '" + jobCode + "'");
            rs.first();
            invoiceID = rs.getInt("invoice_ID");


            //update invoice to paid
            Invoice i = new Invoice(invoiceID);
            i.markPaid();

            //Generate payment ID
            rs = DB.read("SELECT payment_ID FROM payment ORDER BY payment_ID DESC LIMIT 1");
            try {
                paymentID = 1;
                while (rs.next()){
                    paymentID = rs.getInt("payment_ID") + 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
            }

            DB.write("INSERT INTO payment (payment_ID, type_of_payment, amount, invoice_ID) VALUES (" + paymentID + ", '" + typeOfPayment + "', " + amount +", " + invoiceID + ")");
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
     * Constructor For when a payment is read from the database.
     * 
     * <p>
     * A paymetID is used to find a old payment in the database an construct the class based on the stored data.
     * </p>
     * 
     * @param paymentID Unique ID for this payment.
     * @throws java.sql.SQLException
     */
    public Payment(int paymentID) throws SQLException{
       
        DB = new DBConnectivity();
        DB.connect();
        Connection con = DB.getConn();
        
        this.paymentID = -1;
        this.typeOfPayment = "Not found";
        this.amount = -1;
        this.invoiceID = -1;
        try {
            con.setAutoCommit(false);

            ResultSet rs = DB.read("SELECT * FROM payment WHERE payment_ID = " + paymentID);
        
            rs.first();
        
            this.paymentID = paymentID;
            this.typeOfPayment = rs.getString("type_of_payment");
            this.amount = rs.getFloat("amount");
            this.invoiceID = rs.getInt("invoice_ID");
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
     * Get the ID for this invoice.
     * @return the unique ID for invoice for this payment.
     */
    public int getInvoice(){
        return invoiceID;
    }
    
    /**
     * Gets the payment ID.
     * @return ID for this payment.
     */
    public int getPaymentID(){
        return paymentID;
    }
    
    /**
     * Gets the type of payment.
     * @return Type of payment.
     */
    public String getTypeOfPayment(){
        return typeOfPayment;
    }
    /**
     * Gets the Amount.
     * @return payment amount.
     */
    public double getAmount(){
        return amount;
    }
}
