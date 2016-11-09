/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmroy.luxoft;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dmitriyroy
 */
public class MainTest {
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Main.
     */
    @org.junit.Test
    public void testMain() {
        System.out.println("main");
        String[] args = new String[2];
        String fileToWork = "D:/!!!_tmp/Books/test_words.txt";  
        String fileToWorkResult = "D:/!!!_tmp/Books/test_words_result.txt";
        args[0] = fileToWork;
        args[1] = fileToWorkResult;
        Main.main(args);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}