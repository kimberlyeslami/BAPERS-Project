/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import java.sql.SQLException;
import java.util.HashMap;
import org.junit.Test;

/**
 *
 * @author Adem Aybar
 */
public class VariableDiscountTest {
    
        public VariableDiscountTest(){}
    
      @Test
    public void newDiscount() throws SQLException {
        System.out.println("make new variable discount");
        
        HashMap<Integer, Double> taskDiscounts = new HashMap<>();
        taskDiscounts.put(1,0.12);
        taskDiscounts.put(2,0.80);
        taskDiscounts.put(3,0.95);
        
        VariableDiscount discount = new VariableDiscount(taskDiscounts,"ACC0003");
        
    }
//    @Test
//    public void existingDiscount() throws SQLException {
//        System.out.println("read variable discount");
//        
//        VariableDiscount discount = new VariableDiscount(35);
//        
//    }
}
