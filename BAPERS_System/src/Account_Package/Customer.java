/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import Database_Package.DBConnectivity;
import Process_Package.Job;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Shows how to construct a Customer object and its associated variables and methods.
 * 
 * @author      Felice Gregorio
 * @version     (10/04/2018) version 3.2
 * @since       (10/03/2018) version 1
 */
public class Customer extends Account {
    /** The Customer ID. */
    private String custID;
    /** The type of account [Normal/Valued]. */
    private String acc_type;
    /** The next monthly payment date (Only for Valued Customers). */
    private Date nextPayment = null;
    /** The outstanding balance of the account (Only for Valued Customers). */
    private double outstandingBalance = 0;
    /** The status of the account [Active/Suspended/In Default] (Only for Valued Customers). */
    private String status = null;
    /** A flag identifying whether or not the customer has been deleted. */
    private boolean isDeleted = false;
    /** A list of monthly jobs awaiting payment (Only for Valued Customers). */
    private ArrayList<Job> monthlyJobs = new ArrayList<>();
    /** The discount plan associated to this account (Only for Valued Customers). */
    private DiscountPlan discount;
    /** The connectivity to the database.  */
    private final DBConnectivity myDB = new DBConnectivity();
    
    /**
    * Constructor for a new Customer object.
    * <p>
    * This is the constructor used when creating a new customer and introducing them to the system.
    * It is needed to create an instance of this class.
    * 
    * When an instance of this class is created, a connection to the database will be established and 
    * will insert a new record in the 'Customers' table.
    * 
    * @param id The generated customer ID.
    * @param f_name The customer's first name.
    * @param s_name The customer's surname.
    * @param address The customer's address.
    * @param city The city the customer lives in.
    * @param country The country the customer lives in.
    * @param postcode The customer's postcode.
    * @param email The customer's email address.
    * @param phone_num The customer's contact number.
    * 
    * 
    */
    public Customer(String id, String f_name, String s_name, String address, String city, 
                        String country, String postcode, String email, String phone_num) {
        super(f_name, s_name, address, city, country, postcode, email, phone_num);
        custID = id;
        acc_type = "Normal";
        
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("INSERT INTO Customers (customer_ID, first_name, last_name, "
                    + "address, city, country, postcode, email, phone_num, account_type) "
                    + "VALUES ('" + custID + "','" + f_name + "','" + s_name + "','" + address + "','" 
                    + city + "','" + country + "','" + postcode + "','" + email + "','" + phone_num + "','" + acc_type + "')");
            con.commit();
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        myDB.closeConnection();
    }
    
    /**
    * Constructor for a existing Customer object.
    * <p>
    * This is the constructor used when creating a customer object for an existing customer.
    * It is needed to create an instance of this class.
    * 
    * @param id The customer's ID.    * 
    */
    public Customer(String id) {
        super(id);
        this.custID = id;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            ResultSet rs = myDB.read("SELECT account_type, deleted FROM Customers WHERE customer_ID = '" + id + "'");
            while (rs.next()){
                acc_type = rs.getString("account_type");
                isDeleted = rs.getBoolean("deleted");
            }
            
            //initialises additional attributes associated to a valued customer account only.
            if (acc_type.equals("Valued")) {
                Date currentDate = Date.valueOf(LocalDate.now());
                myDB.connect();
                rs = myDB.read("SELECT * FROM ValuedCustomer WHERE customer_ID = '" + id + "'");
                ResultSet rs2 = myDB.read("SELECT job_code FROM Job INNER JOIN Invoice USING (invoice_ID) "
                                + "WHERE customer_ID = '" + id + "' AND payment_status = 'Pending' "
                                + "AND DATE(Invoice.date) BETWEEN '" + Date.valueOf(LocalDate.now().minusMonths(1)) + "' AND '" + currentDate + "'");
                ResultSet rs3 = myDB.read("SELECT discount_ID, type from DiscountPlan WHERE customer_ID = '" + custID + "'");
                while (rs.next()) {
                    nextPayment = rs.getDate("payment_due");
                    outstandingBalance = rs.getDouble("outstanding_balance");
                    status = rs.getString("status_of_account");
                }
                while (rs2.next()) {
                    monthlyJobs.add(new Job(rs2.getString("job_code")));
                }
                while (rs3.next()) {
                    if (rs3.getString("type").equals("Fixed")){
                        discount = new FixedDiscount(rs3.getInt("discount_ID"));
                    } else if (rs3.getString("type").equals("Flexible")){
                        discount = new FlexibleDiscount(rs3.getInt("discount_ID"));
                    } else if (rs3.getString("type").equals("Variable")){
                        discount = new VariableDiscount(rs3.getInt("discount_ID"));
                    }
                }
                con.commit();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
    }
    
    /**
    * Upgrade the customer account.
    * <p>
    * This function upgrades the customer account to a "Valued" customer account.
    * 
    * If attempting to upgrade a "Valued" customer account, an error message will appear.
    *
    * @param paymentDue The monthly payment date.
    * @param outPayment The outstanding balance.
    * @param status The status of the customer account [Active/Suspended/In Default].
    * @param jobs The list of monthly jobs waiting for payment.
    * 
    */
    public void upgradeAccount(Date paymentDue, double outPayment, String status, ArrayList<Job> jobs) {
        if ("Normal".equals(acc_type)){
            setAcc_type("Valued");
            nextPayment = paymentDue;
            outstandingBalance = outPayment;
            this.status = status;
            monthlyJobs = jobs;
            myDB.connect();
            Connection con = myDB.getConn();
            try {
                con.setAutoCommit(false);
                myDB.write("INSERT INTO ValuedCustomer (customer_ID, payment_due, outstanding_balance, status_of_account, firstletter_alert, secondletter_alert) "
                        + "VALUES ('" + custID + "','" + nextPayment + "','" + outstandingBalance + "','" + status + "', false, false)");
                con.commit();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                if (con != null) {
                    try {
                        System.err.print("Transaction rolling back!");
                        con.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            } finally {
                try {
                    con.setAutoCommit(true);
                    myDB.closeConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "This customer is already at the highest Account level!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
    * Downgrade the customer account.
    * <p>
    * This function downgrades the customer account to a "Normal" customer account.
    * 
    * If attempting to downgrade a "Normal" customer account, an error message will appear.
    * 
    */
    public void downgradeAccount() {
        if ("Valued".equals(acc_type)){
            setAcc_type("Normal");
            nextPayment = null;
            outstandingBalance = 0;
            status = null;
            monthlyJobs.clear();
            myDB.connect();
            Connection con = myDB.getConn();
            try {
                con.setAutoCommit(false);
                myDB.write("DELETE FROM ValuedCustomer WHERE customer_ID = '" + custID + "'");
                con.commit();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                if (con != null) {
                    try {
                        System.err.print("Transaction rolling back!");
                        con.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            } finally {
                try {
                    con.setAutoCommit(true);
                    myDB.closeConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "This customer is already at the lowest Account level!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
    * Delete Customer.
    * <p>
    * This function is used to delete the given customer from the database.
    * 
    */
    public void delete(){
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            ResultSet rs = myDB.read("SELECT customer_ID FROM Job WHERE customer_ID = '" + custID + "'");
            if (rs.next()){
                isDeleted = true;
                myDB.write("UPDATE Customers SET deleted = true WHERE customer_ID = '" + custID + "'");
            } else {
                myDB.write("DELETE FROM Customers WHERE customer_ID = '" + custID + "'");
            }
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
    * Assign Discount Plan.
    * <p>
    * This function is used to assign a discount plan object to this customer.
    * 
    * @param dp The discount plan to be assigned.
    */
    public void assignDiscountPlan(DiscountPlan dp) {
        discount = dp;
    }

    /**
    * Get Discount Plan.
    * <p>
    * This function retrieves the discount plan associated to this customer.
    * 
    * @return Returns the customer's discount plan.
    */
    public DiscountPlan getDiscountPlan() {
        return discount;
    }
    
    /**
    * Add a Job to the list of Monthly Jobs.
    * <p>
    * This function is used to add a job to the list of monthly jobs awaiting payment for a Valued Customer.
    * 
    * If attempting to add a job to a the list of monthly jobs for a Normal Customer, an error message will appear.
    * 
    * @param job The job to be added to the list.
    * 
    */
    public void addMonthlyJob(Job job) {
        if ("Valued".equals(acc_type)){
            monthlyJobs.add(job);
        } else {
            JOptionPane.showMessageDialog(null, "This customer is not a Valued Customer!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
    * Empty the list of Monthly Jobs.
    * <p>
    * This function empties the list of monthly jobs awaiting payment for a Valued Customer. 
    * 
    * If attempting to empty the list for a Normal Customer, an error message will appear.
    * 
    */
    public void emptyMonthlyJobs() {
        if ("Valued".equals(acc_type)){
            monthlyJobs.clear();
        } else {
            JOptionPane.showMessageDialog(null, "This customer is not a Valued Customer!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
    * Get Customer ID.
    * <p>
    * This function retrieves the customer ID and returns it.
    * 
    * @return Returns the customer's ID.
    */
    public String getCustID() {
        return custID;
    }
    
    /**
    * Get Account Type.
    * <p>
    * This function retrieves the account type [Normal/Valued] and returns it.
    * 
    * @return Returns the type of account.
    */
    public String getAcc_type() {
        return acc_type;
    }
    
    /**
    * Set Account Type.
    * <p>
    * This function changes the account type of the customer account and updates the account type stored on the database.
    * 
    * @param acc_type The account type to be set.
    */
    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE Customers SET account_type = '" + acc_type + "' WHERE customer_ID = '" + custID + "'");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
    * Set Phone Number.
    * <p>
    * This function changes the customer's phone number and updates the phone number stored on the database.
    * 
    * @param phone_num The contact number to be set.
    */
    @Override
    public void setPhone_num(String phone_num) {
        super.setPhone_num(phone_num);
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE Customers SET phone_num = '" + phone_num + "' WHERE customer_ID = '" + custID + "'");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
    * Set E-Mail.
    * <p>
    * This function changes the customer's e-mail and updates the e-mail stored on the database.
    * 
    * @param email The e-mail to be set.
    */
    @Override
    public void setEmail(String email) {
        super.setEmail(email);
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE Customers SET email = '" + email + "' WHERE customer_ID = '" + custID + "'");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
    * Set Postcode.
    * <p>
    * This function changes the customer's postcode and updates the postcode stored on the database.
    * 
    * @param postcode The e-mail to be set.
    */
    @Override
    public void setPostcode(String postcode) {
        super.setPostcode(postcode);
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE Customers SET postcode = '" + postcode + "' WHERE customer_ID = '" + custID + "'");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
    * Set Country.
    * <p>
    * This function changes the country that the customer lives in and updates the country stored on the database.
    * 
    * @param country The country to be set.
    */
    @Override
    public void setCountry(String country) {
        super.setCountry(country);
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE Customers SET country = '" + country + "' WHERE customer_ID = '" + custID + "'");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
    * Set City.
    * <p>
    * This function changes the city that the customer lives in and updates the city stored on the database.
    * 
    * @param city The city to be set.
    */
    @Override
    public void setCity(String city) {
        super.setCity(city);
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE Customers SET city = '" + city + "' WHERE customer_ID = '" + custID + "'");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
    * Set Address.
    * <p>
    * This function changes the customer's address and updates the address stored on the database.
    * 
    * @param address The address to be set.
    */
    @Override
    public void setAddress(String address) {
        super.setAddress(address);
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE Customers SET address = '" + address + "' WHERE customer_ID = '" + custID + "'");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
    * Set Surname.
    * <p>
    * This function changes the customer's surname and updates the surname stored on the database.
    * 
    * @param s_name The surname to be set.
    */
    @Override
    public void setS_name(String s_name) {
        super.setS_name(s_name);
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE Customers SET last_name = '" + s_name + "' WHERE customer_ID = '" + custID + "'");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
    * Set First Name.
    * <p>
    * This function changes the customer's first name and updates the first name stored on the database.
    * 
    * @param f_name The first name to be set.
    */
    @Override
    public void setF_name(String f_name) {
        super.setF_name(f_name);
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE Customers SET first_name = '" + f_name + "' WHERE customer_ID = '" + custID + "'");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
    * Get Payment Date.
    * <p>
    * This function retrieves the monthly payment date and returns it.
    * 
    * If attempting to retrieve the monthly payment date of a Normal Customer, and error will appear and return 'null'.
    * 
    * @return Returns the next monthly payment date.
    */
    public Date getNextPayment() {
        return nextPayment;
    }

    /**
    * Set next Payment Date.
    * <p>
    * This function changes the monthly payment date for which the Valued Customer needs to pay by, 
    * and updates the next payment date stored on the database.
    * 
    * If attempting to set the next payment date for a Normal Customer, an error message will appear.
    * 
    */
    public void setNextPayment() {
        if ("Valued".equals(acc_type)){
            LocalDate payDate = nextPayment.toLocalDate();
            this.nextPayment = Date.valueOf(payDate.plusMonths(1));
            myDB.connect();
            Connection con = myDB.getConn();
            try {
                con.setAutoCommit(false);
                myDB.write("UPDATE ValuedCustomer SET payment_due = '" + nextPayment + "' WHERE customer_ID = '" + custID + "'");
                con.commit();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                if (con != null) {
                    try {
                        System.err.print("Transaction rolling back!");
                        con.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            } finally {
                try {
                    con.setAutoCommit(true);
                    myDB.closeConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "This customer is not a Valued Customer!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
    * Get Outstanding Balance.
    * <p>
    * This function retrieves the outstanding balance associated to the Valued Customer account and returns it.
    * 
    * If attempting to retrieve the outstanding balance for a Normal Customer, an error will appear and return 0.
    * 
    * @return Returns the outstanding balance.
    */
    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    /**
    * Set Outstanding Balance.
    * <p>
    * This function changes the outstanding balance associated to the Valued Customer account, 
    * and updates the outstanding balance stored on the database.
    * 
    * If attempting to set the outstanding balance for a Normal Customer account, an error will appear.
    * 
    * @param outstandingBalance The outstanding balance to be set.
    */
    public void setOutstandingBalance(double outstandingBalance) {
        if ("Valued".equals(acc_type)){
            this.outstandingBalance = outstandingBalance;
            myDB.connect();
            Connection con = myDB.getConn();
            try {
                con.setAutoCommit(false);
                myDB.write("UPDATE ValuedCustomer SET outstanding_balance = '" + outstandingBalance + "' WHERE customer_ID = '" + custID + "'");
                con.commit();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                if (con != null) {
                    try {
                        System.err.print("Transaction rolling back!");
                        con.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            } finally {
                try {
                    con.setAutoCommit(true);
                    myDB.closeConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "This customer is not a Valued Customer!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
    * Get Account Status.
    * <p>
    * This function retrieve the account status [Active/Suspended/In Default] of a Valued Customer's account and returns it.
    * 
    * If attempting to retrieve the account status of a Normal Customer account, an error will appear and return 'null'.
    * 
    * @return Returns the account status.
    */
    public String getStatus() {
        return status;
    }

    /**
    * Set Account Status.
    * <p>
    * This function changes the account status of a Valued Customer Account and updates the account status stored on the database.
    * 
    * @param status The account status to be set.
    */
    public void setStatus(String status) {
        this.status = status;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE ValuedCustomer SET status_of_account = '" + status + "' WHERE customer_ID = '" + custID + "'");
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                con.setAutoCommit(true);
                myDB.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
    * Change Account Status.
    * <p>
    * This function changes the account status of a Valued Customer Account and updates the account status stored on the database.
    * 
    * If attempting to set the account status of a Normal Customer, an error will appear. 
    * A confirmation box will appear if attempting to change the status of an account marked as 'In Default'.
    * 
    * @param status The account status to be set.
    */
    public void changeStatus(String status) {
        if ("Valued".equals(acc_type)){
            if (status.equals(this.status)){
                JOptionPane.showMessageDialog(null, "This customer is already has this status!", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                if (this.status.equals("In Default")){
                    int option = JOptionPane.showConfirmDialog(null, "This account is marked as In Default. Are you sure you wish to change it?", "Attention!", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION){
                        setStatus(status);
                    }
                } else {
                    setStatus(status);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "This customer is not a Valued Customer!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
    * Get Monthly Jobs.
    * <p>
    * This function retrieves the list of monthly jobs, associated to a Valued Customer account, that are awaiting payment and returns it.
    * 
    * If attempting to retrieve the list of monthly jobs for a Normal Customer account, an error will appear and return 'null'.
    * 
    * @return Returns a list of monthly jobs awaiting payment
    */
    public ArrayList<Job> getMonthlyJobs() {
        return monthlyJobs;
    }

    /**
    * Set Monthly Jobs.
    * <p>
    * This function changes the list of monthly jobs, associated to a Valued Customer account, that are awaiting payment.
    * 
    * If attempting to set the list of monthly jobs of a Normal Customer account, an error will appear.
    * 
    * @param monthlyJobs The e-mail to be set.
    */
    public void setMonthlyJobs(ArrayList<Job> monthlyJobs) {
        if ("Valued".equals(acc_type)){
            this.monthlyJobs = monthlyJobs;
        } else {
            JOptionPane.showMessageDialog(null, "This customer is not a Valued Customer!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
