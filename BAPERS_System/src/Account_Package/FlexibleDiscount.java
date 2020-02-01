/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import Database_Package.DBConnectivity;
import Process_Package.Task;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adem Aybar
 * @version     (10/04/2018) version 1.5
 * @since       (04/04/2018) version 1
 */
public class FlexibleDiscount extends DiscountPlan {
  
    //a collection of the different discount bands in this plan.
    private ArrayList<DiscountBand> discountBands;
    
    /**
     * Constructor for when a new flexible discount plan is made.
     * 
     * @param discountBands an ArrayList of type DiscountBand(inner class of Flexible discount).
     * @param customerID Id for the customer this discount plan belongs to.
     * @throws java.sql.SQLException
     */
    public FlexibleDiscount(ArrayList<DiscountBand> discountBands, String customerID) throws SQLException{
        
        this.discountBands = new ArrayList<>();
        
        for(DiscountBand band : discountBands){
            this.discountBands.add(band);
        }
        
        super.customerID = customerID;
        super.type = "Flexible";
        
        //delete any old discount plans
        super.deleteCurrentDiscountPlan();
        
        //Database Connectivity
        DB = new DBConnectivity();
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);
            
            //generate discount ID
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
            
            //insert into Flexible discount table.
            for(DiscountBand band : discountBands){
                DB.write("INSERT INTO flexiblediscountbands (discount_ID, lower_limit, upper_limit, discount_rate) VALUES (" + discountID + "," + band.getLowerLimit() + "," + band.getUpperLimit()+ "," + band.getDiscountRate() + ")");
                con.commit();
            }
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
     * Constructor for when an existing discount plan is read from the database.
     * <p>
     * The discount ID is used to pull data for an existing discount plan from the database.
     * </p>
     * @param discountID The ID for the discount plan.
     * @throws java.sql.SQLException
     */
    public FlexibleDiscount(int discountID) throws SQLException{
        
        super.discountID = discountID;
        super.type = "Flexible";
        
        //connect to database
        DB = new DBConnectivity();
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);

            // get discount Bands
            ResultSet rs = DB.read("SELECT * FROM flexiblediscountbands WHERE discount_ID = " + discountID);
     
            //initilise arraylist
            discountBands = new ArrayList<>();
        
            //assign tasks discount rates to ArrayList.
            while (rs.next()){
           
                discountBands.add(new DiscountBand(rs.getInt("lower_limit"),rs.getInt("upper_limit"),rs.getDouble("discount_rate")));
            }
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
     * Applies this Discount plan to a given value.
     * .
     * @param before the price of job before discount or VAT is applied.
     * @return the price of job after discount, according to this discount plan, is applied.
     * @throws java.sql.SQLException 
     */
    public double applyDiscount(double before) throws SQLException{
        
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);

            //get current date
            String currentDate = new Date(System.currentTimeMillis()).toString();
            // Splits date from YYYY-MM-DD into array date[0] = YYYY date[1] = MM date[3] = DD
            String date[] = currentDate.split("-");
            //get price of jobs from this month
            ResultSet rs = DB.read("SELECT price FROM job WHERE customer_ID = '"+ customerID +"' AND DATE(accepted) BETWEEN '"+ date[0] + "-"+date[1]+"-01' AND '" + currentDate +"'");

            //Add price of jobs for this month
            double totalPrice = 0;
            while(rs.next()){
                totalPrice+= rs.getDouble("price");
            }

            //select appropriate discount based on band
            double currentDiscount =0;

            for(DiscountBand band : discountBands){
                if(totalPrice >= band.getLowerLimit() && totalPrice <= band.getUpperLimit()) currentDiscount = band.getDiscountRate();
            }

            //apply discount to input value
            double after = before -(before * currentDiscount);

            con.commit();
            return after;
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
        return 0;
    }
    
    /**
     * Gets the array list of discount bands.
     * @return return an ArrayList of the discount bands in this discount plan.
     */
    public ArrayList<DiscountBand> getDiscountBands(){
        return discountBands;
    }
}
