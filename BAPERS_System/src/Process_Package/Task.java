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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Shows how to construct a Task object and its associated variables and methods.
 * 
 * @author      Felice Gregorio
 * @version     (10/04/2018) version 2
 * @since       (20/03/2018) version 1
 */
public class Task {
    /** The Task ID. */
    private int taskID;
    /** The Task description. */
    private String description;
    /** The location the task is carried out at. */
    private String location;
    /** The shelf slot of the task. */
    private String shelfSlot;
    /** The cost of the Task. */
    private double price;
    /** The amount of time it takes for the Task to be carried out. */
    private double duration;
    /** The status of the task for a particular job. */
    private String status;
    /** The priority of the task for a particular job. */
    private String priority;
    /** The value that dictates whether or not a task is considered as 'Deleted' on the database. */
    private boolean deleted;
    /** The connectivity to the database.  */
    private final DBConnectivity myDB = new DBConnectivity();

    /**
    * Constructor for a new Task object.
    * <p>
    * This is the constructor used when creating a new task and adding it to the system.
    * It is needed to create an instance of this class.
    * 
    * When an instance of this class is created, a connection to the database will be established and 
    * will insert a new record in the 'Task' table.
    * 
    * @param description The description of the task
    * @param location The location the task is carried out at.
    * @param shelfSlot The shelf slot of the task.
    * @param price The cost of the task.
    * @param duration The time it takes for the task to be carried out.
    * 
    */
    public Task(String description, String location, String shelfSlot, 
                    double price, double duration) {
        this.description = description;
        this.location = location;
        this.shelfSlot = shelfSlot;
        this.price = price;
        this.duration = duration;
        this.status = "Pending";
        this.priority = "Normal";
        this.deleted = false;
        taskID = 1;
        
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            ResultSet rs = myDB.read("SELECT task_ID FROM Task ORDER BY task_ID DESC LIMIT 1");
            try {
                while (rs.next()){
                    taskID = rs.getInt("task_ID") + 1;
                } 
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.write("INSERT INTO Task (task_ID, task_description, location, "
                        + "price, duration, shelf_slot, deleted) VALUES ('" + taskID + "','" 
                        + description + "','" + location + "','" + price + "','" + duration + "','" + shelfSlot + "'," + deleted + ")");
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
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
    }
    
    /**
    * Constructor for a existing Task object.
    * <p>
    * This is the constructor used when creating task object for an existing task.
    * It is needed to create an instance of this class.
    * 
    * @param taskID The Task ID.
    * 
    */
    public Task(int taskID) {
        this.taskID = taskID;
        this.status = "Pending";
        this.priority = "Normal";
        
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            ResultSet rs = myDB.read("SELECT * FROM Task WHERE task_ID = '" + taskID + "'");
            try {
                while (rs.next()){
                    description = rs.getString("task_description");
                    location = rs.getString("location");
                    price = rs.getDouble("price");
                    duration = rs.getDouble("duration");
                    shelfSlot = rs.getString("shelf_slot");
                    deleted = rs.getBoolean("deleted");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }

            myDB.closeConnection();
        }
    }
    
    /**
    * Start Task.
    * <p>
    * This function is called when beginning to process a task and will change the status of the given task to "Active".
    * 
    * @param jobCode
    * @param empID
    */
    public void startTask(String jobCode, String empID) {
        status = "Active";
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            Job job = new Job(jobCode);
            if (!job.getStatus().equals("Active")){
                job.started();
            } 
            
            myDB.write("UPDATE JobTasks SET employee_ID = '" + empID + "', status = '" + status + "', started = CURRENT_TIMESTAMP() WHERE job_code = '" + jobCode + "' AND task_ID = '" + taskID + "'");
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
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }       
    }
    
    /**
    * Task Completed.
    * <p>
    * This function is called when a task is completed and will change the status of the given task to "Completed".
    * 
    * @param jobCode
    */
    public void taskCompleted(String jobCode) {
        status  = "Completed";
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            
            Timestamp started = null;
            Timestamp completed = new Timestamp(System.currentTimeMillis());
            
            ResultSet rs = myDB.read("SELECT started FROM JobTasks WHERE job_code = '" + jobCode + "' AND task_ID = '" + taskID + "'");
            while(rs.next()){
                started = rs.getTimestamp("started");
            }
            
            long startLong = started.getTime();
            long compLong = completed.getTime();
            
            long timeTaken = startLong - compLong;
            
            double timeDiff = (timeTaken / 1000) / 60;
            
            myDB.write("UPDATE JobTasks SET status = '" + status + "', time_taken = " + timeDiff + ", completed = CURRENT_TIMESTAMP() WHERE job_code = '" + jobCode + "' AND task_ID = '" + taskID + "'");
            ResultSet rs2 = myDB.read("SELECT status FROM JobTasks WHERE job_code = '" + jobCode + "'");
            
            boolean allCompleted = true;
            while (rs2.next() && allCompleted){
                if(!(rs2.getString("status").equals("Completed"))){
                    allCompleted = false;
                }
            }
            if (allCompleted){
                Job job = new Job(jobCode);
                job.completed();
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
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
    }
    
    /**
    * Task is/may be late.
    * <p>
    * This function is called when a task is considered to be or being late and will change the status of the given task to "Late".
    * 
    * @param jobCode
    */
    public void taskLate(String jobCode) {
        status  = "Late";
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            myDB.write("UPDATE JobTasks SET status = '" + status + "', late_alert = true WHERE job_code = '" + jobCode + "' AND task_ID = '" + taskID + "'");
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
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
    }
    
    /**
    * Delete Task.
    * <p>
    * This function is used to delete the given task from the database if it has never been used/associated to a job.
    * If, however, the task has taken part in the process of a job, then the task is marked as deleted on the database, 
    * so that it can no longer be added to future incoming jobs.
    * 
    */
    public void delete() {
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            ResultSet rs = myDB.read("SELECT task_ID FROM JobTasks WHERE task_ID = '" + taskID + "'");
            if (!rs.next()){
                myDB.write("DELETE FROM Task WHERE task_ID = '" + taskID + "'");
            } else {
                deleted = true;
                myDB.write("UPDATE Task SET deleted = " + deleted + " WHERE task_ID = '" + taskID + "'");
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
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
    }
    
    /**
    * Get Task ID.
    * <p>
    * This function retrieves the Task ID and returns it.
    * 
    * @return Returns the Task ID.
    */
    public int getTaskID() {
        return taskID;
    }

    /**
    * Get Task Description.
    * <p>
    * This function retrieves the task description and returns it.
    * 
    * @return Returns the task description.
    */
    public String getDescription() {
        return description;
    }

    /**
    * Set Task Description.
    * <p>
    * This function changes the task description and updates the task description data stored on the database.
    * 
    * @param description The task description to be set.
    */
    public void setDescription(String description) {
        this.description = description;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            myDB.write("UPDATE Task SET task_description = '" + description + "' WHERE task_ID = '" + taskID + "'");
            
            con.commit();
        }catch (SQLException ex) {
            
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }

    }

    /**
    * Get Task Location.
    * <p>
    * This function retrieves the location in which the task is carried out and returns it.
    * 
    * @return Returns the task location.
    */
    public String getLocation() {
        return location;
    }

    /**
    * Set Task Location.
    * <p>
    * This function changes the location the task is carried out at and updates the task location data stored on the database.
    * 
    * @param location The task location to be set.
    */
    public void setLocation(String location) {
        this.location = location;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            myDB.write("UPDATE Task SET location = '" + location + "' WHERE task_ID = '" + taskID + "'");
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
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }   
    }

    /**
    * Get Task Shelf Slot.
    * <p>
    * This function retrieves the shelf slot of the task and returns it.
    * 
    * @return Returns the task shelf slot.
    */
    public String getShelfSlot() {
        return shelfSlot;
    }

    /**
    * Set Task Description.
    * <p>
    * This function changes the shelf slot the task has been assigned and updates the shelf slot data stored on the database.
    * 
    * @param shelfSlot The task shelf slot to be set.
    */
    public void setShelfSlot(String shelfSlot) {
        this.shelfSlot = shelfSlot;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            myDB.write("UPDATE Task SET shelf_slot = '" + shelfSlot + "' WHERE task_ID = '" + taskID + "'");
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
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }     
    }
    
    /**
    * Set Completion Shelf Slot.
    * <p>
    * This function sets the completion shelf slot of this task for a particular job,
    * 
    * @param shelfSlot The completion shelf slot to be set.
    * @param jobCode The job code of the job this task is referring to.
    */
    public void setCompletionShelfSlot(String shelfSlot, String jobCode){
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            myDB.write("UPDATE JobTasks SET completion_shelf_slot = '" + shelfSlot + "' WHERE job_code = '" + jobCode + "' AND task_ID = '" + taskID + "'");
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
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
    }

    /**
    * Get Task Price.
    * <p>
    * This function retrieves the cost of the task and returns it.
    * 
    * @return Returns the task price.
    */
    public double getPrice() {
        return price;
    }

    /**
    * Set Task Price.
    * <p>
    * This function changes the cost of the task and updates the task price data stored on the database.
    * 
    * @param price The price to be set.
    */
    public void setPrice(double price) {
        this.price = price;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            myDB.write("UPDATE Task SET price = '" + price + "' WHERE task_ID = '" + taskID + "'");
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
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }  
    }

    /**
    * Get Task Duration.
    * <p>
    * This function retrieves the time it takes for the task to be carried out and returns it.
    * 
    * @return Returns the task duration.
    */
    public double getDuration() {
        return duration;
    }

    /**
    * Set Task Duration.
    * <p>
    * This function changes the time it tasked for a task to be carried out and updates the task duration data stored on the database.
    * 
    * @param duration The task duration to be set.
    */
    public void setDuration(double duration) {
        this.duration = duration;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            myDB.write("UPDATE Task SET duration = '" + duration + "' WHERE task_ID = '" + taskID + "'");
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
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
    }

    /**
    * Get Task Status.
    * <p>
    * This function retrieves the status of a task [Pending/Active/Completed] for a given job and returns it.
    * 
    * @param jobCode The job code the task is associated to.
    * 
    * @return Returns the task status.
    */
    public String getStatus(String jobCode) {
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            ResultSet rs = myDB.read("SELECT status FROM JobTasks WHERE job_code = '" + jobCode + "' AND task_ID ='" + getTaskID() + "'");
        
            while (rs.next()){
                status = rs.getString("status");
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
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            myDB.closeConnection();
        }
        return status;
    }

    /**
    * Get Task Priority.
    * <p>
    * This function retrieves the priority of a task for a given job and returns it.
    * 
    * @param jobCode The job code the task is associated to.
    * 
    * @return Returns the task priority level.
    */
    public String getPriority(String jobCode) {
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);

            ResultSet rs = myDB.read("SELECT priority FROM JobTasks WHERE job_code = '" + jobCode + "' AND task_ID ='" + getTaskID() + "'");
        
            while (rs.next()){
                priority = rs.getString("priority");
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
        } finally {
            myDB.closeConnection();
        }
        return priority;
    }

    /**
    * Set Task Status.
    * <p>
    * This function sets the status of a task for a given job and returns it.
    * 
    * @param jobCode The job code the task is associated to.
    * @param priority The priority to be set
    * 
    */
    public void setPriority(String jobCode, String priority) {
        this.priority = priority;
        myDB.connect();
        Connection con = myDB.getConn();
        try {
            con.setAutoCommit(false);
            myDB.write("UPDATE JobTasks SET priority = '" + priority + "' WHERE job_code = '" + jobCode + "'");
            myDB.write("UPDATE Job SET priority = '" + priority + "' WHERE job_code = '" + jobCode + "'");
            con.commit();
            con.setAutoCommit(true);
            myDB.closeConnection();
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    System.err.print("Transaction rolling back!");
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
    }
    
    /**
    * Is Task Deleted.
    * <p>
    * This function is used to check whether the task is marked as 'deleted'.
    * 
    * @return Returns 'True' if the task is marked as deleted. 'False' if otherwise. 
    */
    public boolean isDeleted(){
        return deleted;
    }
    
}
