/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Package;

import Account_Package.Customer;
import Account_Package.FixedDiscount;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umahm
 */
public class FixedDiscountForm extends javax.swing.JFrame {
    /** The Employee job role. */
    private final String jobRole;
    /** The Customer that the discount is applied to. */
    private Customer cust;
    /** The Employees ID. */
    private final String empID;
    
    /**
     * Creates new form FixedDiscount
     * @param jobRole Employee Job role
     * @param cust Customer applying discount to.
     * @param empID The employee ID.
     */
    
    public FixedDiscountForm(String jobRole, Customer cust, String empID) {
        this.empID = empID;
        this.jobRole = jobRole;
        this.cust = cust;
        initComponents();
        CustomerID_Label.setText("Customer ID: " + cust.getCustID());

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
        Assign_Button = new javax.swing.JButton();
        Back_Button = new javax.swing.JButton();
        DiscountRate_Label = new javax.swing.JLabel();
        DiscountRate_Field = new javax.swing.JTextField();
        CustomerID_Label = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Logout_Button = new javax.swing.JButton();
        HomePage_Button = new javax.swing.JButton();
        FixedDiscount_Field = new javax.swing.JTextField();
        LOGO_Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        DiscountRate_Label.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        DiscountRate_Label.setText("Discount Rate:");

        DiscountRate_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiscountRate_FieldActionPerformed(evt);
            }
        });

        CustomerID_Label.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        CustomerID_Label.setText("Customer ID:");

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
        HomePage_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomePage_ButtonActionPerformed(evt);
            }
        });

        FixedDiscount_Field.setBackground(new java.awt.Color(0, 153, 255));
        FixedDiscount_Field.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        FixedDiscount_Field.setForeground(new java.awt.Color(255, 255, 255));
        FixedDiscount_Field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FixedDiscount_Field.setText("Fixed Discount");
        FixedDiscount_Field.setBorder(null);
        FixedDiscount_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FixedDiscount_FieldActionPerformed(evt);
            }
        });

        LOGO_Label.setText("LOGO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LOGO_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Logout_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HomePage_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(FixedDiscount_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Logout_Button)
                            .addComponent(HomePage_Button))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FixedDiscount_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(LOGO_Label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addComponent(Assign_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addComponent(Back_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CustomerID_Label))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(DiscountRate_Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DiscountRate_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(CustomerID_Label)
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DiscountRate_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DiscountRate_Label))
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Assign_Button)
                    .addComponent(Back_Button))
                .addContainerGap(164, Short.MAX_VALUE))
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

    private void FixedDiscount_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FixedDiscount_FieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FixedDiscount_FieldActionPerformed
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
    private void HomePage_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomePage_ButtonActionPerformed
        this.dispose();
        HomePage hp = new HomePage(jobRole, empID);
        hp.setVisible(true);
    }//GEN-LAST:event_HomePage_ButtonActionPerformed
    /**
     * Assign Button
     * <p>
     * Assigns the discount plan to the customer.
     */
    private void Assign_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Assign_ButtonActionPerformed
        try {
            new FixedDiscount(Double.parseDouble(DiscountRate_Field.getText()), cust.getCustID());
            this.dispose();
            AssignDiscountPlan adp = new AssignDiscountPlan(jobRole, cust, empID);
            adp.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(FixedDiscountForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Assign_ButtonActionPerformed
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

    private void DiscountRate_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiscountRate_FieldActionPerformed
        
    }//GEN-LAST:event_DiscountRate_FieldActionPerformed

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
            java.util.logging.Logger.getLogger(FixedDiscountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FixedDiscountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FixedDiscountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FixedDiscountForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FixedDiscountForm("Office Manager", new Customer("ACC0001"), "FG12").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Assign_Button;
    private javax.swing.JButton Back_Button;
    private javax.swing.JLabel CustomerID_Label;
    private javax.swing.JTextField DiscountRate_Field;
    private javax.swing.JLabel DiscountRate_Label;
    private javax.swing.JTextField FixedDiscount_Field;
    private javax.swing.JButton HomePage_Button;
    private javax.swing.JLabel LOGO_Label;
    private javax.swing.JButton Logout_Button;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
