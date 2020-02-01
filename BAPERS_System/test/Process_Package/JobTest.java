/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process_Package;

import Database_Package.DBConnectivity;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Adem Aybar
 */
public class JobTest {
    
    private final DBConnectivity myDB = new DBConnectivity();
    
    String jobID;
    
    //for this test to run you need tasks with id 1 through 3 and a customer with ID ACC0001 in the database.
    
    public JobTest(){}
    
     @Test
    public void newJob() throws SQLException {
        System.out.println("create job");
        
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1));
        tasks.add(new Task(2));
        tasks.add(new Task(3));
        
        Job job = new Job("These are special instructions","Normal",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),"ACC0001",tasks);
        this.jobID = job.getJobID();
        
        assertEquals("These are special instructions", job.getSpecialInstruction());
        assertEquals("Normal", job.getPriority());
    }
    
    @Test
    public void existingJob() throws SQLException {
        System.out.println("create job");
        
        Job job = new Job("AY6999");
        
        assertEquals("These are special instructions", job.getSpecialInstruction());
        assertEquals("Normal", job.getPriority());
    }
    
    
}
