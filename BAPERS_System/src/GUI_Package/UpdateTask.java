/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Package;

import Process_Package.Task;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author umahm, Kimberly Eslami
 * @version (11/04/18) version 2
 * @since (03/04/18) version 1
 */
public class UpdateTask extends javax.swing.JFrame {
    /** The Employees ID. */
    private final String empID;
    /** The Employees job role. */
    private final String jobRole;
    /** The Tasks ID. */
    private final int taskID;
    /** The Task being updated. */
    private final Task task;
    

    /**
     * Creates new form UpdateTaskForm.
     *
     * @param jobRole employees role
     * @param taskID tasks id
     * @param empID
     */
    public UpdateTask(String jobRole, int taskID, String empID) {
        this.empID = empID;
        this.jobRole = jobRole;
        this.taskID = taskID;
        task = new Task(taskID);

        initComponents();
        Toolkit tool = Toolkit.getDefaultToolkit();
        int height = (int) tool.getScreenSize().getHeight();
        int width = (int) tool.getScreenSize().getWidth();
        this.setSize(width, height);
        
        TaskID_lbl.setText("Task ID: " + taskID);
        Description_txt.setText(task.getDescription());
        location_txt.setText(task.getLocation());
        ShelfSlot_txt.setText(task.getShelfSlot());
        Price_txt.setText(String.valueOf(task.getPrice()));
        Duration_txt.setText(String.valueOf(task.getDuration()));

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
        Cancel_btn = new javax.swing.JButton();
        Save_btn = new javax.swing.JButton();
        Location_lbl = new javax.swing.JLabel();
        location_txt = new javax.swing.JTextField();
        Duration_lbl = new javax.swing.JLabel();
        Duration_txt = new javax.swing.JTextField();
        Description_scrl = new javax.swing.JScrollPane();
        Description_txt = new javax.swing.JTextArea();
        Price_lbl = new javax.swing.JLabel();
        Price_txt = new javax.swing.JTextField();
        ShelfSlot_lbl = new javax.swing.JLabel();
        ShelfSlot_txt = new javax.swing.JTextField();
        Description_lbl = new javax.swing.JLabel();
        TaskID_lbl = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        UpdateTasks_lbl = new javax.swing.JLabel();
        LogOut_btn = new javax.swing.JButton();
        HomePage_btn = new javax.swing.JButton();
        Logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Cancel_btn.setBackground(new java.awt.Color(0, 153, 255));
        Cancel_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Cancel_btn.setForeground(new java.awt.Color(255, 255, 255));
        Cancel_btn.setText("Cancel");
        Cancel_btn.setBorder(null);
        Cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancel_btnActionPerformed(evt);
            }
        });

        Save_btn.setBackground(new java.awt.Color(0, 153, 255));
        Save_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Save_btn.setForeground(new java.awt.Color(255, 255, 255));
        Save_btn.setText("Save");
        Save_btn.setBorder(null);
        Save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_btnActionPerformed(evt);
            }
        });

        Location_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Location_lbl.setText("Location:");

        location_txt.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        Duration_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Duration_lbl.setText("Duration:");

        Duration_txt.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        Description_txt.setColumns(20);
        Description_txt.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        Description_txt.setRows(5);
        Description_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Description_txtKeyTyped(evt);
            }
        });
        Description_scrl.setViewportView(Description_txt);

        Price_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Price_lbl.setText("Price:");

        Price_txt.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        ShelfSlot_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        ShelfSlot_lbl.setText("Shelf Slot:");

        ShelfSlot_txt.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        Description_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Description_lbl.setText("Description:");

        TaskID_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        TaskID_lbl.setText("ID:");

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        UpdateTasks_lbl.setBackground(new java.awt.Color(0, 153, 255));
        UpdateTasks_lbl.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        UpdateTasks_lbl.setForeground(new java.awt.Color(255, 255, 255));
        UpdateTasks_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        UpdateTasks_lbl.setText("Update Tasks");
        UpdateTasks_lbl.setOpaque(true);

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

        Logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\kimbe\\Documents\\Uni Work\\Second Year Uni\\Team project\\WhatsApp Image 2018-04-18 at 2.43.23 PM.jpeg")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(Logo)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LogOut_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(HomePage_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(UpdateTasks_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HomePage_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LogOut_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(UpdateTasks_lbl)
                .addContainerGap())
            .addComponent(Logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(642, 642, 642)
                        .addComponent(Save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Description_lbl)
                            .addComponent(TaskID_lbl)
                            .addComponent(Description_scrl, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Location_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(location_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Duration_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Price_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Price_txt)
                                    .addComponent(Duration_txt)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ShelfSlot_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ShelfSlot_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(179, 207, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShelfSlot_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShelfSlot_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TaskID_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(Description_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Price_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Price_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Duration_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Duration_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(location_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Location_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Description_scrl, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(Save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(Cancel_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(94, 94, 94))
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
     * Logout Button
     * <p>
     * Closes the form and takes you back to the login form.
     */ 
    private void LogOut_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOut_btnActionPerformed
   
        this.dispose();
        Login_Form l = new Login_Form();
        l.setVisible(true);
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

    private void Description_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Description_txtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_Description_txtKeyTyped
     /** Save button
     * <p>
     * Adds the new task information into the database.
     */
    private void Save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_btnActionPerformed
       
        String Price = Price_txt.getText();
        String Duration = Duration_txt.getText();
        String Description = Description_txt.getText();
        String Location = location_txt.getText();
        String ShelfSlot = ShelfSlot_txt.getText();
        if (Description.isEmpty() || Location.isEmpty() || ShelfSlot.isEmpty() || Price.isEmpty() || Duration.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter Text in All Fields ");
        } else {
            Double price = Double.parseDouble(Price);
            Double duration = Double.parseDouble(Duration);

            task.setDescription(Description);
            task.setDuration(duration);
            task.setLocation(Location);
            task.setPrice(price);
            task.setShelfSlot(ShelfSlot);
            JOptionPane.showMessageDialog(null, "Task Updated ");
            this.dispose();
            Tasks tk = new Tasks(jobRole, empID);
            tk.setVisible(true);
        }
    }//GEN-LAST:event_Save_btnActionPerformed
    /** Cancel button 
     * <p>
    * Closes the form and takes you back to the previous page.
    */
    private void Cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancel_btnActionPerformed
        this.dispose();
        Tasks tk = new Tasks(jobRole, empID);
        tk.setVisible(true);
    }//GEN-LAST:event_Cancel_btnActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateTask.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateTask("Office Manager", 1, "FG12").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel_btn;
    private javax.swing.JLabel Description_lbl;
    private javax.swing.JScrollPane Description_scrl;
    private javax.swing.JTextArea Description_txt;
    private javax.swing.JLabel Duration_lbl;
    private javax.swing.JTextField Duration_txt;
    private javax.swing.JButton HomePage_btn;
    private javax.swing.JLabel Location_lbl;
    private javax.swing.JButton LogOut_btn;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel Price_lbl;
    private javax.swing.JTextField Price_txt;
    private javax.swing.JButton Save_btn;
    private javax.swing.JLabel ShelfSlot_lbl;
    private javax.swing.JTextField ShelfSlot_txt;
    private javax.swing.JLabel TaskID_lbl;
    private javax.swing.JLabel UpdateTasks_lbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField location_txt;
    // End of variables declaration//GEN-END:variables
}
