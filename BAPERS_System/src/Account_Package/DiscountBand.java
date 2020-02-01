/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

/**
 * @author Adem Aybar
 * @version     (07/04/2018) version 1
 * @since       (07/04/2018) version 1
 */
public class DiscountBand{
        
        /**The lower limit of a discount band*/
        private int lowerLimit;
        /**The upper limit of a discount band*/
        private int upperLimit;
        /**the discount rate of a discount band*/
        private double discountRate;
     
        /**
         * Constructor for discount band.
         * @param lowerLimit the lower limit of the range.
         * @param upperLimit the upper limit of the range.
         * @param discountRate the discount rate of this band.
         */
        public DiscountBand(int lowerLimit, int  upperLimit, double discountRate){
            
            this.lowerLimit = lowerLimit;
            this.upperLimit = upperLimit;
            this.discountRate = discountRate;
        }
        /**
         * Gets the lower limit.
         * @return The lower limit for this discount band
         */
        public int getLowerLimit(){
            return lowerLimit;
        }
        /**
         * Gets the upper limit.
         * @return The upper limit for this discount band
         */
        public int getUpperLimit(){
            return upperLimit;
        }
        /**
         * Gets the discount rate.
         * @return The discount rate for this discount band.
         */
        public double getDiscountRate(){
            return discountRate;
        }
    }
