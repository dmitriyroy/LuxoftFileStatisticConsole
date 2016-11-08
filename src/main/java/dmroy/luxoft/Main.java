package dmroy.luxoft;

import dmroy.luxoft.dao.FileStatisticDao;
import dmroy.luxoft.been.Line;
import dmroy.luxoft.service.FileParser;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author dmitriyroy
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String path = Main.class.getResource("../../beans.xml").toString().substring(6);
        new FileSystemXmlApplicationContext(path);
//        String fileToWork = "D:/!!!_tmp/Books/Кирпичи.txt";
//        String fileToWork = "D:/!!!_tmp/Books/eroshort.txt";        
//        String fileToWorkResult = "D:/!!!_tmp/Books/Кирпичи_result.txt";
        FileParser fileParser = null;
        if(args.length > 0){
            fileParser = new FileParser(new File(args[0]));
        }else{
            fileParser = new FileParser();
        }
        List<Line> lineList = fileParser.parseFile();
        Set<String> globalWordSet = fileParser.getGlobalWordSet();
        int globalMinWordLength = fileParser.getGlobalMinWordLength();
        int globalMaxWordLength = fileParser.getGlobalMaxWordLength();
        String globalMinWord = fileParser.getGlobalMinWord();
        String globalMaxWord = fileParser.getGlobalMaxWord();
        int lineNumber = fileParser.getLineNumber();

        FileStatisticDao statistic = new FileStatisticDao();
        if(args.length > 1){
            statistic.writeIntoFile(lineList, args[1]);
        }else{
            statistic.writeIntoFile(lineList);
        }
        statistic.writeIntoDB(lineList);
        
        System.out.println("rows = " + lineNumber 
                        + "; globalWordsCount = " + globalWordSet.size()
                        + "; globalMinWord = " + globalMinWord
                        + "; globalMinWordLength = " + globalMinWordLength
                        + "; globalMaxWord = " + globalMaxWord
                        + "; globalMaxWordLength = " + globalMaxWordLength);       
        
    }       
}
