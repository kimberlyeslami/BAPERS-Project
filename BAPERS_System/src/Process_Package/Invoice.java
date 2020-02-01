/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process_Package;

import Account_Package.Customer;
import Account_Package.FixedDiscount;
import Account_Package.FlexibleDiscount;
import Account_Package.VariableDiscount;
import Database_Package.DBConnectivity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**An Invoice For A payment.
 *
 * @author Adem Aybar
 * @version     (04/04/2018) version 1.2
 * @since       (25/03/2018) version 1
 */
public class Invoice {
    
    /** A unique ID for the invoice*/
    private int invoiceID;
    /**Date and time the invoice is created*/
    private Timestamp date;
    /** Value added Tax */
    private final float VAT = 0.2f;
    /* Total before VAT */
    private double subTotal;
    /**price after discount*/
    private double afterDiscount;
    /** Total after VAT*/
    private double total;
    /** Paid or not paid*/
    private String paymentStatus;
    /**customer ID*/
    private String customerID;
    /**Late Alert*/
    private boolean lateAlert;
    /**Database Connection*/
    private DBConnectivity DB;
    
    /**
     * Constructor for a new Invoice.
     * <p>
     * Creates a new invoice object, adding data to the database as its created.
     * </p>
     * @param date date invoice is created.
     * @param subTotal total vale before discount or VAT.
     * @param jobID the id of the job which creates this invoice.
     * @param customerID the id of the customer this invoice is for.
     * @throws java.sql.SQLException
     */
    public Invoice(Timestamp date, double subTotal, String jobID,String customerID) throws SQLException{
        
        this.date = date;
        this.subTotal = subTotal;
        this.paymentStatus = "Pending";
        //Database Connectivity
        DB = new DBConnectivity();
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);
            //find discount plan
            //get customerID
            ResultSet rs =DB.read("SELECT customer_ID from job WHERE job_code = '" + jobID + "'");
            rs.first();

            //check for valued
            rs = DB.read("SELECT account_type FROM customers WHERE customer_ID = '" + customerID + "'");
            rs.first();
            //if not valued
            if(rs.getString("account_type").equals("Normal")) afterDiscount = subTotal ;
            //if valued
            else{
                //find discountplan
                rs = DB.read("SELECT type, discount_ID FROM discountplan WHERE customer_ID = '"+ customerID + "'");
                rs.first();
                String discountType = rs.getString("type");
                int discountID = rs.getInt("discount_ID");
                //calculate Discount
                switch (discountType) {
                    case "Fixed":
                        //apply fixed discount
                        FixedDiscount fixed = new FixedDiscount(discountID);
                        afterDiscount = fixed.applyDiscount(subTotal);
                        break;
                    case "Variable":
                        //apply variable discount
                        VariableDiscount variable = new VariableDiscount(discountID);
                        Job job = new Job(jobID);
                        Task[] tasks = job.retriveTasks();
                        afterDiscount = 0;
                        //For each task
                        for(Task task : tasks){
                            //calculate discount on individual task
                            double discountedTask = variable.applyDiscount(task.getTaskID());
                            //Add to total
                            afterDiscount += discountedTask;
                        }
                        break;
                    case "Flexible":
                        //apply flexible discount
                        FlexibleDiscount flexible = new FlexibleDiscount(discountID);
                        afterDiscount = flexible.applyDiscount(subTotal);
                        break;
                    default:
                        break;
                }
            }
            //Calculate Total
            this.total = afterDiscount + (afterDiscount * VAT);
            //round total to 2 decimal places
            this.total = Math.round(this.total * 100.0) / 100.0;


            // generate invoice ID
            rs = DB.read("SELECT invoice_ID FROM invoice ORDER BY invoice_ID DESC LIMIT 1");
            try {
                invoiceID = 1;
                while (rs.next()){
                    invoiceID = rs.getInt("invoice_ID") + 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            //Insert into invoice table
            DB.write("INSERT INTO invoice VALUES (" + invoiceID + ", '" + date + "'," + subTotal + "," + total +",'" + paymentStatus +"', 0)");
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
     * Constructor for an invoice being read from the database.
     * <p>
     * uses an invoice ID to pull an existing invoice from the database.
     * </P
     * @param invoiceID The ID of the wanted Invoice.
     * @throws java.sql.SQLException
     */
    public Invoice(int invoiceID) throws SQLException{
        
        this.invoiceID = invoiceID;
        
        //Database Connectivity
        DB = new DBConnectivity();
        DB.connect();
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);

            //Pull details from database
            ResultSet rs = DB.read("SELECT * FROM invoice WHERE invoice_ID = " + invoiceID);
            rs.first();
            //initialise values from database
            this.date = rs.getTimestamp("date");
            this.subTotal = rs.getDouble("sub-total");
            this.total = rs.getDouble("totalCost");
            this.paymentStatus = rs.getString("payment_status");
            this.lateAlert = rs.getBoolean("late_alert");

            rs =DB.read("SELECT customer_ID from job WHERE invoice_ID = " + invoiceID);
            rs.first();
            customerID = rs.getString("customer_ID");
            
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
     * Add a job to this invoice.
     * <p>
     * Adds a new job to this invoice, calculating a new total cost and adding this new information to the database.
     * </p>
     * 
     * @param jobID ID/job_code for the job being added to this invoice.
     * @throws java.sql.SQLException
     */
    public void addJob(String jobID) throws SQLException{
        
        //calculate and set new subTotal
        subTotal += new Job(jobID).getPrice() ;
        
        //calculate new total
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);
            //check for valued
            ResultSet rs = DB.read("SELECT customer_ID FROM valuedcustomer WHERE customer_ID = '" + customerID + "'");
            //if not valued
            if(!(rs.next())){ 
                afterDiscount = subTotal;
                
            //if valued
            } else{
                //find discountplan
                rs = DB.read("SELECT type, discount_ID FROM discountplan WHERE customer_ID = '"+ customerID + "'");
                rs.first();
                String discountType = rs.getString("type");
                int discountID = rs.getInt("discount_ID");
                //calculate Discount
                switch (discountType) {
                    case "Fixed":
                        //apply fixed discount
                        FixedDiscount fixed = new FixedDiscount(discountID);
                        afterDiscount = fixed.applyDiscount(subTotal);
                        break;
                    case "Variable":
                        //apply variable discount
                        VariableDiscount variable = new VariableDiscount(discountID);
                        Job job = new Job(jobID);
                        Task[] tasks = job.retriveTasks();
                        afterDiscount = 0;
                        //For each task
                        for(Task task : tasks){
                            //calculate discount on individual task
                            double discountedTask = variable.applyDiscount(task.getTaskID());
                            //Add to total
                            afterDiscount += discountedTask;
                        }
                        break;
                    case "Flexible":
                        //apply flexible discount
                        FlexibleDiscount flexible = new FlexibleDiscount(discountID);
                        afterDiscount = flexible.applyDiscount(subTotal);
                        break;
                    default:
                        break;
                }
            }
            this.total = afterDiscount + (afterDiscount * VAT);
    
            // update database
            DB.write("UPDATE invoice SET `sub-total` = " + subTotal + ",totalCost = " + total + " WHERE invoice_ID = " + invoiceID);
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
     * Marks the invoice paid.
     * 
     * <p>
     * updates the invoice status to "Paid".
     * </p>
     */
    public void markPaid() throws SQLException{
        this.paymentStatus = "Paid";
        
        
        //update customer
        Customer cust = new Customer(this.customerID);
        if(cust.getAcc_type().equals("Valued") || cust.getAcc_type().equals("Late")){
            
            String currentDate = new Date(System.currentTimeMillis()).toString();
            // Splits date from YYYY-MM-DD into array date[0] = YYYY date[1] = MM date[3] = DD
            String date[] = currentDate.split("-");
            
            int nextYear = Integer.parseInt(date[0]);
            int nextMonth = Integer.parseInt(date[1]);
            
            if (nextMonth == 12){
                nextYear ++;
                nextMonth = 1;
            }else nextMonth++;
            
            cust.setNextPayment();
            
            if(cust.getAcc_type().equals("Late")) {
                
                double newOutstandingBalance = cust.getOutstandingBalance() - total;
                cust.setOutstandingBalance(newOutstandingBalance);
            }
        }
        //update table.
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);
            DB.write("UPDATE invoice SET payment_status = '"+ paymentStatus + "' WHERE invoice_ID = " + invoiceID);
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
     * Marks this invoice as a late payment
     * <p>
     * updates the invoice status to "Late".
     * </p>
     */
    public void markLate() throws SQLException{
        //update table.
        DB.connect();
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);
            DB.write("UPDATE invoice SET late_alert = 1 WHERE invoice_ID = " + invoiceID);
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
     * Gets the invoice id.
     * 
     * @return the unique identifier for this invoice
     */
    public int getInvoiceID(){
        return invoiceID;
    }
    /**
     * Gets the date of this invoice.
     * 
     * @return the date time this invoice was created.
     */
    public Timestamp getDate(){
        return date;
    }
    /**
     * Returns late alert.
     * 
     * @return if the invoice is late or not.
     */
    public boolean getLateAlert(){
        return lateAlert;
    }

    /**
     * Gets sub-total of the invoice.
     * 
     * @return Retruns the invoice sub-total.
     */
    public double getSubTotal() {
        return subTotal;
    }

    /**
     * Gets the total cost (inc VAT) of the invoice.
     * 
     * @return Returns the total cost (inc VAT).
     */
    public double getTotal() {
        return total;
    }

    /**
     * Gets the status of the invoice [Pending/Paid/Late].
     * 
     * @return Returns the payment status for the invoice.
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }
}
