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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Shows how to construct an Employee object and its associated variables and methods.
 * 
 * @author      Felice Gregorio
 * @version     (29/03/2018) version 2.2
 * @since       (10/03/2018) version 1
 */
public class Employee extends Account {
    /** The Employee ID. */
    private final String empID;
    /** The Employee Role. */
    private String role;
    /** The security question associated to the employee account. */
    private String securityQuestion;
    /** The answer to the security question. */
    private String securityAnswer;
    /** The account password. */
    private String password;
    /** The connectivity to the database.  */
    private final DBConnectivity myDB = new DBConnectivity();

    /**
    * Constructor for a new Employee object.
    * <p>
    * This is the constructor used when creating a new employee and introducing them to the system.
    * It is needed to create an instance of this class.
    * 
    * When an instance of this class is created, a connection to the database will be established and 
    * will insert a new record in the 'Employee' table.
    * 
    * @param empID
    * @param f_name The employee's first name.
    * @param s_name The employee's surname.
    * @param address The employee's address.
    * @param city The city the employee lives in.
    * @param country The country the employee lives in.
    * @param postcode The employee's postcode.
    * @param email The employee's email address.
    * @param phone_num The employee's contact number.
    * @param role The employee's job role.
    * @param securityQuestion The account security question.
    * @param securityAnswer The answer to the security question.
    * @param password The account password.
    * 
    */
    public Employee(String empID, String f_name, String s_name, String address, String city, 
                    String country, String postcode, String email, String phone_num, 
                    String role, String securityQuestion, String securityAnswer, String password) {
        super(f_name, s_name, address, city, country, postcode, email, phone_num);
        Random rand = new Random();
        this.empID = empID;
        this.role = role;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.password = password;
        myDB.connect();
        
        Connection con = myDB.getConn();
        try {
            
            con.setAutoCommit(false);
            
            myDB.write("INSERT INTO Employee (employee_ID, job_role, first_name, last_name, "
                    + "address, city, country, postcode, email, phone_num, password, security_question, security_answer) "
                    + "VALUES ('" + empID + "','" + role + "','" + f_name + "','" + s_name + "','" + address + "','" 
                    + city + "','" + country + "','" + postcode + "','" + email + "','" + phone_num + "','" 
                    + password + "','" + securityQuestion + "','" + securityAnswer + "')");
            con.commit();
        }catch (SQLException ex){
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }  
    }

    /**
    * Constructor for a existing Employee object.
    * <p>
    * This is the constructor used when creating an employee object for an existing employee.
    * It is needed to create an instance of this class.
    * 
    * @param empID The employee ID.
    * @throws java.sql.SQLException
    * 
    */
    public Employee(String empID) throws SQLException {
        super(empID);
        this.empID = empID;
        
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false); 
            
            ResultSet rs = myDB.read("SELECT * FROM Employee WHERE employee_ID = '" + empID + "'");
            while(rs.next()){
                role = rs.getString("job_role");
                securityQuestion = rs.getString("security_question");
                securityAnswer = rs.getString("security_answer");
                password = rs.getString("password");
            }
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
        }finally {
            con.setAutoCommit(true);
            myDB.closeConnection();
        }
    }

    
    /**
    * Get Employee ID.
    * <p>
    * This function retrieves the employee ID and returns it.
    * 
    * @return Returns the employee's ID.
    */
    public String getEmpID() {
        return empID;
    }
    
    /**
    * Get Job role.
    * <p>
    * This function retrieves the employee's job role and returns it.
    * 
    * @return Returns the employee's job role.
    */
    public String getRole() {
        return role;
    }

    /**
    * Set Job Role.
    * <p>
    * This function sets the employee's job role.
    * 
    * @param role The job role to be set.
    */
    public void setRole(String role) {
        this.role = role;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            myDB.write("UPDATE Employee SET job_role = '" + role + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
             myDB.closeConnection();
        } 
    }

    /**
    * Get account Security Question.
    * <p>
    * This function retrieves the employee's security question associated to their account and returns it.
    * 
    * @return Returns the employee's security question.
    */
    public String getSecurityQuestion() {
        return securityQuestion;
    }

    /**
    * Set Security Question.
    * <p>
    * This function sets the employee's account security question.
    * 
    * @param securityQuestion The security question to be set.
    */
    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            myDB.write("UPDATE Employee SET security_question = '" + securityQuestion + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }   
    }

    /**
    * Get Security Question Answer.
    * <p>
    * This function retrieves the answer to the employee's security question and returns it.
    * 
    * @return Returns the answer to the employee's security question.
    */
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    /**
    * Set Security Question Answer.
    * <p>
    * This function sets the answer to the employee's account security question.
    * 
    * @param securityAnswer The answer to the security question to be set.
    */
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE Employee SET security_answer = '" + securityAnswer + "' WHERE employee_ID = '" + empID + "'");
            con.commit();
        }catch (SQLException ex) {
            
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }  
    }

    /**
    * Get Password.
    * <p>
    * This function retrieves the password to the employee's account and returns it.
    * 
    * @return Returns the password to the employee's account.
    */
    public String getPassword() {
        return password;
    }

    /**
    * Set Password.
    * <p>
    * This function sets the password to the employee's account.
    * 
    * @param password The password to be set.
    */
    public void setPassword(String password) {
        this.password = password;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            
            con.setAutoCommit(false);
            
            myDB.write("UPDATE Employee SET password = '" + password + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }    
    }

    /**
    * Set Phone Number.
    * <p>
    * This function changes the employee's phone number and updates the phone number stored on the database.
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
            myDB.write("UPDATE Employee SET phone_num = '" + phone_num + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection(); 
        }
    }

    /**
    * Set E-Mail.
    * <p>
    * This function changes the employee's e-mail and updates the e-mail stored on the database.
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
            myDB.write("UPDATE Employee SET email = '" + email + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }   
    }

    /**
    * Set Postcode.
    * <p>
    * This function changes the employee's postcode and updates the postcode stored on the database.
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
            myDB.write("UPDATE Employee SET postcode = '" + postcode + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
    }

    /**
    * Set Country.
    * <p>
    * This function changes the country that the employee lives in and updates the country stored on the database.
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
            myDB.write("UPDATE Employee SET country = '" + country + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }   
    }

    /**
    * Set City.
    * <p>
    * This function changes the city that the employee lives in and updates the city stored on the database.
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
            myDB.write("UPDATE Employee SET city = '" + city + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
    }

    /**
    * Set Address.
    * <p>
    * This function changes the employee's address and updates the address stored on the database.
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
            myDB.write("UPDATE Employee SET address = '" + address + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }    
    }

    /**
    * Set Surname.
    * <p>
    * This function changes the employee's surname and updates the surname stored on the database.
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
            myDB.write("UPDATE Employee SET last_name = '" + s_name + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }  
    }

    /**
    * Set First Name.
    * <p>
    * This function changes the employee's first name and updates the first name stored on the database.
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
            myDB.write("UPDATE Employee SET first_name = '" + f_name + "' WHERE employee_ID = '" + empID + "'");
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }   
    }
}
