/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import java.sql.SQLException;
import org.junit.Test;

/**
 *
 * @author Adem Aybar
 */
public class FixedDiscountTest {
    
        public FixedDiscountTest(){}
    
      @Test
    public void newDiscount() throws SQLException {
        System.out.println("make new fixed discount");
        
        FixedDiscount discount = new FixedDiscount(0.2,"ACC0003");
        
    }
    @Test
    public void existingDiscount() throws SQLException {
        System.out.println("read fixed discount");
        
        FixedDiscount discount = new FixedDiscount(10);
        
    }
}
