/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database_Package;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Demonstrates how to establish a connection with the MySQL database server
 * alongside its associated variables and methods.
 *
 * @author Felice Gregorio, Adem Aybar
 * @version (19/03/2018) version 3
 * @since (12/03/2018) version 1
 */
public class DBConnectivity implements DBConnectivityInterface {

    /**
     * The connection to the database.
     */
    private Connection conn;
    /**
     * The statement to be executed.
     */
    private Statement st;
    /**
     * The resulting table from the execute statement.
     */
    private ResultSet rs = null;
    /**
     * The callable statement to be executed when calling a stored procedure.
     */
    private CallableStatement cs;

    /**
     * Constructor for a new Database Connection.
     * <p>
     * This is the constructor used when establishing a new Database connection.
     *
     */
    public DBConnectivity() {
    }

    /**
     * Open connection to database.
     * <p>
     * This function opens and establishes a connection to the MySQL database
     * server.
     *
     */
    @Override
    public void connect() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bapers?useSSL=false", "root", "Benny@0903");
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Connection to the database has been lost!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Close connection to database.
     * <p>
     * This function closes the connection to the MySQL database server and
     * releases all resources.
     *
     */
    @Override
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Read from database.
     * <p>
     * This function reads from the database and executes a specified SQL
     * statement. It then returns the result of the executed SQL statement.
     *
     * @param query The SQL query/statement to be executed.
     * @return Returns the resulting table from the executed statement.
     */
    @Override
    public ResultSet read(String query) {
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    /**
     * Write to database.
     * <p>
     * This function writes to the database and executes a specified SQL
     * statement.
     *
     * @param query The SQL query/statement to be executed.
     */
    @Override
    public void write(String query) {
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Read and Execute a stored procedure from the database.
     * <p>
     * This function reads from the database and executes a specified stored
     * procedure statement, used to generate a report. It then returns the
     * result of the executed stored procedure statement.
     *
     * @param storedProc The stored procedure to be executed to be executed.
     * @param custID The customer ID needed when executing the spIdividualReport
     * stored procedure.
     * @param start The start date needed when executing a stored procedure to
     * generate a report.
     * @param end The end date needed when executing a stored procedure to
     * generate a report.
     * @return Returns the resulting table from the executed stored procedure.
     */
    public ResultSet execStoredProc(String storedProc, String custID, Date start, Date end) {
        if (custID == null) {
            try {
                conn.setAutoCommit(false);
                cs = conn.prepareCall(storedProc);
                cs.setDate(1, start);
                cs.setDate(2, end);
                rs = cs.executeQuery();
                conn.commit();
            } catch (SQLException ex) {
                if (conn != null) {
                    try {
                        System.err.print("Transaction rolling back!");
                        conn.rollback();
                    } catch (SQLException ex1) {
                   	Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }finally{
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                conn.setAutoCommit(false);
                cs = conn.prepareCall(storedProc);
                cs.setString(1, custID);
                cs.setDate(2, start);
                cs.setDate(3, end);
                rs = cs.executeQuery();
                conn.commit();
            } catch (SQLException ex) {
                if (conn != null) {
                    try {
                        System.err.print("Transaction rolling back!");
                        conn.rollback();
                    } catch (SQLException ex1) {
                   	Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }finally{
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return rs;
    }

    /**
     * Backup system database.
     * <p>
     * This function is used to create a backup file of the current state of the
     * BAPERS database and stores it in the specified location.
     *
     * @param isAuto
     */
    public void backup(boolean isAuto) {
        String bkupDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Process proc;
        try {
            Runtime rt = Runtime.getRuntime();
            proc = rt.exec("/Applications/MySQLWorkbench.app/Contents/MacOS/mysqldump -uroot -pInternazionale1997 --add-drop-database BAPERS -r /Users/FMG97/BAPERS_Backup/BAPERS_bkup_" + bkupDate + ".sql");
            int procFinished = proc.waitFor();
            if (!isAuto){
                if (procFinished == 0) {
                    JOptionPane.showMessageDialog(null, "Backup Successful!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Backup Failed!", "Error!", JOptionPane.ERROR_MESSAGE);
                    File bkupFile = new File("/Users/FMG97/BAPERS_Backup/BAPERS_bkup_" + bkupDate + ".sql");
                    bkupFile.delete();
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Restore system database.
     * <p>
     * This function is used to restore the BAPERS database to a previous state
     * from a specified backup file.
     *
     * @param sourcePath The path/directory of the backup file to restore.
     */
    public void restore(String sourcePath) {
        String[] restore = new String[]{"/Applications/MySQLWorkbench.app/Contents/MacOS/mysql", "BAPERS", "-uroot", "-pInternazionale1997", "-e", "source " + sourcePath};
        Process proc;
        try {

            proc = Runtime.getRuntime().exec(restore);
            int processFinished = proc.waitFor();

            if (processFinished == 0) {
                JOptionPane.showMessageDialog(null, "Successfully Restored!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Restore Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(DBConnectivity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get database connection.
     * <p>
     * This function retrieves the database connection.
     *
     * @return Returns the database connection.
     *
     */
    public Connection getConn() {
        return conn;
    }
}
