/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_Package;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Demonstrates how a list of generated report queries can be written to a text file.
 * 
 * @author      Felice Gregorio
 * @version     (11/04/2018) version 1.2
 * @since       (01/04/2018) version 1
 */
public class Writer {
    /** The name of the .txt file to write to. */
    private final String fileName;
    /** The current date/time. */
    private final LocalDateTime date = LocalDateTime.now();

    /**
     * Initialise a new ReportsWriter.
     * 
     * @param fileName The file to write to.
     */
    public Writer(String fileName) {
        this.fileName = fileName;
    }
    
    /**
     * Write the generated reports SQL query to the file .
     * <p>
     * This method will write the name of the generated report, the query executed 
     * to generate the report and the current date/time the report was generated to the pre-specified .txt file.
     * 
     * @param reptName The name of the generated report.
     * @param start The start date of the report.
     * @param end The end date of the report.
     * @param custID The customer ID (ONLY FOR INDIVIDUAL REPORT).
     */
    public void writeGeneratedReport(String reptName, Date start, Date end, String custID){
        boolean append = true;
        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(date.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss")) + "," + reptName + "," + start + "," + end + "," + custID + "\n");
        } catch (IOException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Write the report schedules to the file .
     * <p>
     * This method will write the list of schedules for each report into a file.
     * 
     * @param schedules The list of schedules to write.
     */
    public void writeReportSchedule(String schedules[]){
        boolean append = false;
        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write("Individual Report," + schedules[0] + "\n");
            writer.write("Individual Performance Report," + schedules[1] + "\n");
            writer.write("Summary Performance Report," + schedules[2] + "\n");
        } catch (IOException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Write the backup schedule to the file .
     * <p>
     * This method will write the backup schedule into a file.
     * 
     * @param schedule The schedule for backups.
     */
    public void writeBackupSchedule(String schedule){
        boolean append = false;
        try (FileWriter writer = new FileWriter(fileName, append)) {
            writer.write(schedule);
        } catch (IOException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Write the letter to the file .
     * <p>
     * This method will write the letter into a file.
     * 
     * @param letter The content of the letter.
     */
    public void writeLetter(String letter){
        try (FileWriter writer = new FileWriter("ReminderLetters/" + fileName)) {
            writer.write(letter);
        } catch (IOException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}