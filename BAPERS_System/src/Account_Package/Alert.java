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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Shows how to construct a Alert object and its associated variables and methods.
 * 
 * @author      Felice Gregorio
 * @version     (06/04/2018) version 2.3
 * @since       (20/03/2018) version 1
 */
public class Alert {
    /** The Database connectivity. */
    private final DBConnectivity myDB = new DBConnectivity();
    /** Flag used to check if there are any alerts. */
    private boolean hasAlerts = false;
    /** Flag used to indicate whether the alerts have been updated. */
    private boolean updatedAlerts = false;
    /** Results sets used to check if there are any alerts. */
    private ResultSet jobRS, lateTaskRS, latePayRS, letterRS, deadlineRS, priorityRS;
    
    /**
    * Constructor for a new Alert object.
    * <p>
    * This is the constructor used when creating a new Alert to display.
    * It is needed to create an instance of this class.
    * 
    */
    public Alert() {}
    
    /**
    * Generate alert for a New Job.
    * <p>
    * This function generates and displays an alert for the arrival of a new job.
    * 
    * @throws java.sql.SQLException 
    */
    public void genNewJobAlert() throws SQLException{
        String jobCode;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            
            //Generates new Job alerts
            while (jobRS.next()){
                jobCode = jobRS.getString("job_code");
                JOptionPane.showMessageDialog(null, "New Job " + jobCode + " has arrived!", "Attention!", JOptionPane.INFORMATION_MESSAGE);
                myDB.write("UPDATE Job SET job_alert = false WHERE job_code = '" + jobCode + "'");
                con.commit();
            }  
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
            con.setAutoCommit(true);
        }
        
    }
    
    /**
    * Generate alert for a potentially late completion time of a Task.
    * <p>
    * This function generates and displays an alert for a task belonging to a particular job, 
    * that is likely to exceed the set time period.
    * 
    * @throws java.sql.SQLException 
    */
    public void genLateTaskAlert() throws SQLException{
        int taskID;
        String jobCode;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            
            //Generates alerts for tasks that are likely to exceed their set time period
            while (lateTaskRS.next()){
                taskID = lateTaskRS.getInt("task_id");
                jobCode = lateTaskRS.getString("job_code");
                JOptionPane.showMessageDialog(null, "Expected time for Task " + taskID
                        + " in Job " + jobCode + " is likely to exceed the set time period!",
                        "Attention!", JOptionPane.INFORMATION_MESSAGE);
                myDB.write("UPDATE JobTasks SET late_alert = false WHERE job_code = '" + jobCode + "' AND task_ID = '" + taskID + "'");
                con.commit();
            }
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
            con.setAutoCommit(true);
        }
    }
    
    /**
    * Generate alert for a late payment.
    * <p>
    * This function generates and displays an alert for late payment of a customer.
    * 
    * @throws java.sql.SQLException 
    */
    public void genLatePayAlert() throws SQLException{
        int invoiceID;
        String custID;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            
            //Generates alerts for late payments
            ResultSet rs;
            while (latePayRS.next()){
                invoiceID = latePayRS.getInt("invoice_ID");
                rs = myDB.read("SELECT DISTINCT customer_ID FROM Job WHERE invoice_ID = '" + invoiceID + "'");
                while (rs.next()){
                    custID = rs.getString("customer_ID");
                    JOptionPane.showMessageDialog(null, "Customer " + custID + " is now late for payment of Invoice " + invoiceID + "!", "Attention!", JOptionPane.INFORMATION_MESSAGE);
                }
                myDB.write("UPDATE Invoice SET late_alert = false WHERE invoice_ID = '" + invoiceID + "'");
                con.commit();
            }
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
            con.setAutoCommit(true);
        }
    }
    
    /**
    * Generate alert for the generation of a reminder letter.
    * <p>
    * This function generates and displays an alert when a reminder letter has been generated for a customer.
    * 
    * @throws java.sql.SQLException 
    * 
    */
    public void genLetterAlert() throws SQLException{
        String custID;
        myDB.connect();
        Connection con = myDB.getConn();
        
        try {
            con.setAutoCommit(false);
            
            //Generates alerts for reminder letters
            while (letterRS.next()){
                if (letterRS.getBoolean("firstletter_alert") == true){
                    custID = letterRS.getString("customer_ID");
                    JOptionPane.showMessageDialog(null, "Customer " + custID + " has been sent the First Reminder Letter.", "Attention!", JOptionPane.INFORMATION_MESSAGE);
                    myDB.write("UPDATE ValuedCustomer SET firstletter_alert = false WHERE customer_ID = '" + custID + "'");
                }
                if (letterRS.getBoolean("secondletter_alert") == true){
                    custID = letterRS.getString("customer_ID");
                    JOptionPane.showMessageDialog(null, "Customer " + custID + " has been sent the Second Reminder Letter.", "Attention!", JOptionPane.INFORMATION_MESSAGE);
                    myDB.write("UPDATE ValuedCustomer SET secondletter_alert = false WHERE customer_ID = '" + custID + "'");
                }
                con.commit();
            }   
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
            con.setAutoCommit(true);
        }
    }
    
    /**
    * Generate alert for the jobs with approaching deadlines.
    * <p>
    * This function generates and displays an alert when a deadline is approaching for a job.
    * 
    * @throws java.sql.SQLException 
    * 
    */
    public void genDeadlineApproachingAlert() throws SQLException{
        String jobCode;
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        myDB.connect();
        Connection con = myDB.getConn();
        
        try {
            con.setAutoCommit(false);
            
            if (!updatedAlerts){
                myDB.write("UPDATE Job SET deadline_alert = true WHERE DATE(deadline) BETWEEN '" + currentDate.minusDays(3).format(dateFormat) + "' AND '" + currentDate.format(dateFormat) + "'");
                con.commit();
                updatedAlerts = true;
            }

            //Generates deadline approaching alerts
            while (deadlineRS.next()){
                jobCode = deadlineRS.getString("job_code");
                JOptionPane.showMessageDialog(null, "Job " + jobCode + " has an approaching deadline!", "Attention!", JOptionPane.INFORMATION_MESSAGE);
                myDB.write("UPDATE Job SET deadline_alert = false WHERE job_code = '" + jobCode + "'");
                con.commit();
            }
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
            con.setAutoCommit(true);
        }
    }
    
    /**
    * Generate alert for when a priority has been changed.
    * <p>
    * This function generates and displays an alert when a status of a task/job.
    * 
    * @throws java.sql.SQLException 
    * 
    */
    public void genPriorityChangedAlert() throws SQLException{
        String jobCode;
        String priority;
        int taskID;
        myDB.connect();
        Connection con = myDB.getConn();
        
        try {
            con.setAutoCommit(false);
            
            //Generates new Job alerts
            while (priorityRS.next()){
                jobCode = priorityRS.getString("job_code");
                taskID = priorityRS.getInt("task_ID");
                priority = priorityRS.getString("priority");
                JOptionPane.showMessageDialog(null, "Task " + taskID + " in Job " + jobCode + " has now changed to " + priority + " priority!", "Attention!", JOptionPane.INFORMATION_MESSAGE);
                myDB.write("UPDATE JobTasks SET priorityChanged_alert = false WHERE job_code = '" + jobCode + "' AND task_ID = '" + taskID + "'");
            }
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
            con.setAutoCommit(true);
        }
    }
    
    /**
    * Check for available alerts.
    * <p>
    * This function is used to check if there are any available alerts, needed to be displayed for the office manager.
    * 
    * @return Returns 'True' if there are pending alerts, waiting to be displayed. 'False' if otherwise.
    * @throws java.sql.SQLException 
    * 
    */
    public boolean checkAlerts() throws SQLException{
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            
            jobRS = myDB.read("SELECT job_code FROM Job WHERE job_alert = true");
            lateTaskRS = myDB.read("SELECT job_code, task_ID FROM JobTasks WHERE late_alert = true");
            latePayRS = myDB.read("SELECT invoice_ID FROM Invoice WHERE late_alert = true");
            letterRS = myDB.read("SELECT customer_ID, firstletter_alert, secondletter_alert FROM ValuedCustomer WHERE firstletter_alert = true OR secondletter_alert = true");
            deadlineRS = myDB.read("SELECT job_code FROM Job WHERE deadline_alert = true");
            priorityRS = myDB.read("SELECT job_code, task_ID, priority FROM JobTasks WHERE priorityChanged_alert = true");
            
            if (jobRS.next() || lateTaskRS.next() || latePayRS.next() || letterRS.next() || deadlineRS.next() || priorityRS.next()){
                jobRS.beforeFirst();
                lateTaskRS.beforeFirst();
                latePayRS.beforeFirst();
                letterRS.beforeFirst();
                deadlineRS.beforeFirst();
                priorityRS.beforeFirst();
                hasAlerts = true;
            }
            
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
            con.setAutoCommit(true);
        }
        
        return hasAlerts;
    }

    public DBConnectivity getMyDB() {
        return myDB;
    }
}
