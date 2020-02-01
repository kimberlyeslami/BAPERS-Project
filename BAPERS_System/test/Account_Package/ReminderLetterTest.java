/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account_Package;

import System_Package.Reader;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A test class for testing methods belonging to the ReminderLetter class.
 * 
 * @author      Felice Gregorio
 * @version     (28/03/2018) version 1
 * @since       (28/03/2018) version 1
 */
public class ReminderLetterTest {
    
    public ReminderLetterTest() {
    }

    /**
     * Test of generateFirstLetter method, of class ReminderLetter.
     */
    @Test
    public void testGenerateFirstLetter() {
        System.out.println("generateFirstLetter");
        ReminderLetter letter = new ReminderLetter("ACC2525", 1);
        String expResult = "Jane Doe,\n"
                + "22 Lincln Road,\n"
                + "London,\n"
                + "United Kingdom,\n"
                + "N9 8DR\n\n"
                + "The Lab\n"
                + "Bloomsbury's Image Processing Laboratory\n"
                + "2, Wynytt Street, London, EC1V 7HU\n"
                + "Phone: 020 7235 7534\n\n"
                + "18 April 2018\n\n"
                + "REMINDER - Invoice No. 1\n"
                + "Job Code(s): ACN54, C108 Total Amount: 22.8\n\n"
                + "Dear Jane Doe,\n"
                + "According to our records, it appears that we have not yet received payment of the above invoice, "
                + "which was posted to you on 02 March 2018, for photographic work in out laboratory.\n"
                + "We would appreciate payment at your earliest convenience.\n"
                + "If you have already sent a payment to us recently, please accept our apologies.\n"
                + "Yours Sincerely,\n"
                + "G.Lancaster\n";
        
        letter.generateFirstLetter();
        
        Reader reader = new Reader("ReminderLetters/ACC2525_FirstReminder.txt");
        
        String result = reader.readLetter();
        
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of generateSecondLetter method, of class ReminderLetter.
     */
    @Test
    public void testGenerateSecondLetter() {
        System.out.println("generateSecondLetter");
        ReminderLetter letter = new ReminderLetter("ACC2525", 1);
        String expResult = "Jane Doe,\n"
                + "22 Lincln Road,\n"
                + "London,\n"
                + "United Kingdom,\n"
                + "N9 8DR\n\n"
                + "The Lab\n"
                + "Bloomsbury's Image Processing Laboratory\n"
                + "2, Wynytt Street, London, EC1V 7HU\n"
                + "Phone: 020 7235 7534\n\n"
                + "18 April 2018\n\n"
                + "REMINDER - Invoice No. 1\n"
                + "Job Code(s): ACN54, C108 Total Amount: 22.8\n\n"
                + "Dear Jane Doe,\n"
                + "It appears that we still have not received payment of the above invoice, "
                + "which was posted to you on 02 March 2018, for photographic work in out laboratory, "
                + "despite a reminder letter posted to you 1 month later.\n"
                + "Unless you pay the outstanding amount in full within SEVEN DAYS, or contact us with proposals for repayment, "
                + "we will have no option but to refer the matter to out solicitor.\n"
                + "Please send payment immediately to avoid further action.\n"
                + "Yours Sincerely,\n"
                + "G.Lancaster\n";
        
        letter.generateSecondLetter();
        
        Reader reader = new Reader("ReminderLetters/ACC2525_SecondReminder.txt");
        
        String result = reader.readLetter();
        
        System.out.println(result);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getBiplContact method, of class ReminderLetter.
     */
    @Test
    public void testGetBiplContact() {
        System.out.println("getBiplContact");
        ReminderLetter letter = new ReminderLetter("ACC2525", 1);
        String expResult = "The Lab\n"
                + "Bloomsbury's Image Processing Laboratory\n"
                + "2, Wynytt Street, London, EC1V 7HU\n"
                + "Phone: 020 7235 7534";
        String result = letter.getBiplContact();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBiplContact method, of class ReminderLetter.
     */
    @Test
    public void testSetBiplContact() {
        System.out.println("setBiplContact");
        String biplContact = "The Lab\n"
                + "Bloomsbury's Image Processing Laboratroy\n"
                + "18, Green Street, London, S12 9TQ\n"
                + "Phone: 020 8475 3648";
        ReminderLetter letter = new ReminderLetter("ACC2525", 1);
        letter.setBiplContact(biplContact);
        assertEquals(biplContact, letter.getBiplContact());
    }

    /**
     * Test of getMessage method, of class ReminderLetter.
     */
    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        ReminderLetter letter = new ReminderLetter("ACC2525", 1);
        letter.generateFirstLetter();
        String expResult = "Dear Jane Doe,\n"
                + "According to our records, it appears that we have not yet received payment of the above invoice, "
                + "which was posted to you on 02 March 2018, for photographic work in out laboratory.\n"
                + "We would appreciate payment at your earliest convenience.\n"
                + "If you have already sent a payment to us recently, please accept our apologies.\n"
                + "Yours Sincerely,\n"
                + "G.Lancaster";
        String result = letter.getMessage();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMessage method, of class ReminderLetter.
     */
    @Test
    public void testSetMessage() {
        System.out.println("setMessage");
        String message = "Dear Jane Doe,\n"
                + "It appears that we still have not received payment of the above invoice, "
                + "which was posted to you on 25 February 2018, for photographic work in out labratory, "
                + "despite a reminder letter posted to you 1 month later.\n"
                + "Unless you pay the outstanding amount in full within SEVEN DAYS, or contact us with proposals for repayment, "
                + "we will have no option but to refer the matter to out solicitor.\n"
                + "Please send payment immediatly to avoid further action.\n"
                + "Yours Sincerely,\n"
                + "G.Lancaster";
        ReminderLetter letter = new ReminderLetter("ACC2525", 1);
        letter.setMessage(message);
        assertEquals(message, letter.getMessage());
    }    
}
