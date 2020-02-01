/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Package;

import Account_Package.Employee;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author umahm, Kimberly Eslami
 * @version (11/04/18) version 2
 * @since (03/04/18) version 1
 */
public class ResetPassword extends javax.swing.JFrame {

    /** The Employees ID. */
    private String empID;
    /** The employee thats changing their password. */
    private Employee emp;

    /**
     * Creates new form ResetPassword
     *
     * @param empID Employee ID.
     * @throws java.sql.SQLException
     */
    public ResetPassword(String empID) throws SQLException {
        this.empID = empID;
        emp = new Employee(empID);
        initComponents();
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
        Submit_btn = new javax.swing.JButton();
        Confirm_txt = new javax.swing.JPasswordField();
        Confirm_lbl = new javax.swing.JLabel();
        NewPassword_txt = new javax.swing.JPasswordField();
        NewPassword_lbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ResetPassword_lbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Back_btn.setBackground(new java.awt.Color(0, 153, 255));
        Back_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Back_btn.setForeground(new java.awt.Color(255, 255, 255));
        Back_btn.setText("Back");
        Back_btn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Back_btn.setContentAreaFilled(false);
        Back_btn.setOpaque(true);
        Back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back_btnActionPerformed(evt);
            }
        });

        Submit_btn.setBackground(new java.awt.Color(0, 153, 255));
        Submit_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Submit_btn.setForeground(new java.awt.Color(255, 255, 255));
        Submit_btn.setText("Submit");
        Submit_btn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Submit_btn.setContentAreaFilled(false);
        Submit_btn.setOpaque(true);
        Submit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Submit_btnActionPerformed(evt);
            }
        });

        Confirm_txt.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        Confirm_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Confirm_lbl.setText("Confirm New Password:");

        NewPassword_txt.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        NewPassword_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        NewPassword_lbl.setText("Enter New Password:");

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        ResetPassword_lbl.setBackground(new java.awt.Color(0, 153, 255));
        ResetPassword_lbl.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        ResetPassword_lbl.setForeground(new java.awt.Color(255, 255, 255));
        ResetPassword_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ResetPassword_lbl.setText("Reset Password");
        ResetPassword_lbl.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(ResetPassword_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(ResetPassword_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(Submit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(NewPassword_lbl)
                                .addGap(18, 18, 18)
                                .addComponent(NewPassword_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Confirm_lbl)
                                .addGap(18, 18, 18)
                                .addComponent(Confirm_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(120, 120, 120))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NewPassword_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NewPassword_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Confirm_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Confirm_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Submit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
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
     /**
     * Back Button
     * <p>
     * Closes the form taking you back to the previous form.
     */
    private void Back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back_btnActionPerformed
        
        this.dispose();
        Login_Form l = new Login_Form();
        l.setVisible(true);
    }//GEN-LAST:event_Back_btnActionPerformed
     /** Submit button 
     * <p>
     * Replaces the employees password with the new password
     */
    private void Submit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Submit_btnActionPerformed
       

        String password = new String(NewPassword_txt.getPassword());
        String confirm = new String(Confirm_txt.getPassword());
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again. ");
        } else if (password.isEmpty() && confirm.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter Text in All Fields");
        } else {
            emp.setPassword(confirm);
            JOptionPane.showMessageDialog(null, "Password Updated");
            this.dispose();

        }
        
        


    }//GEN-LAST:event_Submit_btnActionPerformed

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
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResetPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ResetPassword("BH1234").setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ResetPassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back_btn;
    private javax.swing.JLabel Confirm_lbl;
    private javax.swing.JPasswordField Confirm_txt;
    private javax.swing.JLabel NewPassword_lbl;
    private javax.swing.JPasswordField NewPassword_txt;
    private javax.swing.JLabel ResetPassword_lbl;
    private javax.swing.JButton Submit_btn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}