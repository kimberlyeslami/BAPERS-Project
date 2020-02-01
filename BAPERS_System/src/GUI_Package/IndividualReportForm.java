/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Package;

import Reports_Package.IndividualReport;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.text.*;
import java.awt.print.*;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author umahm, Felice Gregorio
 * 
 * @version     (11/04/2018) version 2.1
 * @since       (26/03/2018) version 1
 */
public class IndividualReportForm extends javax.swing.JFrame {
   /**New JFrame */
    private final JFrame frame;
    /** The Start Date */
    private final Date start;
    /**The End Date */
    private final Date end;
    /**The Customers ID */
    private final String custID;
    /** The Employee Job role*/
    private final String jobRole;
     /** Sets the date format */
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     /**The Individual report */
    private final IndividualReport ir;
    /** Array list with the Reports */
    private final ArrayList<ResultSet> reports;
    /** The Employees ID. */
    private final String empID;

    /**
     * Creates new form IndividualReport.
     * 
     * @param frame new JFrame
     * @param jobRole  Employee's job role
     * @param custID Customers ID
     * @param start  start date 
     * @param end end date
     * @param empID The employee ID.
     */
    public IndividualReportForm(JFrame frame, String jobRole, String custID, Date start, Date end, String empID) {
        this.empID = empID;
        this.frame = frame;
        this.jobRole = jobRole;
        this.custID = custID;
        this.start = start;
        this.end = end;
        
        ir = new IndividualReport(custID, start, end);
        
        if (frame.getName().equals("viewReports")){
            reports = ir.generate(false);
        } else {
            reports = ir.generate(true);
        }
        
        initComponents();
        
        period_lbl.setText("Period: " + sdf.format(start) + " - " + sdf.format(end));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Back_btn = new javax.swing.JButton();
        Print_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        report_tbl = new javax.swing.JTable();
        period_lbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        IndvReport_lbl = new javax.swing.JLabel();
        LogOut_btn = new javax.swing.JButton();
        HomePage_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Back_btn.setBackground(new java.awt.Color(0, 153, 255));
        Back_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Back_btn.setForeground(new java.awt.Color(255, 255, 255));
        Back_btn.setText("Back");
        Back_btn.setBorder(null);
        Back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_btnActionPerformed(evt);
            }
        });

        Print_btn.setBackground(new java.awt.Color(0, 153, 255));
        Print_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Print_btn.setForeground(new java.awt.Color(255, 255, 255));
        Print_btn.setText("Print ");
        Print_btn.setBorder(null);
        Print_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Print_btnActionPerformed(evt);
            }
        });

        report_tbl.setModel(setTable());
        report_tbl.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(report_tbl);

        period_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        period_lbl.setText("Period: DD/MM/YY - DD/MM/YY");

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        IndvReport_lbl.setBackground(new java.awt.Color(0, 153, 255));
        IndvReport_lbl.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        IndvReport_lbl.setForeground(new java.awt.Color(255, 255, 255));
        IndvReport_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IndvReport_lbl.setText("Individual Report");
        IndvReport_lbl.setOpaque(true);

        LogOut_btn.setBackground(new java.awt.Color(255, 255, 255));
        LogOut_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        LogOut_btn.setForeground(new java.awt.Color(0, 153, 255));
        LogOut_btn.setText("Logout");
        LogOut_btn.setBorder(null);
        LogOut_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOut_btnActionPerformed(evt);
            }
        });

        HomePage_btn.setBackground(new java.awt.Color(255, 255, 255));
        HomePage_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        HomePage_btn.setForeground(new java.awt.Color(0, 153, 255));
        HomePage_btn.setText("Home Page");
        HomePage_btn.setBorder(null);
        HomePage_btn.setMaximumSize(new java.awt.Dimension(65, 19));
        HomePage_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomePage_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LogOut_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(HomePage_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(IndvReport_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(238, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LogOut_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HomePage_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IndvReport_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(Back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Print_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(period_lbl)
                .addGap(284, 284, 284))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(period_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Print_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Back Button
     * <p>
     * Closes the form taking you back to the previous form.
     */
    private void Back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back_btnActionPerformed
        this.dispose();
        frame.setVisible(true);
    }//GEN-LAST:event_Back_btnActionPerformed
     /**
     * Print Button
     * <p>
     * Prints the Report.
     */
    private void Print_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Print_btnActionPerformed
        MessageFormat header = new MessageFormat(ir.getReptName() + "\n" + period_lbl.getText());
        MessageFormat footer = new MessageFormat("Page{0, number, integer}");
        
        try {
            report_tbl.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (PrinterException ex) {
            Logger.getLogger(IndividualReportForm.class.getName()).log(Level.SEVERE, null, ex);
            System.err.format("Cannot print Report!", ex.getMessage());
        }
        
    }//GEN-LAST:event_Print_btnActionPerformed
     /**
     * Logout Button
     * <p>
     * Closes the form and takes you back to the login form.
     */
    private void LogOut_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOut_btnActionPerformed
        this.dispose();
        Login_Form login = new Login_Form();
        login.setVisible(true);
    }//GEN-LAST:event_LogOut_btnActionPerformed
     /**
     * HomePage Button
     * <p>
     * Closes the form and takes you back to the home page form.
     */
    private void HomePage_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomePage_btnActionPerformed
        this.dispose();
        HomePage hp = new HomePage(jobRole, empID);
        hp.setVisible(true);
    }//GEN-LAST:event_HomePage_btnActionPerformed
     /**
     * Set Table
     * <p>
     * Sets the values of the table.
     */
    public TableModel setTable(){
        return resultSetToTableModel(reports.get(0));
    }
    /**
     * Table
     * <p>
     * Creates new table and populates it with data from the DB
     * @param rs
     * @return table populated with the report data.
     */
    public static DefaultTableModel resultSetToTableModel(ResultSet rs) {
        ResultSetMetaData md;
        
        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();
        
        try {
            md = rs.getMetaData();
            
            // names of columns
            int columnCount = md.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(md.getColumnLabel(i));
            }

            // data of the table
            while (rs.next()) {
                Vector<Object> vector = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(InspectTasks.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new DefaultTableModel(data, columnNames);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IndividualReportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IndividualReportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IndividualReportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IndividualReportForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IndividualReportForm(new Generate_Report("Office Manager", "FG12"), "Office Manager", "ACC0002", new Date(new GregorianCalendar(2018, 02, 01).getTimeInMillis()), new Date(new GregorianCalendar(2018, 02, 30).getTimeInMillis()), "FG12").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back_btn;
    private javax.swing.JButton HomePage_btn;
    private javax.swing.JLabel IndvReport_lbl;
    private javax.swing.JButton LogOut_btn;
    private javax.swing.JButton Print_btn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel period_lbl;
    private javax.swing.JTable report_tbl;
    // End of variables declaration//GEN-END:variables
}
