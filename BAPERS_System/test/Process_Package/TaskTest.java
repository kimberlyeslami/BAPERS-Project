/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process_Package;

import Database_Package.DBConnectivity;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test class for testing methods belonging to the Task class.
 * 
 * @author      Felice Gregorio
 * @version     (10/04/2018) version 2
 * @since       (22/03/2018) version 1
 */
public class TaskTest {
    /** The connectivity to the database.  */
    private final DBConnectivity myDB = new DBConnectivity();
    
    public TaskTest() {}

    /**
     * Test of constructor for existing task, of class Task.
     * <p>
     * This test ensures that when constructing a Task object with the second constructor, 
     * the data needed to initialise the object is correctly extracted from the database.
     * 
     */
    @Test
    public void testExistingTaskConstructor() {
        System.out.println("secondConstructor");
        Task task = new Task(3);
        
        assertEquals(3, task.getTaskID(), 0.0);
        assertEquals("Bag up", task.getDescription());
        assertEquals("Packing Department", task.getLocation());
        assertEquals("PR10", task.getShelfSlot());
        assertEquals(6, task.getPrice(), 0.0);
        assertEquals(30, task.getDuration(), 0.0);
        assertEquals("Completed", task.getStatus("ABN54"));
    }
    
    /**
     * Test of startTask method, of class Task.
     * @throws java.sql.SQLException
     */
    @Test
    public void testStartTask() throws SQLException {
        System.out.println("startTask");
        String jobCode ="ABN54";
        Task task = new Task(1);
        task.startTask(jobCode, "FG12");
        assertEquals("Active", task.getStatus(jobCode));
        
        myDB.connect();
        ResultSet rs = myDB.read("SELECT status FROM JobTasks WHERE job_code = '" + jobCode + "' AND task_ID = '" + task.getTaskID() + "'");
        while (rs.next()){
            assertEquals("Active", rs.getString("status"));
        }
        myDB.closeConnection();
    }

    /**
     * Test of taskCompleated method, of class Task.
     * @throws java.sql.SQLException
     */
    @Test
    public void testTaskCompleated() throws SQLException {
        System.out.println("taskCompleated");
        String jobCode = "ABN54";
        Task task = new Task(1);
        task.taskCompleted(jobCode);
        assertEquals("Completed", task.getStatus(jobCode));
        
        myDB.connect();
        ResultSet rs = myDB.read("SELECT status FROM JobTasks WHERE job_code = '" + jobCode + "' AND task_ID = '" + task.getTaskID() + "'");
        while (rs.next()){
            assertEquals("Completed", rs.getString("status"));
        }
        myDB.closeConnection();
        
    }

    /**
     * Test of getTaskID method, of class Task.
     */
    @Test
    public void testGetTaskID() {
        System.out.println("getTaskID");
        Task task = new Task(3);
        int expResult = 3;
        int result = task.getTaskID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescription method, of class Task.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        String expResult = "Apply protective coating";
        String result = task.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Task.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetDescription() throws SQLException {
        System.out.println("setDescription");
        String description = "Use of medium copy camera";
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        task.setDescription(description);
        
        assertEquals(description, task.getDescription());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT task_description FROM Task WHERE task_ID = '" + task.getTaskID() + "'");
        if (rs.next()){
            assertEquals(description, rs.getString("task_description"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of getLocation method, of class Task.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        String expResult = "Finishing Room";
        String result = task.getLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocation method, of class Task.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetLocation() throws SQLException {
        System.out.println("setLocation");
        String location = "Copy Room";
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        task.setLocation(location);
        
        assertEquals(location, task.getLocation());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT location FROM Task WHERE task_ID = '" + task.getTaskID() + "'");
        if (rs.next()){
            assertEquals(location, rs.getString("location"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of getShelfSlot method, of class Task.
     */
    @Test
    public void testGetShelfSlot() {
        System.out.println("getShelfSlot");
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        String expResult = "FR22";
        String result = task.getShelfSlot();
        assertEquals(expResult, result);
    }

    /**
     * Test of setShelfSlot method, of class Task.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetShelfSlot() throws SQLException {
        System.out.println("setShelfSlot");
        String shelfSlot = "CR7";
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        task.setShelfSlot(shelfSlot);
        
        assertEquals(shelfSlot, task.getShelfSlot());
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT shelf_slot FROM Task WHERE task_ID = '" + task.getTaskID() + "'");
        if (rs.next()){
            assertEquals(shelfSlot, rs.getString("shelf_slot"));
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of getPrice method, of class Task.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        double expResult = 25.50;
        double result = task.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPrice method, of class Task.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetPrice() throws SQLException {
        System.out.println("setPrice");
        double price = 76.5;
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        task.setPrice(price);
        
        assertEquals(price, task.getPrice(), 0.0);
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT price FROM Task WHERE task_ID = '" + task.getTaskID() + "'");
        if (rs.next()){
            assertEquals(price, rs.getDouble("price"), 0.0);
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of getDuration method, of class Task.
     */
    @Test
    public void testGetDuration() {
        System.out.println("getDuration");
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        double expResult = 30;
        double result = task.getDuration();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setDuration method, of class Task.
     * @throws java.sql.SQLException
     */
    @Test
    public void testSetDuration() throws SQLException {
        System.out.println("setDuration");
        double duration = 240;
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        task.setDuration(duration);
        
        assertEquals(duration, task.getDuration(), 0.0);
        
        //Checks if the details were correctly stored in the database
        myDB.connect();
        ResultSet rs = myDB.read("SELECT duration FROM Task WHERE task_ID = '" + task.getTaskID() + "'");
        if (rs.next()){
            assertEquals(duration, rs.getDouble("duration"), 0.0);
        } else {
            fail("No results were found from the resulting query!");
        }
        myDB.closeConnection();
    }

    /**
     * Test of getStatus method, of class Task.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        String expResult = "Pending";
        String result = task.getStatus("ABN54");
        assertEquals(expResult, result);
    }

    /**
     * Test of taskLate method, of class Task.
     * @throws java.sql.SQLException
     */
    @Test
    public void testTaskLate() throws SQLException {
        System.out.println("taskLate");
        String jobCode = "ABN54";
        Task task = new Task(1);
        task.taskLate(jobCode);
        assertEquals("Late", task.getStatus("ABN54"));
        
        myDB.connect();
        ResultSet rs = myDB.read("SELECT status FROM JobTasks WHERE job_code = '" + jobCode + "' AND task_ID = '" + task.getTaskID() + "'");
        while (rs.next()){
            assertEquals("Late", rs.getString("status"));
        }
        myDB.closeConnection();
    }

    /**
     * Test of delete method, of class Task.
     * @throws java.sql.SQLException
     */
    @Test
    public void testDelete() throws SQLException {
        System.out.println("delete");
        Task task = new Task("Apply protective coating", "Finishing Room", "FR22", 25.50, 30);
        task.delete();
        
        myDB.connect();
        ResultSet rs = myDB.read("SELECT * FROM Task WHERE task_ID = '" + task.getTaskID() + "'");
        assertTrue(!rs.next());
        myDB.closeConnection();
    }
    
    /**
     * Test of delete method (Marking Task Deleted), of class Task.
     * @throws java.sql.SQLException
     */
    @Test
    public void testDelete2() throws SQLException {
        System.out.println("delete2");
        Task task = new Task(1);
        task.delete();
        
        myDB.connect();
        ResultSet rs = myDB.read("SELECT deleted FROM Task WHERE task_ID = '" + task.getTaskID() + "'");
        assertTrue(rs.getBoolean("deleted") == true);
        myDB.closeConnection();
    }

    /**
     * Test of getPriority method, of class Task.
     */
    @Test
    public void testGetPriority() {
        System.out.println("getPriority");
        String jobCode = "ABN54";
        Task task = new Task(1);
        String expResult = "Normal";
        String result = task.getPriority(jobCode);
        assertEquals(expResult, result);
    }

    /**
     * Test of setPriority method, of class Task.
     */
    @Test
    public void testSetPriority() throws SQLException {
        System.out.println("setPriority");
        String jobCode = "ABN54";
        String priority = "High";
        Task task = new Task(1);
        task.setPriority(jobCode, priority);
        
        myDB.connect();
        ResultSet rs = myDB.read("SELECT priority FROM JobTasks WHERE jobCode = '" + jobCode + "'");
        while(rs.next()){
            assertEquals("High", rs.getBoolean("priority"));
        }
        ResultSet rs2 = myDB.read("SELECT priority FROM Job WHERE jobCode = '" + jobCode + "'"); 
        while(rs2.next()){
            assertEquals("High", rs.getBoolean("priority"));
        }
        myDB.closeConnection();
        
    }
    
}
