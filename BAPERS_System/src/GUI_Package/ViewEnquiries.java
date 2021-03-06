/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Package;

import Database_Package.DBConnectivity;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author umahm, Felice Gregorio
 */
public class ViewEnquiries extends javax.swing.JFrame {
    /** The Employees job role. */
    private final String jobRole;
    /** The Employees ID. */
    private final String empID;
    
    /**
     * Creates new form ViewEnquiries.
     * @param jobRole employees job role.
     * @param empID employee ID.
     */
    public ViewEnquiries(String jobRole, String empID) {
        this.empID = empID;
        this.jobRole = jobRole;
        initComponents();
        Toolkit tool = Toolkit.getDefaultToolkit();
        int height = (int) tool.getScreenSize().getHeight();
        int width = (int) tool.getScreenSize().getWidth();
        sort();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ViewEnquiries_Panel = new javax.swing.JPanel();
        search_txt = new javax.swing.JTextField();
        search_lbl = new javax.swing.JLabel();
        Respond_Button = new javax.swing.JButton();
        Search_Button = new javax.swing.JButton();
        Back_Button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        enquiries_tbl = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        ViewEnquiries_Field = new javax.swing.JTextField();
        Logout_Button = new javax.swing.JButton();
        HomePage_Button = new javax.swing.JButton();
        Logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ViewEnquiries_Panel.setBackground(new java.awt.Color(255, 255, 255));

        search_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_txtActionPerformed(evt);
            }
        });

        search_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        search_lbl.setText("Search:");

        Respond_Button.setBackground(new java.awt.Color(0, 153, 255));
        Respond_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Respond_Button.setForeground(new java.awt.Color(255, 255, 255));
        Respond_Button.setText("Respond");
        Respond_Button.setBorder(null);
        Respond_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Respond_ButtonActionPerformed(evt);
            }
        });

        Search_Button.setBackground(new java.awt.Color(0, 153, 255));
        Search_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Search_Button.setForeground(new java.awt.Color(255, 255, 255));
        Search_Button.setText("Search");
        Search_Button.setBorder(null);
        Search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_ButtonActionPerformed(evt);
            }
        });

        Back_Button.setBackground(new java.awt.Color(0, 153, 255));
        Back_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Back_Button.setForeground(new java.awt.Color(255, 255, 255));
        Back_Button.setText("Back");
        Back_Button.setBorder(null);
        Back_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_ButtonActionPerformed(evt);
            }
        });

        DBConnectivity myDB = new DBConnectivity();
        myDB.connect();
        ResultSet rs = myDB.read("SELECT enquiry_id AS 'Enquiry ID', job_code AS 'Job Code', comment AS 'Message', response AS 'Response' FROM Enquiry");
        enquiries_tbl.setModel(resultSetToTableModel(rs));
        myDB.closeConnection();
        enquiries_tbl.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(enquiries_tbl);

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        ViewEnquiries_Field.setBackground(new java.awt.Color(0, 153, 255));
        ViewEnquiries_Field.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        ViewEnquiries_Field.setForeground(new java.awt.Color(255, 255, 255));
        ViewEnquiries_Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ViewEnquiries_Field.setText("View Enquires");
        ViewEnquiries_Field.setBorder(null);
        ViewEnquiries_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewEnquiries_FieldActionPerformed(evt);
            }
        });

        Logout_Button.setBackground(new java.awt.Color(255, 255, 255));
        Logout_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Logout_Button.setForeground(new java.awt.Color(0, 153, 255));
        Logout_Button.setText("Logout");
        Logout_Button.setBorder(null);
        Logout_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout_ButtonActionPerformed(evt);
            }
        });

        HomePage_Button.setBackground(new java.awt.Color(255, 255, 255));
        HomePage_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        HomePage_Button.setForeground(new java.awt.Color(0, 153, 255));
        HomePage_Button.setText("Homepage");
        HomePage_Button.setBorder(null);
        HomePage_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomePage_ButtonActionPerformed(evt);
            }
        });

        Logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\kimbe\\Documents\\Uni Work\\Second Year Uni\\Team project\\WhatsApp Image 2018-04-18 at 2.43.23 PM.jpeg")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logo)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Logout_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HomePage_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(ViewEnquiries_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Logout_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HomePage_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(ViewEnquiries_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Logo)
                .addContainerGap())
        );

        javax.swing.GroupLayout ViewEnquiries_PanelLayout = new javax.swing.GroupLayout(ViewEnquiries_Panel);
        ViewEnquiries_Panel.setLayout(ViewEnquiries_PanelLayout);
        ViewEnquiries_PanelLayout.setHorizontalGroup(
            ViewEnquiries_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ViewEnquiries_PanelLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(ViewEnquiries_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewEnquiries_PanelLayout.createSequentialGroup()
                        .addComponent(search_lbl)
                        .addGap(18, 18, 18)
                        .addComponent(search_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Search_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ViewEnquiries_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(ViewEnquiries_PanelLayout.createSequentialGroup()
                            .addComponent(Respond_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Back_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        ViewEnquiries_PanelLayout.setVerticalGroup(
            ViewEnquiries_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewEnquiries_PanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(ViewEnquiries_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_lbl)
                    .addComponent(search_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(ViewEnquiries_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Respond_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Back_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ViewEnquiries_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ViewEnquiries_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ViewEnquiries_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewEnquiries_FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewEnquiries_FieldActionPerformed
    /**
     * HomePage Button
     * <p>
     * Closes the form and takes you back to the home page form.
     */
    private void HomePage_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomePage_ButtonActionPerformed
        this.dispose();
        HomePage hp = new HomePage(jobRole, empID);
        hp.setVisible(true);
    }//GEN-LAST:event_HomePage_ButtonActionPerformed
    /**
     * Logout Button
     * <p>
     * Closes the form and takes you back to the login form.
     */
    private void Logout_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout_ButtonActionPerformed
        this.dispose();
        Login_Form login = new Login_Form();
        login.setVisible(true);
    }//GEN-LAST:event_Logout_ButtonActionPerformed
     /** Search button
    * <p>
    * Searches for a Enquiry in the table. 
    */
    private void Search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_ButtonActionPerformed
        String s = search_txt.getText();
        filter(s);
    }//GEN-LAST:event_Search_ButtonActionPerformed

    private void search_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_txtActionPerformed
   /** Respond button
     * <p>
     * Opens the Respond Enquiry form for the selected enquiry. 
     */
    private void Respond_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Respond_ButtonActionPerformed
        int row = enquiries_tbl.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Please select an enquiry!", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            Object jobCode = enquiries_tbl.getValueAt(row, 1);
            Object enquiryID = enquiries_tbl.getValueAt(row, 0);
            RespondEnquiry re = new RespondEnquiry(jobRole, jobCode.toString(), Integer.parseInt(enquiryID.toString()), empID);
            re.setVisible(true);
        }
    }//GEN-LAST:event_Respond_ButtonActionPerformed
     /**
     * Back Button
     * <p>
     * Closes the form taking you back to the previous form.
     */
    private void Back_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back_ButtonActionPerformed
        this.dispose();
        HomePage hp = new HomePage(jobRole, empID);
        hp.setVisible(true);
    }//GEN-LAST:event_Back_ButtonActionPerformed
    /**
     * Sort List
     * <p>
     * Sorts through the table with the Enquiries data.
     */
    public void sort(){
        DefaultTableModel dm = (DefaultTableModel) enquiries_tbl.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dm);
        enquiries_tbl.setRowSorter(trs);
    }
    /**
     * Filter List
     * <p>
     * Filters through the list with the Enquiries data.
     */
    public void filter(String s){
        DefaultTableModel dm = (DefaultTableModel) enquiries_tbl.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dm);
        enquiries_tbl.setRowSorter(trs);
        
        if (!"None".equals(s) || s.isEmpty()){
            trs.setRowFilter(RowFilter.regexFilter(s));
        } else {
            enquiries_tbl.setRowSorter(trs);
        }
    }
     /**
     * Table
     * <p>
     * Converts a result set to a table model
     * @param rs
     * @return converted result set.
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
            java.util.logging.Logger.getLogger(ViewEnquiries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewEnquiries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewEnquiries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewEnquiries.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewEnquiries("Office Manager", "FG12").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back_Button;
    private javax.swing.JButton HomePage_Button;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton Logout_Button;
    private javax.swing.JButton Respond_Button;
    private javax.swing.JButton Search_Button;
    private javax.swing.JTextField ViewEnquiries_Field;
    private javax.swing.JPanel ViewEnquiries_Panel;
    private javax.swing.JTable enquiries_tbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel search_lbl;
    private javax.swing.JTextField search_txt;
    // End of variables declaration//GEN-END:variables
}
