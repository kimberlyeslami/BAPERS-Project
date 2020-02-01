/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_Package;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Demonstrates how generated reports data can be read from a text file.
 * 
 * @author      Felice Gregorio
 * @version     (02/04/2018) version 1.1
 * @since       (01/04/2018) version 1
 */
public class Reader {
    /** The name of the .txt file to read from. */
    private final String fileName;
    /** The list of generated reports, read from the .txt file. */
    private final ArrayList<String> reports;
    /** The list of report schedules, read from the .txt file. */
    private final ArrayList<String> schedules;

    /**
     * Initialise a new ReportsReader.
     * 
     * @param fileName The name of the file to read from.
     */
    public Reader(String fileName) {
        this.fileName = fileName;
        reports = new ArrayList<>();
        schedules = new ArrayList<>();
    }
    
    /**
     * Read the generated reports data from the .txt file.
     * 
     * @return Returns a list of generated reports, read from the .txt file.
     */
    public ArrayList<String> readGeneratedReports(){
        FileReader fr;
        BufferedReader reader;
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                reports.add(line);
                line = reader.readLine();
            }
            
            Collections.sort(reports, Collections.reverseOrder());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reports;
    }
    
    /**
     * Read the report schedules from the .txt file.
     * 
     * @return Returns the list of report schedules, read from the .txt file.
     */
    public ArrayList<String> readReportSchedules(){
        FileReader fr;
        BufferedReader reader;
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                schedules.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return schedules;
    }
    
    /**
     * Read the backup schedules from the .txt file.
     * 
     * @return Returns the backup schedule, read from the .txt file.
     */
    public String readBackupSchedule(){
        FileReader fr;
        BufferedReader reader;
        String schedule = "";
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            schedule = reader.readLine();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return schedule;
    }
    
    /**
     * Read the letter data from the .txt file.
     * 
     * @return Returns content of the letter, read from the .txt file.
     */
    public String readLetter(){
        FileReader fr;
        BufferedReader reader;
        String letter = "";
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                letter += line + "\n";
                line = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return letter;
    }
}
