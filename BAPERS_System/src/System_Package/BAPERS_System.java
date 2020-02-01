/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System_Package;

import GUI_Package.Login_Form;

/**
 *
 * @author Felice Gregorio
 */
public class BAPERS_System {

    
    public BAPERS_System(){
        Login_Form lf = new Login_Form();
        lf.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new BAPERS_System();
    }
}
