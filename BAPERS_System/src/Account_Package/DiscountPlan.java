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
 *Abstract discount plan class to be inherited by FixedDiscount, VariableDiscount and FlexibleDiscount.
 * 
 * @author Adem Aybar
 * @version     (30/03/2018) version 1.1
 * @since       (26/03/2018) version 1
 */
public abstract class DiscountPlan {
    /**The unique ID for a discount plan.*/
    protected int discountID;
    /**The ID for the customer this discount plan is assigned to.*/
    protected String customerID;
    /**The type of discount plan ("fixed","Variable","Flexible").*/
    protected String type;
    /**The database connection for this class*/
    protected DBConnectivity DB;

    /**
     * Deletes any discount plans for the same customer that currently exist.
     * @throws SQLException 
     */
    protected void deleteCurrentDiscountPlan() throws SQLException{
        
        DB = new DBConnectivity();
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);
            //get discountplan id
            ResultSet rs = DB.read("Select discount_ID FROM discountplan WHERE customer_ID = '"+ customerID + "'");
        
            //delete from discount plan table
            while(rs.next()){
                DB.write("DELETE FROM discountplan WHERE discount_ID = '"+ rs.getInt("discount_ID")+"'");

                switch (type) {
                    case "Fixed":
                        DB.write("DELETE FROM fixeddiscount WHERE discount_ID = '"+ rs.getInt("discount_ID")+"'");
                        break;
                    case "Variable":
                        DB.write("DELETE FROM variablediscount WHERE discount_ID = '"+ rs.getInt("discount_ID")+"'");
                        break;
                    case "Flexible":
                        DB.write("DELETE FROM flexiblediscountbands WHERE discount_ID = '"+ rs.getInt("discount_ID")+"'");
                        break;
                    default:
                        break;
                }
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
            con.setAutoCommit(true);
            DB.closeConnection();
        }
    }
    
    /**
     * Gets the discount ID.
     * @return ID for the discount plan
     */
    public int getDiscountID(){
        return discountID;
    }
    /**
     * Gets the customer ID.
     * @return ID for the customer to which this discount plan is assigned.
     */
    public String getCustomerID(){
        return customerID;
    }
}


