/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process_Package;

import Database_Package.DBConnectivity;
import java.sql.SQLException;
import org.junit.Test;

/**
 *
 * @author Adem1
 */
public class InvoiceTest {
    private final DBConnectivity myDB = new DBConnectivity();
    
    //for this test to run you need an invoice with id = 1 in the database
    
    public InvoiceTest(){}
    
    
    @Test
    public void existingInvoice() throws SQLException {
        System.out.println("read invoice");
        
        Invoice invoice = new Invoice(0);
        
    }
}
