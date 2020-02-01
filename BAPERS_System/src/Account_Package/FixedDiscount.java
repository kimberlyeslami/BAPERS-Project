/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import Database_Package.DBConnectivity;
import Process_Package.Task;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *A fixed discount plan
 * 
 * @author Adem Aybar
 * @version (30/02/2018)  Version 1
 * @since   (30/02/2018)  Version 1
 */
public class FixedDiscount extends DiscountPlan{
    
    /** The fixed discount to be applied*/
    private double discount;
    
    
    /**
     * Constructor for making a new Fixed discount plan.
     * <p>
     * parameters are passed into the constructor to construct the variables into the class. This data is then saved to the database.
     * </P>
     * @param discount the value of the discount.
     * @param customerID the ID of the customer you want to assign this discount to.
     * @throws java.sql.SQLException 
     */
    public FixedDiscount(double discount, String customerID) throws SQLException{
       
        this.discount = discount;
        super.customerID = customerID;
        super.type = "Fixed";
        
        //delete any old discount plans
        super.deleteCurrentDiscountPlan();
        //Database Connectivity
        DB = new DBConnectivity();
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            
            con.setAutoCommit(false);
            
            // generate discount ID
            ResultSet rs = DB.read("SELECT discount_ID FROM discountplan ORDER BY discount_ID DESC LIMIT 1");
            try {
                discountID = 1;
                while (rs.next()){
                    discountID = rs.getInt("discount_ID") + 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Insert into discountplan table
            DB.write("INSERT INTO discountplan (discount_ID, type, customer_ID) VALUES (" + discountID + ",'" + type + "','" + customerID + "')");

            //insert into fixed discount table.
            DB.write("INSERT INTO fixeddiscount (discount_ID, discount_rate) VALUES (" + discountID + "," + discount + ")");
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
            //Close database connection
            DB.closeConnection();
        }
    }
    
    /**
     * Constructor to read a current fixed discount plan from the database.
     * <p>
     * The discount ID is used to read data of a current discount plan from the database.
     * </p>
     * @param discountID the unique id of this discount.
     * @throws java.sql.SQLException 
     */
    public FixedDiscount(int discountID) throws SQLException{
        
        //assign ID
        super.discountID = discountID;
        super.type = "Fixed";
        
        DB = new DBConnectivity();
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);

            // get discount rate
            ResultSet rs = DB.read("SELECT discount_rate FROM fixeddiscount WHERE discount_ID = " + discountID);
            rs.first();
            //assign discount rate
            discount = rs.getDouble("discount_rate");

            //get customer ID
            rs = DB.read("SELECT customer_ID FROM discountplan WHERE discount_ID = " + discountID);
            rs.first();
            super.customerID = rs.getString("customer_ID");

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
     * Applies the discount to a given value.
     * 
     * @param before the price before discount is applied.
     * @return price after discount is applied.
     */
    public double applyDiscount(double before){
        
        double after;
        after = before -(before * discount);
        return after;
    }
   
    /**
     * Gets the discount rate.
     * @return the discount value
     */
    public double getDiscount(){
        return discount;
    }
}
