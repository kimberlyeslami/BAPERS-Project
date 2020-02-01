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
 *A Payment made by credit/debit card.
 * 
 * @author Adem Aybar
 * @version     (25/03/2018) version 1
 * @since       (25/03/2018) version 1
 */
public class CardPay extends Payment{
    String cardNum;
    /**The last four digits of card number*/
    int cvcCode;
    /**The type of card*/
    String cardType;
    /**The expiry date of card*/
    String expiryDate;
    
    /**
     * Constructor for a new card payment.
     * 
     * <p>
     * New card payment details are accepted and stored to the database.
     * </p>
     * @param jobCode A job this payment is made on.
     * @param typeOfPayment type of payment.
     * @param amount payment amount.
     * @param cardNum Th card number
     * @param cvcCode Card cvc number.
     * @param cardType Type of card.
     * @param expiryDate Expiry date.
     * @throws java.sql.SQLException
     */
    public CardPay(String jobCode, String typeOfPayment, float amount, String cardNum, int cvcCode, String cardType, String expiryDate) throws SQLException {
        super(jobCode, amount);
        
        DB = new DBConnectivity();
        
        this.cardNum = cardNum;
        this.cvcCode =cvcCode;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        super.typeOfPayment = "Card";
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);

            //update the payment table
            DB.write("UPDATE payment SET type_of_payment = '" + typeOfPayment + "' WHERE payment_ID =" + paymentID);
            con.commit();
            //write to card payment table
            DB.write("INSERT INTO `card payment` (payment_ID, card_num, cvc, card_type, expiry_date) VALUES (" + paymentID + ", '" + cardNum + "', " + cvcCode + ", '" + cardType + "', '" + expiryDate + "')");
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
     * Constructor to receive a current card payment from the database.
     * <p>
     * The payment id is used to pull data for a current card payment and construct this class.
     * </p>
     * @param paymentID Unique payment ID.
     * @throws java.sql.SQLException
     */
    public CardPay(int paymentID) throws SQLException {
        super(paymentID);
        
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);

            ResultSet rs = DB.read("SELECT * FROM `card payment` WHERE payment_ID = " + paymentID);
            rs.first();

            this.cardNum = rs.getString("card_num");
            this.cvcCode = rs.getInt("cvc");
            this.cardType = rs.getString("card_type");
            this.expiryDate = rs.getString("expiry_date");
            
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
    * Gets the last four digits of a card number.
    * @return last four digits of card number.
    */
    public int getCvcCode(){
        return cvcCode;
    }
    
    /**
     * Gets the card type.
     * @return Type of Card.
     */
    public String getCardType(){
        return cardType;
    }
    
    /**
     * Gets the expiry date.
     * @return Expiry Date of card.
     */
    public String getExpiryDate(){
        return expiryDate;
    } 
}
