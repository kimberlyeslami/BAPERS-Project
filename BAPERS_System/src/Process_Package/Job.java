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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *A Job which has been accepted by the system.
 * 
 * @author       Adem Aybar
 * @version     (29/03/2018) version 1.3
 * @since       (22/03/2018) version 1
 */
public class Job {

    /** ID for this job. */
    private String jobID;
    /** Special instructions from the customer for this job. */
    private String specialInstruction;
    /** The priority of this job "Normal"/"Urgent". */
    private String priority;
    /** The deadline for the job to be completed. */
    private Timestamp deadline;
    /** When the job was accepted. */
    private Timestamp accepted;
    /** The value of the job before VAT or any discount. */
    private Double price;
    /** The status of the job. */
    private String status;
    /** The ID for the customer who has requested this job. */
    private String customerID;
    /**ID for the invoice which contains this job. */
    private int invoiceID;
    /**If the office manager has been alerted. */
    private boolean alerted;
    /**list of enquiries made on this job. */
    private ArrayList<Enquiry> enquiries = new ArrayList<>();
    /** A list of the tasks to be carried out as part of this job. */
    private final ArrayList<Task> listOfTasks;
    /**The database connection for this job. */
    private DBConnectivity DB; 
    /**alert for when deadline is reached. */
    private boolean deadlineAlert;
    /**
     * Constructor for when a new Job is created.
     * 
     * <p>
     * A new job is created with contained tasks, and the jobs details are added to and invoice.
     * </p>
     * 
     * @param specialInstruction Special instructions from the customer for this job.
     * @param accepted Time and date the job is accepted.
     * @param priority  priority of the job.
     * @param customerID ID for the customer who requested this job.
     * @param deadline Deadline for the job to be completed.
     * @param tasks Tasks to be completed as part of this job.
     * @throws java.sql.SQLException
     */
    public Job (String specialInstruction,String priority, Timestamp accepted,Timestamp deadline,String customerID,ArrayList<Task> tasks) throws SQLException{
        
        DB = new DBConnectivity();
        
        this.specialInstruction = specialInstruction;
        this.priority = priority;
        this.accepted = accepted;
        this.deadline = deadline;
        this.status = "Pending";
        this.customerID = customerID;
        this.alerted = true;
        this.deadlineAlert = false;
        
        this.listOfTasks = new ArrayList<>();
        //transfer tasks into arraylist
        listOfTasks.addAll(tasks);
        
        //get price
        this.price = 0.0;
        for(Task task : tasks){
            this.price += task.getPrice();
        }
        
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);
            
            /*generate job_code*/

            ResultSet rs = DB.read("SELECT job_code FROM job");
            boolean idTaken;    

            String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";  //Possible random letters.
            String numbers = "0123456789";                  //Possible random numbers.
            String randomString = "";                       //Final Random string.
            Random r = new Random();                        //Java Random object.
            int letterLength = 2;                           //Amount of random letters.
            int numLength = 4;                              //Amount of random numbers.

            do{
                //sets id to not taken.
                idTaken = false;
                //add random letters to array
                char[] randomLetters = new char[letterLength];
                for(int i = 0; i < letterLength; i++){
                    randomLetters[i] = letters.charAt(r.nextInt(letters.length()));
                }
                //add random numbers to array
                char[] randomNumbers = new char[numLength];
                for(int i = 0; i < numLength; i++){
                    randomNumbers[i] = numbers.charAt(r.nextInt(numbers.length()));
                }
                //Add letters to string
                for(int i = 0; i < randomLetters.length ; i ++){
                    randomString += randomLetters[i];
                }
                //add numbers to string
                for(int i = 0; i < randomNumbers.length ; i ++){
                    randomString += randomNumbers[i];
                }
                //set string as job id
                this.jobID = randomString;
                //checks database for id.
                while (rs.next()){
                    if(rs.getString("job_code").equals(this.jobID)) idTaken = true;
                }
            }while(idTaken); //loops if id is taken

            //Check for / make new appropriate invoice. 
            rs = DB.read("SELECT Customer_ID from valuedcustomer WHERE customer_ID = '" + customerID + "'");

            //check for valued customer
            if(rs.next()){
                //if valued customer.

                //gets current date
                String currentDate = new Date(System.currentTimeMillis()).toString();
                // Splits date from YYYY-MM-DD into array date[0] = YYYY date[1] = MM date[3] = DD
                String date[] = currentDate.split("-");
                 //check if appropriate invoice exists
                rs = DB.read("SELECT customer_ID, invoice_ID, DATE(Invoice.date) as 'Date' FROM Job INNER JOIN Invoice USING (invoice_ID) " +
                            "WHERE customer_ID = '" + customerID + "' AND payment_status = 'Pending' AND DATE(Invoice.date) BETWEEN '"+ date[0]+"-"+date[1]+"-01' AND '" + currentDate + "'");

                if(rs.next()){
                    //an invoice exists
                    this.invoiceID = rs.getInt("invoice_ID");
                    
                    //insert value into database.
                    DB.write("INSERT INTO job (job_code, special_instruction, priority, price, accepted, deadline, status, customer_ID, invoice_ID, job_alert, deadline_alert)"
                            + "VALUES ('" + jobID + "', '" + specialInstruction + "', '" + priority + "', " + price + ", '" + accepted + "', '" + deadline + "', '" + status + "', '"
                            + customerID +"', "+ invoiceID +","+ alerted + "," + deadlineAlert + ")");
                    
                    con.commit();
                    
                    //adds job to the existing invoice    
                    Invoice i = new Invoice(invoiceID);
                    i.addJob(jobID);
                }else{
                    //no appopriate invoice exists
                    Invoice i;
                    i = new Invoice(new Timestamp(System.currentTimeMillis()), price, jobID, customerID);
                    invoiceID = i.getInvoiceID();
                    
                    //insert value into database.
                    DB.write("INSERT INTO job (job_code, special_instruction, priority, price, accepted, deadline, status, customer_ID, invoice_ID, job_alert, deadline_alert)"
                            + "VALUES ('" + jobID + "', '" + specialInstruction + "', '" + priority + "', " + price + ", '" + accepted + "', '" + deadline + "', '" + status + "', '"
                            + customerID +"', "+ invoiceID +","+ alerted + "," + deadlineAlert + ")");
                }
            }else{
                //gets current date
                String currentDate = new Date(System.currentTimeMillis()).toString();
                // Splits date from YYYY-MM-DD into array date[0] = YYYY date[1] = MM date[3] = DD
                String date[] = currentDate.split("-");
                 //check if appropriate invoice exists
                rs = DB.read("SELECT customer_ID, invoice_ID, DATE(Invoice.date) as 'Date' FROM Job INNER JOIN Invoice USING (invoice_ID) " +
                            "WHERE customer_ID = '" + customerID + "' AND payment_status = 'Pending' AND DATE(Invoice.date) BETWEEN '"+ date[0]+"-"+date[1]+"-01' AND '" + currentDate + "'");

                if(rs.next()){
                    //an invoice exists
                    this.invoiceID = rs.getInt("invoice_ID");
                    
                    //insert value into database.
                    DB.write("INSERT INTO job (job_code, special_instruction, priority, price, accepted, deadline, status, customer_ID, invoice_ID, job_alert, deadline_alert)"
                            + "VALUES ('" + jobID + "', '" + specialInstruction + "', '" + priority + "', " + price + ", '" + accepted + "', '" + deadline + "', '" + status + "', '"
                            + customerID +"', "+ invoiceID +","+ alerted + "," + deadlineAlert + ")");
                    
                    con.commit();
                    
                    //adds job to the existing invoice    
                    Invoice i = new Invoice(invoiceID);
                    i.addJob(jobID);
                }else{
                    //no appopriate invoice exists
                    Invoice i;
                    i = new Invoice(new Timestamp(System.currentTimeMillis()), price, jobID, customerID);
                    invoiceID = i.getInvoiceID();
                    
                    //insert value into database.
                    DB.write("INSERT INTO job (job_code, special_instruction, priority, price, accepted, deadline, status, customer_ID, invoice_ID, job_alert, deadline_alert)"
                            + "VALUES ('" + jobID + "', '" + specialInstruction + "', '" + priority + "', " + price + ", '" + accepted + "', '" + deadline + "', '" + status + "', '"
                            + customerID +"', "+ invoiceID +","+ alerted + "," + deadlineAlert + ")");
                }
            }
             
            //instert into jobtasks
            for(Task t : listOfTasks){
                DB.write("INSERT INTO jobtasks(job_code, task_ID, priority ,status, late_alert, priorityChanged_alert) VALUES ('"+ jobID + "'," + t.getTaskID() + ",'" + this.priority + "','" + t.getStatus(jobID) + "', 0, 0)");
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
        } finally {
            con.setAutoCommit(true);
            DB.closeConnection();
        }  
    }
    
    /**
     * Constructor for an existing Job. 
     * <p>
     * An existing job is pulled from the database using the job ID, constructing the object with stored values.
     * </p>
     * @param id The unique ID for the desired job.
     * @throws java.sql.SQLException
     */
      public Job(String id) throws SQLException{
          
        DB  = new DBConnectivity();
        //task details added
        listOfTasks = new ArrayList<>();
        
        DB.connect();
        
        Connection con = DB.getConn();
        try {
            con.setAutoCommit(false);

            //Job details added
            ResultSet rs = DB.read("SELECT * FROM job WHERE job_code = '" + id + "'");
        
            rs.first();
            this.jobID =id;
            this.specialInstruction = rs.getString("special_instruction");
            this.priority = rs.getString("priority");
            this.price = rs.getDouble("price");
            this.accepted = rs.getTimestamp("accepted");
            this.deadline = rs.getTimestamp("deadline");
            this.status = rs.getString("status");
            this.customerID = rs.getString("customer_ID");
            this.invoiceID = rs.getInt("invoice_ID");
            this.alerted = rs.getBoolean("job_alert");
            this.deadlineAlert = rs.getBoolean("deadline_alert");

            rs = DB.read("SELECT task_ID FROM jobtasks WHERE job_code = '" + jobID + "'");
            while (rs.next()){

                listOfTasks.add(new Task(rs.getInt("task_ID")));
            }

            //Enquiry details added.
            rs = DB.read("SELECT enquiry_ID FROM enquiry WHERE job_code = '" + jobID + "'");

            while (rs.next()){
                enquiries.add(new Enquiry((rs.getInt("enquiry_ID"))));
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
     * Returns tasks.
     * 
     * <p>
     * The tasks contained in the job are moved into an array and returned. 
     * </p>
     * @return Array of all Tasks included in this job.
     **/
    public Task[] retriveTasks(){
        
        // creates an array to size
        Task[] tasks = new Task[listOfTasks.size()];
        int i=0;
        for(Task t : listOfTasks){
           
            tasks[i] = t;
            
            i++;
        }
        return tasks;
    }
    
    /** 
     * Updates status of the job to started.
     * <p>
     * Updates job status to "Active".
     * </p>
     **/
    public void started() throws SQLException{
        if(this.status.equals("Pending")){
           this.status = "Active";
           DB.connect();
           
           Connection con = DB.getConn();
           try {
                con.setAutoCommit(false);

                DB.write("UPDATE job SET status = '" + this.status + "' WHERE job_code = '" + jobID + "'");
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
    }
    
    /**
     * Updates status of job to completed.
     * <p>
     * The job status is updated to "Completed".
     * </p>
     * @throws java.sql.SQLException
     */
    public void completed() throws SQLException{
        if(this.status.equals("Active")){
           
            this.status = "Completed";
            DB.connect();
           
            Connection con = DB.getConn();
            try {
                con.setAutoCommit(false);
                DB.write("UPDATE job SET status = '" + this.status + "' WHERE job_code = '" + jobID + "'");
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
    }
    
    /**
     * Makes an enquiry for the current job.
     * <p>
     * A new enquiry is added to the current job, adding the data to the class and database.
     * </p>
     * 
     * @param comment enquiry made by customer on this job.
     */
    public void makeEnquiry(String comment) throws SQLException{
        enquiries.add(new Enquiry(comment,jobID));   
    }
    
    /**
     * Gets the job ID.
     * @return The unique identifier for this job.
     */
    public String getJobID(){
      return jobID;  
    }
    
    /**
     * Gets the special instructions.
     * @return specialInstructions made by the customer.
     */
    public String getSpecialInstruction(){
        return specialInstruction;
    }
    
    /**
     * Get priority.
     * @return Priority of the job.
     */
    public String getPriority(){
        return priority;
    }
    
    /**
     * Gets the deadline.
     * @return deadline for completion.
     */
    public Timestamp getDeadline(){
        return deadline;
    }
    
    /**
     * gets the time and date accepted.
     * @return Time accepted (YYYY-MM-DD).
     */
    public Timestamp getAccepted(){
        return accepted;
    }
    
    /**
     * Gets the price.
     * @return Price of job before and discount or VAT.
     */
    public double getPrice(){
        return price;
    }
    /**
     * Get customer ID.
     * @return The ID of the customer who place this job.
     */
    public String getCustomerID(){
        return customerID;
    }
    /**
     * Get invoice ID.
     * @return ID of associated Invoice.
     */
    public int getInvoiceID(){
        return invoiceID;
    }
    /**
     * Get enquiry by ID.
     * 
     * @param enquiryID the ID for the enquiry you want to select.
     * @return enquiry object for this job.
     */
    public Enquiry getEnquiry(int enquiryID){
        
        
        for(Enquiry e : enquiries){
            
            if(e.getEnquiryID() == enquiryID) return e;
            
        }
        return null; 
    }
    
    /**
     * Get alert.
     * @return if the manager has been alerted of the new job.
     */
    public boolean getAlerted(){
        return alerted;
    }
    
    /**
     * Get all enquiries.
     * @return list of all enquiries
     */
    public ArrayList<Enquiry> getListOfEnquiries(){
        return enquiries;
    }
    
    /**
     * Get job status.
     * @return Returns the status of the job.
     */
    public String getStatus(){
        return status;
    }
    
    /**
     * Get job tasks.
     * @return Returns the list of tasks associated to this job.
     */
    public ArrayList<Task> getTasks(){
        return listOfTasks;
    }
}
