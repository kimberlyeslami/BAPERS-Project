/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Package;

import Account_Package.Customer;
import Database_Package.DBConnectivity;
import Process_Package.Job;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
public class SearchCustomer extends javax.swing.JFrame {
   /** The Employees job role*/
    private final String jobRole;
    /** The Employees ID. */
    private final String empID;
    

    /**
     * Creates new form SearchCustomer
     * @param jobRole Employees job role
     * @param empID The employee ID.
     */
    public SearchCustomer(String jobRole, String empID) {
        this.empID = empID;
        this.jobRole = jobRole;
        initComponents();
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

        jPanel1 = new javax.swing.JPanel();
        Back_Button = new javax.swing.JButton();
        upgrade_btn = new javax.swing.JButton();
        downgrade_btn = new javax.swing.JButton();
        delete_btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        customer_tbl = new javax.swing.JTable();
        customers_lbl = new javax.swing.JLabel();
        Search_Lbl = new javax.swing.JLabel();
        Search_Field = new javax.swing.JTextField();
        Search_Button = new javax.swing.JButton();
        type_lbl = new javax.swing.JLabel();
        type_cmbo = new javax.swing.JComboBox<>();
        viewValCust_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        SearchCustomer_Field = new javax.swing.JTextField();
        Logout_Button = new javax.swing.JButton();
        Homepage_Button = new javax.swing.JButton();
        LOGO = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Back_Button.setBackground(new java.awt.Color(0, 153, 255));
        Back_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Back_Button.setForeground(new java.awt.Color(255, 255, 255));
        Back_Button.setText("Back");
        Back_Button.setContentAreaFilled(false);
        Back_Button.setOpaque(true);
        Back_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_ButtonActionPerformed(evt);
            }
        });

        upgrade_btn.setBackground(new java.awt.Color(0, 153, 255));
        upgrade_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        upgrade_btn.setForeground(new java.awt.Color(255, 255, 255));
        upgrade_btn.setText("Upgrade");
        upgrade_btn.setContentAreaFilled(false);
        upgrade_btn.setOpaque(true);
        upgrade_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upgrade_btnActionPerformed(evt);
            }
        });

        downgrade_btn.setBackground(new java.awt.Color(0, 153, 255));
        downgrade_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        downgrade_btn.setForeground(new java.awt.Color(255, 255, 255));
        downgrade_btn.setText("Downgrade");
        downgrade_btn.setContentAreaFilled(false);
        downgrade_btn.setOpaque(true);
        downgrade_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downgrade_btnActionPerformed(evt);
            }
        });

        delete_btn.setBackground(new java.awt.Color(0, 153, 255));
        delete_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        delete_btn.setForeground(new java.awt.Color(255, 255, 255));
        delete_btn.setText("Delete");
        delete_btn.setContentAreaFilled(false);
        delete_btn.setOpaque(true);
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        DBConnectivity myDB = new DBConnectivity();
        myDB.connect();
        ResultSet rs2 = myDB.read("SELECT customer_ID AS 'Customer ID', first_name AS 'First Name', last_name AS 'Surname', address AS 'Address', city AS 'City', country AS 'Country', postcode AS 'Postcode', email AS 'E-Mail', phone_num AS 'Number', account_type AS 'Type' FROM Customers");
        customer_tbl.setAutoCreateRowSorter(true);
        customer_tbl.setModel(resultSetToTableModel(rs2));
        myDB.closeConnection();
        customer_tbl.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(customer_tbl);

        customers_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        customers_lbl.setText("Customers:");

        Search_Lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Search_Lbl.setText("Search:");

        Search_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_FieldActionPerformed(evt);
            }
        });

        Search_Button.setBackground(new java.awt.Color(0, 153, 255));
        Search_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Search_Button.setForeground(new java.awt.Color(255, 255, 255));
        Search_Button.setText("Search");
        Search_Button.setContentAreaFilled(false);
        Search_Button.setOpaque(true);
        Search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_ButtonActionPerformed(evt);
            }
        });

        type_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        type_lbl.setText("Account Type:");

        type_cmbo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Normal", "Valued" }));
        type_cmbo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                type_cmboItemStateChanged(evt);
            }
        });

        viewValCust_btn.setBackground(new java.awt.Color(0, 153, 255));
        viewValCust_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        viewValCust_btn.setForeground(new java.awt.Color(255, 255, 255));
        viewValCust_btn.setText("View Valued Customers");
        viewValCust_btn.setContentAreaFilled(false);
        viewValCust_btn.setOpaque(true);
        viewValCust_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewValCust_btnActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        SearchCustomer_Field.setBackground(new java.awt.Color(0, 153, 255));
        SearchCustomer_Field.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        SearchCustomer_Field.setForeground(new java.awt.Color(255, 255, 255));
        SearchCustomer_Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SearchCustomer_Field.setText("Search Customer");
        SearchCustomer_Field.setBorder(null);
        SearchCustomer_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchCustomer_FieldActionPerformed(evt);
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

        Homepage_Button.setBackground(new java.awt.Color(255, 255, 255));
        Homepage_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Homepage_Button.setForeground(new java.awt.Color(0, 153, 255));
        Homepage_Button.setText("Home Page");
        Homepage_Button.setBorder(null);
        Homepage_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Homepage_ButtonActionPerformed(evt);
            }
        });

        LOGO.setText("LOGO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Logout_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Homepage_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(SearchCustomer_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Homepage_Button)
                                    .addComponent(Logout_Button)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(LOGO, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(SearchCustomer_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Back_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(144, 144, 144)
                        .addComponent(upgrade_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(downgrade_btn)
                        .addGap(151, 151, 151)
                        .addComponent(delete_btn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customers_lbl)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Search_Lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Search_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Search_Button)
                        .addGap(71, 71, 71)
                        .addComponent(type_lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(type_cmbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(viewValCust_btn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Search_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search_Lbl)
                    .addComponent(Search_Button)
                    .addComponent(type_lbl)
                    .addComponent(type_cmbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewValCust_btn))
                .addGap(38, 38, 38)
                .addComponent(customers_lbl)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Back_Button)
                    .addComponent(delete_btn)
                    .addComponent(upgrade_btn)
                    .addComponent(downgrade_btn))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /** Search Button
     *<p>
     * Search for the customer.
     */
    private void Search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_ButtonActionPerformed
        String s = Search_Field.getText();
        filter(s);
    }//GEN-LAST:event_Search_ButtonActionPerformed

    private void SearchCustomer_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchCustomer_FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchCustomer_FieldActionPerformed

    private void Search_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Search_FieldActionPerformed
     /**
     * Cancel button
     * <p>
     * Closes the form and takes you back to the previous page
     */
    private void Back_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back_ButtonActionPerformed
        this.dispose();
        HomePage hp = new HomePage(jobRole, empID);
        hp.setVisible(true);
    }//GEN-LAST:event_Back_ButtonActionPerformed
     /** Delete Button
      * <p>
      * Deletes a customer from the table.
      */
    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        int row = customer_tbl.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Please select a Customer!", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            Customer cust = new Customer(customer_tbl.getValueAt(row, 0).toString());
            cust.delete();
            DefaultTableModel dtm = (DefaultTableModel) customer_tbl.getModel();
            dtm.removeRow(row);
        }
    }//GEN-LAST:event_delete_btnActionPerformed
    /** Upgrade Button
      * <p>
      * Upgrades a customer from the table.
      */
    private void upgrade_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upgrade_btnActionPerformed
        DBConnectivity myDB = new DBConnectivity();
        myDB.connect();
        Connection con = myDB.getConn();
        ArrayList<Job> jobs = new ArrayList<>();
        
        int row = customer_tbl.getSelectedRow();
        
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Please select a Customer!", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Customer cust = new Customer(customer_tbl.getValueAt(row, 0).toString());
                
                con.setAutoCommit(false);
                ResultSet rs = myDB.read("SELECT job_code FROM Job INNER JOIN Invoice USING(invoice_ID) WHERE customer_ID = '" + cust.getCustID() + "' AND payment_status = 'Pending'");
                while (rs.next()){
                    jobs.add(new Job(rs.getString("job_code")));
                }
                cust.upgradeAccount(new Date(new GregorianCalendar(2018, LocalDate.now().plusMonths(1).getMonthValue(), 10).getTimeInMillis()), 0, "Active", jobs);
                
                customer_tbl.setValueAt("Valued", row, 9);
                
                con.commit();
                
            } catch (SQLException ex) {
                Logger.getLogger(SearchCustomer.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(SearchCustomer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_upgrade_btnActionPerformed
    /** type state changed
      * <p>
      * Filters through the list with the selected type.
      */
    private void type_cmboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_type_cmboItemStateChanged
        String s = type_cmbo.getSelectedItem().toString();
        filter(s);
    }//GEN-LAST:event_type_cmboItemStateChanged
    /** Downgrade Button
      * <p>
      * Downgrades a customer from the table.
      */
    private void downgrade_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downgrade_btnActionPerformed
        int row = customer_tbl.getSelectedRow();
        
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Please select a Customer!", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            Customer cust = new Customer(customer_tbl.getValueAt(row, 0).toString());
            cust.downgradeAccount();
            customer_tbl.setValueAt("Normal", row, 9);
        }
    }//GEN-LAST:event_downgrade_btnActionPerformed
     /** View Valued customer Button
      * <p>
      * closes the form and opens the valued customer form.
      */
    private void viewValCust_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewValCust_btnActionPerformed
        this.dispose();
        ValuedCustomers vc = new ValuedCustomers(jobRole, empID);
        vc.setVisible(true);
    }//GEN-LAST:event_viewValCust_btnActionPerformed
    /**
     * Logout Button
     * <p>
     * Closes the form and takes you back to the login form.
     */
    private void Logout_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout_ButtonActionPerformed
        this.dispose();
        Login_Form l = new Login_Form();
        l.setVisible(true);
    }//GEN-LAST:event_Logout_ButtonActionPerformed
     /**
     * HomePage Button
     * <p>
     * Closes the form and takes you back to the home page form.
     */
    private void Homepage_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Homepage_ButtonActionPerformed
        this.dispose();
        HomePage hp = new HomePage(jobRole, empID);
        hp.setVisible(true);
    }//GEN-LAST:event_Homepage_ButtonActionPerformed
    /**Sort
    *<p>
    *Sorts through the table.
    */
    public void sort(){
        DefaultTableModel dm = (DefaultTableModel) customer_tbl.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dm);
        customer_tbl.setRowSorter(trs);
    }
    /**Filter
    *<p>
    *Filters through the table.
    */
    public void filter(String s){
        DefaultTableModel dm = (DefaultTableModel) customer_tbl.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dm);
        customer_tbl.setRowSorter(trs);
        
        if (!"None".equals(s) || s.isEmpty()){
            trs.setRowFilter(RowFilter.regexFilter(s));
        } else {
            customer_tbl.setRowSorter(trs);
        }
    }
    
     /**
     * Table
     * <p>
     * Creates a table 
     * @return populated table with the Customer data.
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
            java.util.logging.Logger.getLogger(SearchCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchCustomer("Office Manager", "FG12").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back_Button;
    private javax.swing.JButton Homepage_Button;
    private javax.swing.JLabel LOGO;
    private javax.swing.JButton Logout_Button;
    private javax.swing.JTextField SearchCustomer_Field;
    private javax.swing.JButton Search_Button;
    private javax.swing.JTextField Search_Field;
    private javax.swing.JLabel Search_Lbl;
    private javax.swing.JTable customer_tbl;
    private javax.swing.JLabel customers_lbl;
    private javax.swing.JButton delete_btn;
    private javax.swing.JButton downgrade_btn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> type_cmbo;
    private javax.swing.JLabel type_lbl;
    private javax.swing.JButton upgrade_btn;
    private javax.swing.JButton viewValCust_btn;
    // End of variables declaration//GEN-END:variables
}