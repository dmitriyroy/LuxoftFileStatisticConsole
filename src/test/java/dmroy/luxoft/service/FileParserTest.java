/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmroy.luxoft.service;

import dmroy.luxoft.been.Line;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author dmitriyroy
 */
public class FileParserTest {
    
    public FileParserTest() {
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
     * Test of parseFile method, of class FileParser.
     */
    @Test
    public void testParseFile() {
        System.out.println("parseFile");
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        List<Line> expResult = null;
        List<Line> result = instance.parseFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGlobalWordSet method, of class FileParser.
     */
    @Test
    public void testGetGlobalWordSet() {
        System.out.println("getGlobalWordSet");
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        Set<String> expResult = new HashSet<>();
        expResult.add("1");
        expResult.add("2");
        expResult.add("3");
        expResult.add("word1");
        expResult.add("word2");
        expResult.add("word3");
        expResult.add("word4");
        expResult.add("word5");
        expResult.add("longWord");
        Set<String> result = instance.getGlobalWordSet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGlobalWordSet method, of class FileParser.
     */
    @Test
    public void testSetGlobalWordSet() {
        System.out.println("setGlobalWordSet");
        Set<String> globalWordSet = null;
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        instance.setGlobalWordSet(globalWordSet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGlobalMinWordLength method, of class FileParser.
     */
    @Test
    public void testGetGlobalMinWordLength() {
        System.out.println("getGlobalMinWordLength");
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        int expResult = 1;
        int result = instance.getGlobalMinWordLength();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGlobalMinWordLength method, of class FileParser.
     */
    @Test
    @Ignore
    public void testSetGlobalMinWordLength() {
        System.out.println("setGlobalMinWordLength");
        int globalMinWordLength = 1;
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        instance.setGlobalMinWordLength(globalMinWordLength);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGlobalMaxWordLength method, of class FileParser.
     */
    @Test
    public void testGetGlobalMaxWordLength() {
        System.out.println("getGlobalMaxWordLength");
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        int expResult = 8;
        System.out.println("expResult = " + expResult);
        int result = instance.getGlobalMaxWordLength();
        System.out.println("result = " + result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGlobalMaxWordLength method, of class FileParser.
     */
    @Test
    @Ignore
    public void testSetGlobalMaxWordLength() {
        System.out.println("setGlobalMaxWordLength");
        int globalMaxWordLength = 8;
        System.out.println("globalMaxWordLength = " + globalMaxWordLength);
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        instance.setGlobalMaxWordLength(globalMaxWordLength);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGlobalMinWord method, of class FileParser.
     */
    @Test
    public void testGetGlobalMinWord() {
        System.out.println("getGlobalMinWord");
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        String expResult = "1";
        String result = instance.getGlobalMinWord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGlobalMinWord method, of class FileParser.
     */
    @Test
    @Ignore
    public void testSetGlobalMinWord() {
        System.out.println("setGlobalMinWord");
        String globalMinWord = "1";
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        instance.setGlobalMinWord(globalMinWord);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGlobalMaxWord method, of class FileParser.
     */
    @Test
    public void testGetGlobalMaxWord() {
        System.out.println("getGlobalMaxWord");
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        String expResult = "longWord";
        String result = instance.getGlobalMaxWord();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGlobalMaxWord method, of class FileParser.
     */
    @Test
    @Ignore
    public void testSetGlobalMaxWord() {
        System.out.println("setGlobalMaxWord");
        String globalMaxWord = "longWord";
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        instance.setGlobalMaxWord(globalMaxWord);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLineNumber method, of class FileParser.
     */
    @Test
    public void testGetLineNumber() {
        System.out.println("getLineNumber");
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        int expResult = 0;
        System.out.println("expResult = " + expResult);
        int result = instance.getLineNumber();
        System.out.println("result = " + result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(expResult == result);
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setLineNumber method, of class FileParser.
     */
    @Test
    @Ignore
    public void testSetLineNumber() {
        System.out.println("setLineNumber");
        int lineNumber = 3;
        FileParser instance = new FileParser(new File("D:/!!!_tmp/Books/test_words.txt"));
        instance.setLineNumber(lineNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
