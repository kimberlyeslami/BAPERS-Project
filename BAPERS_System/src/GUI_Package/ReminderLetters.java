/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_Package;

import Database_Package.DBConnectivity;
import System_Package.Reader;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FilenameFilter;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListModel;

/**
 *
 * @author FMG97
 */
public class ReminderLetters extends javax.swing.JFrame {
    /** The Employees ID. */
    private final String empID;
    /** The Employee job role. */
    private final String jobRole;
     /** The connection to the Database. */
    private final DBConnectivity myDB = new DBConnectivity();
    /** The Backup File. */
    private final File file = new File("ReminderLetters/");
    
    private final String[] values;
                
    

    /**
     * Creates new form ReminderLetters.
     * 
     * @param jobRole The employee jobROle
     * @param empID The employee ID
     */
    public ReminderLetters(String jobRole, String empID) {
        this.empID = empID;
        initComponents();
        
        DefaultListModel<String> listModel = (DefaultListModel<String>) letters_lst.getModel();
        
        int size = listModel.getSize();
        
        this.values = new String[size];
        this.jobRole = jobRole;
        
        for (int i = 0; i < size; i++){
            values[i] = listModel.get(0);
        }
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
        view_btn = new javax.swing.JButton();
        RestoreFrom_scrl = new javax.swing.JScrollPane();
        letters_lst = new javax.swing.JList<>();
        Search_lbl = new javax.swing.JLabel();
        Search_txt = new javax.swing.JTextField();
        search_btn = new javax.swing.JButton();
        type_lbl = new javax.swing.JLabel();
        type_cmbo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        reminderLetters_lbl = new javax.swing.JLabel();
        Homepage_btn = new javax.swing.JButton();
        Logout_btn = new javax.swing.JButton();

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

        view_btn.setBackground(new java.awt.Color(0, 153, 255));
        view_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        view_btn.setForeground(new java.awt.Color(255, 255, 255));
        view_btn.setText("View");
        view_btn.setBorder(null);
        view_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                view_btnActionPerformed(evt);
            }
        });

        RestoreFrom_scrl.setToolTipText("");
        RestoreFrom_scrl.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        letters_lst.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        DefaultListModel lstModel = new DefaultListModel();

        FilenameFilter ff = (File dir, String name1) -> name1.startsWith("ACC");

        File[] fileList = file.listFiles(ff);
        for(File f : fileList) {
            lstModel.addElement(f.getName());
        }
        letters_lst.setModel(lstModel);
        RestoreFrom_scrl.setViewportView(letters_lst);

        Search_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Search_lbl.setText("Search:");

        Search_txt.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        search_btn.setBackground(new java.awt.Color(0, 153, 255));
        search_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        search_btn.setForeground(new java.awt.Color(255, 255, 255));
        search_btn.setText("Search");
        search_btn.setBorder(null);
        search_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_btnActionPerformed(evt);
            }
        });

        type_lbl.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        type_lbl.setText("Type:");

        type_cmbo.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        type_cmbo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "First Reminder Letter", "Second Reminder Letter" }));
        type_cmbo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                type_cmboItemStateChanged(evt);
            }
        });
        type_cmbo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type_cmboActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        reminderLetters_lbl.setBackground(new java.awt.Color(0, 153, 255));
        reminderLetters_lbl.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        reminderLetters_lbl.setForeground(new java.awt.Color(255, 255, 255));
        reminderLetters_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reminderLetters_lbl.setText("Reminder Letters");
        reminderLetters_lbl.setOpaque(true);

        Homepage_btn.setBackground(new java.awt.Color(255, 255, 255));
        Homepage_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Homepage_btn.setForeground(new java.awt.Color(0, 153, 255));
        Homepage_btn.setText("Home Page");
        Homepage_btn.setBorder(null);
        Homepage_btn.setMaximumSize(new java.awt.Dimension(65, 19));
        Homepage_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Homepage_btnActionPerformed(evt);
            }
        });

        Logout_btn.setBackground(new java.awt.Color(255, 255, 255));
        Logout_btn.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        Logout_btn.setForeground(new java.awt.Color(0, 153, 255));
        Logout_btn.setText("Logout");
        Logout_btn.setBorder(null);
        Logout_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Logout_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Logout_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Homepage_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(reminderLetters_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Homepage_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Logout_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reminderLetters_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Search_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Search_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(type_lbl)
                .addGap(18, 18, 18)
                .addComponent(type_cmbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(Back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(view_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(RestoreFrom_scrl, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Search_txt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(search_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Search_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(type_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(type_cmbo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(RestoreFrom_scrl, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Back_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(view_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Homepage_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Homepage_btnActionPerformed
        this.dispose();
        HomePage hp = new HomePage(jobRole, empID);
        hp.setVisible(true);
    }//GEN-LAST:event_Homepage_btnActionPerformed

    private void Logout_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Logout_btnActionPerformed
        this.dispose();
        Login_Form l = new Login_Form();
        l.setVisible(true);
    }//GEN-LAST:event_Logout_btnActionPerformed

    private void Back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back_btnActionPerformed
        this.dispose();
        HomePage hp = new HomePage(jobRole, empID);
        hp.setVisible(true);
    }//GEN-LAST:event_Back_btnActionPerformed

    private void view_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_view_btnActionPerformed
        if (letters_lst.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "Please select a backup file to restore from!", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            viewLetter(letters_lst.getSelectedValue());
        }
    }//GEN-LAST:event_view_btnActionPerformed

    private void search_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_btnActionPerformed
        filter((DefaultListModel<String>) letters_lst.getModel(), Search_txt.getText());
    }//GEN-LAST:event_search_btnActionPerformed

    private void type_cmboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_type_cmboItemStateChanged
        switch (type_cmbo.getSelectedIndex()) {
            case 0:
                filter((DefaultListModel<String>) letters_lst.getModel(), "ACC");
                break;
            case 1:
                filter((DefaultListModel<String>) letters_lst.getModel(), "FirstReminder");
                break;
            default:
                filter((DefaultListModel<String>) letters_lst.getModel(), "SecondReminder");
                break;
        }
    }//GEN-LAST:event_type_cmboItemStateChanged

    private void type_cmboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_cmboActionPerformed

    }//GEN-LAST:event_type_cmboActionPerformed

    /**Sort
    *<p>
    *Sorts through the list.
    */
    public void sortList() {
        ListModel list = letters_lst.getModel();

        int size = list.getSize();

        String[] data = new String[size];
        for (int i = 0; i < size; i++) {
            data[i] = (String) list.getElementAt(i);
        }

        Arrays.sort(data);
        
        letters_lst.setListData(data);
    }
    
    public void filter(DefaultListModel<String> list, String s) {
        for (String val : values) {
            if (!val.contains(s)) {
                if (list.contains(val)) {
                    list.removeElement(val);
                }
            } else {
                if (!list.contains(val)) {
                    list.addElement(val);
                }
            }
        }
}
    
    public void viewLetter(String fileName){
        Reader reader = new Reader("ReminderLetters/" + fileName);
        String letter = reader.readLetter();
        
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        JTextArea letter_txt = new JTextArea(letter);
        
        JPanel title = new JPanel();
        title.setLayout(new GridLayout());
        JLabel title_lbl = new JLabel("Reminder Letter");
        title_lbl.setBackground(new java.awt.Color(0, 153, 255));
        title_lbl.setFont(new java.awt.Font("Arial", 1, 14));
        title_lbl.setForeground(new java.awt.Color(255, 255, 255));
        title_lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_lbl.setOpaque(true);
        title.add(title_lbl);
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout());
        JButton cancel_btn = new JButton();
        cancel_btn.setText("Cancel");
        cancel_btn.setFont(new java.awt.Font("Arial", 1, 13));
        cancel_btn.setBackground(new java.awt.Color(0, 153, 255));
        cancel_btn.setForeground(new java.awt.Color(255, 255, 255));
        cancel_btn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancel_btn.setContentAreaFilled(false);
        cancel_btn.setOpaque(true);
        
        JButton print_btn = new JButton();
        print_btn.setText("Print");
        print_btn.setFont(new java.awt.Font("Arial", 1, 13));
        print_btn.setBackground(new java.awt.Color(0, 153, 255));
        print_btn.setForeground(new java.awt.Color(255, 255, 255));
        print_btn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        print_btn.setContentAreaFilled(false);
        print_btn.setOpaque(true);
        
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        print_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageFormat header = new MessageFormat("First Reminder Letter" + "\n");
                MessageFormat footer = new MessageFormat("Page{0, number, integer}");

                try {
                    letter_txt.print(header, footer);
                } catch (PrinterException ex) {
                    Logger.getLogger(ReminderLetters.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons.add(cancel_btn);
        buttons.add(print_btn);
        
        frame.add(title, BorderLayout.NORTH);
        frame.add(letter_txt, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
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
            java.util.logging.Logger.getLogger(ReminderLetters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReminderLetters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReminderLetters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReminderLetters.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReminderLetters("Office Manager", "FG12").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back_btn;
    private javax.swing.JButton Homepage_btn;
    private javax.swing.JButton Logout_btn;
    private javax.swing.JScrollPane RestoreFrom_scrl;
    private javax.swing.JLabel Search_lbl;
    private javax.swing.JTextField Search_txt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JList<String> letters_lst;
    private javax.swing.JLabel reminderLetters_lbl;
    private javax.swing.JButton search_btn;
    private javax.swing.JComboBox<String> type_cmbo;
    private javax.swing.JLabel type_lbl;
    private javax.swing.JButton view_btn;
    // End of variables declaration//GEN-END:variables
}