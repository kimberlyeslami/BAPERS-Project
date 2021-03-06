/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Package;

import Account_Package.Customer;
import Account_Package.VariableDiscount;
import Process_Package.Task;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author umahm
 */
public class VariableDiscountForm extends javax.swing.JFrame {
   /** The Employees job role. */
    private final String jobRole;
    /** The Customer thats being assigned the discount*/
    private Customer cust;
    /** HashMap containing the discounts */
    public HashMap<Integer, Double> discounts = new HashMap<>();
    /** The Employees ID. */
    private final String empID;

    /**
     * Creates new form VariableDiscount
     * @param jobRole employee's job role
     * @param cust customer
     * @param empID
    */
    public VariableDiscountForm(String jobRole, Customer cust, String empID) {
        this.empID = empID;
        this.jobRole = jobRole;
        this.cust = cust;
        initComponents();
        Toolkit tool = Toolkit.getDefaultToolkit();
        int height = (int) tool.getScreenSize().getHeight();
        int width = (int) tool.getScreenSize().getWidth();
        this.setSize(width, height);
        CustomerID_Label.setText("Customer ID: " + cust.getCustID());
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
        Delete_Button = new javax.swing.JButton();
        Assign_Button = new javax.swing.JButton();
        Back_Button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tasks_Table = new javax.swing.JTable();
        ListOfTasks_Label = new javax.swing.JLabel();
        CustomerID_Label = new javax.swing.JLabel();
        Discount_Label = new javax.swing.JLabel();
        Discount_Field = new javax.swing.JTextField();
        Add_Button = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        Logout_Button = new javax.swing.JButton();
        HomePage_Button = new javax.swing.JButton();
        VariableDiscount_Field = new javax.swing.JTextField();
        Logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Delete_Button.setBackground(new java.awt.Color(0, 153, 255));
        Delete_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Delete_Button.setForeground(new java.awt.Color(255, 255, 255));
        Delete_Button.setText("Delete");
        Delete_Button.setBorder(null);
        Delete_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete_ButtonActionPerformed(evt);
            }
        });

        Assign_Button.setBackground(new java.awt.Color(0, 153, 255));
        Assign_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Assign_Button.setForeground(new java.awt.Color(255, 255, 255));
        Assign_Button.setText("Assign");
        Assign_Button.setBorder(null);
        Assign_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Assign_ButtonActionPerformed(evt);
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

        Tasks_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Task ID", "Discount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tasks_Table);

        ListOfTasks_Label.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        ListOfTasks_Label.setText("List of Tasks:");

        CustomerID_Label.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        CustomerID_Label.setText("Customer ID:");

        Discount_Label.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Discount_Label.setText("Discount:");

        Add_Button.setBackground(new java.awt.Color(0, 153, 255));
        Add_Button.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Add_Button.setForeground(new java.awt.Color(255, 255, 255));
        Add_Button.setText("Add Task");
        Add_Button.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Add_Button.setContentAreaFilled(false);
        Add_Button.setOpaque(true);
        Add_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_ButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

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
        HomePage_Button.setText("Home Page");
        HomePage_Button.setBorder(null);

        VariableDiscount_Field.setBackground(new java.awt.Color(0, 153, 255));
        VariableDiscount_Field.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        VariableDiscount_Field.setForeground(new java.awt.Color(255, 255, 255));
        VariableDiscount_Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        VariableDiscount_Field.setText("Variable Discount");
        VariableDiscount_Field.setBorder(null);
        VariableDiscount_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VariableDiscount_FieldActionPerformed(evt);
            }
        });

        Logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\kimbe\\Documents\\Uni Work\\Second Year Uni\\Team project\\WhatsApp Image 2018-04-18 at 2.43.23 PM.jpeg")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logo)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(974, 974, 974)
                        .addComponent(Logout_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HomePage_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(VariableDiscount_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Logout_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HomePage_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(VariableDiscount_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
            .addComponent(Logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Back_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(Delete_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Assign_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(CustomerID_Label)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Discount_Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Discount_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Add_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ListOfTasks_Label))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CustomerID_Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ListOfTasks_Label)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Discount_Label)
                        .addComponent(Discount_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Add_Button)))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Assign_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Back_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VariableDiscount_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VariableDiscount_FieldActionPerformed
    }//GEN-LAST:event_VariableDiscount_FieldActionPerformed
     /**
     * Logout Button
     * <p>
     * Closes the form and takes you back to the login form.
     */ 
    private void Logout_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout_ButtonActionPerformed
        Login_Form lf = new Login_Form();
        lf.setVisible(true);
    }//GEN-LAST:event_Logout_ButtonActionPerformed
     /**
     * Back Button
     * <p>
     * Closes the form taking you back to the previous form.
     */
    private void Back_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back_ButtonActionPerformed
        this.dispose();
        AssignDiscountPlan adp = new AssignDiscountPlan(jobRole, cust, empID);
        adp.setVisible(true);
    }//GEN-LAST:event_Back_ButtonActionPerformed
    /** Assign Discount button
     * <p>
     * Assigns the discount plan to the customer.
     */
    private void Assign_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_ButtonActionPerformed
        try {
            new VariableDiscount(discounts ,cust.getCustID());
            this.dispose();
            AssignDiscountPlan adp = new AssignDiscountPlan(jobRole, cust, empID);
            adp.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(VariableDiscountForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Assign_ButtonActionPerformed
    /** Delete button
    * <p>
    * Removes selected Task from the table. 
    */
    private void Delete_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete_ButtonActionPerformed
        int row = Tasks_Table.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(null, "Please select a Customer!", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            Object tasksID = Tasks_Table.getValueAt(row,0);
            Task tsk = new Task(Integer.parseInt(tasksID.toString()));
        }
    }//GEN-LAST:event_Delete_ButtonActionPerformed
    /** Add button
    * <p>
    * Adds a new Task the table. 
    */
    private void Add_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_ButtonActionPerformed
        AddTask at = new AddTask(this, empID);
        at.setVisible(true);
    }//GEN-LAST:event_Add_ButtonActionPerformed
      /**
     * Sort Table
     * <p>
     * Sorts through the table with the Discount data.
     */
    
    public void sort(){
        DefaultTableModel dm = (DefaultTableModel) Tasks_Table.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dm);
        Tasks_Table.setRowSorter(trs);
    }
     /**
     * Filter Table
     * <p>
     * Filters through the list with the Discount data.
     */
    
    public void filter(String s){
        DefaultTableModel dm = (DefaultTableModel) Tasks_Table.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(dm);
        Tasks_Table.setRowSorter(trs);
        
        if (!"None".equals(s) || s.isEmpty()){
            trs.setRowFilter(RowFilter.regexFilter(s));
        } else {
            Tasks_Table.setRowSorter(trs);
        }
    }
     /**
     * Table
     * <p>
     * Creates a table 
     * @return populated table with the discount data.
     */
    public static DefaultTableModel ResultSetToTableModel(ResultSet rs) {
        ResultSetMetaData md;
        
        Vector<String> columnNames = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();
        
        try {
            md = rs.getMetaData();
            
            // names of columns
            int columnCount = md.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(md.getColumnName(i));
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
            java.util.logging.Logger.getLogger(VariableDiscountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VariableDiscountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VariableDiscountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VariableDiscountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VariableDiscountForm("Office Manager", new Customer("ACC0001"), "FG12").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_Button;
    private javax.swing.JButton Assign_Button;
    private javax.swing.JButton Back_Button;
    private javax.swing.JLabel CustomerID_Label;
    private javax.swing.JButton Delete_Button;
    public javax.swing.JTextField Discount_Field;
    private javax.swing.JLabel Discount_Label;
    private javax.swing.JButton HomePage_Button;
    private javax.swing.JLabel ListOfTasks_Label;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton Logout_Button;
    public javax.swing.JTable Tasks_Table;
    private javax.swing.JTextField VariableDiscount_Field;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
