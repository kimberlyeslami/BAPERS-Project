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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Adem Aybar
 * @version     (12/04/2018) version 2
 * @since       (31/03/2018) version 1
 */
public class VariableDiscount extends DiscountPlan {

/**Hash map storing tasks and discounts*/
HashMap<Integer, Double> taskDiscounts;

/**
 * Constructor for creating a new Variable discount plan.
 * <p>
 * The constructor takes two arrays on for tasks and one for discounts these arrays must be of equal length with a task being a the same index to its corresponding discount.
 * </p>
 * @param taskDiscounts
 * @param customerID ID for the customer to which this discount plan belongs.
 * @throws java.sql.SQLException
 */
public VariableDiscount(HashMap<Integer, Double> taskDiscounts, String customerID) throws SQLException{

    super.customerID = customerID;
    super.type = "Variable";
    
    //new hashmap
    this.taskDiscounts = new HashMap<>();
    //add all values
    this.taskDiscounts.putAll(taskDiscounts);
  
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
    
        while (rs.next()){
            discountID = 1;
            discountID = rs.getInt("discount_ID") + 1;
        }
        
        //Insert into discountplan table
        DB.write("INSERT INTO discountplan (discount_ID, type, customer_ID) VALUES (" + discountID + ",'" + type + "','" + customerID + "')");
        
        //insert into variable discount table.
        for (Map.Entry<Integer,Double> pair: taskDiscounts.entrySet()) {
                DB.write("INSERT INTO variablediscount VALUES (" + discountID + "," + pair.getKey() + "," + pair.getValue() + ")");
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
        //Close database connection
        DB.closeConnection();
    }
}

/**
 * Constructor for when an existing discount plan is read from the database.
 * <p>
 * The discount ID is used to pull data for an existing discount plan from the database.
 * </p>
 * @param discountID The ID for the variable discount plan.
 * @throws java.sql.SQLException
 */
public VariableDiscount(int discountID) throws SQLException{
    //assign ID
        super.discountID = discountID;
        super.type = "Variable";
        
        DB = new DBConnectivity();
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);

            // get tasks discounts
            ResultSet rs = DB.read("SELECT task_ID, discount_rate FROM variablediscount WHERE discount_ID = " + discountID);

            //get size of resultset
            int size =0;
            while (rs.next()){
                size++;
            }
            //initilise hashmap
            this.taskDiscounts = new HashMap<>();

            //reset result set
            rs.beforeFirst();
            //assign tasks discount rates to map

            while (rs.next()){

               taskDiscounts.put(rs.getInt("task_ID"), rs.getDouble("discount_rate"));
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
 * Applies this discount to a given task.
 * 
 * <p>
 * This function will search the defined discount plan for a discount for the task and apply that to the cost of that task as found in the database. 
 * If no discount for the given task is found it will return -1.
 * </p>
 * @param taskID The ID for the task.
 * @return task cost with discount applied.
 * @throws java.sql.SQLException
 */
public double applyDiscount(int taskID) throws SQLException{
    
    double after = 0;
    DB.connect();
    
    Connection con = DB.getConn();
    try {
            con.setAutoCommit(false);

            //get initial cost of task
            ResultSet rs = DB.read("SELECT price FROM task WHERE task_ID = " + taskID);
            rs.first();
            double before = rs.getDouble("price");
            //find task in array
            double discount = taskDiscounts.get(taskID);
            //apply set discount
            after = before -(before *discount);
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
    
    //return new price
    return after;
}

public HashMap<Integer,Double>getTaskDiscounts(){
    return this.taskDiscounts;
}
}
