/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;

/**
 *
 * @author Adem1
 */
public class FlexibleDiscountTest {
    
    
    
    public FlexibleDiscountTest(){
    
        
    }
    
    @Test
    public void newDiscount() throws SQLException {
        ArrayList<DiscountBand> bands;
        System.out.println("make new flexible discount");
        bands = new ArrayList<>();
        bands.add(new DiscountBand(0,10,0.3));
        bands.add(new DiscountBand(11,22,0.4));
        bands.add(new DiscountBand(23,40,0.5));
        System.out.println("number of bands : " + bands.size());
       
        
        FlexibleDiscount discount = new FlexibleDiscount(bands,"ACC0003");
        
    }
//    @Test
//    public void existingDiscount() throws SQLException {
//        System.out.println("read flexible discount");
//        
//        
//        
//    }
}
