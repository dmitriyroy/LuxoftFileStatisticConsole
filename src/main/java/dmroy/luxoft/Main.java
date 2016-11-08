package dmroy.luxoft;

import dmroy.luxoft.dao.FileStatisticDao;
import dmroy.luxoft.been.Line;
import dmroy.luxoft.service.FileParser;
import java.io.File;
import java.util.List;
import java.util.Set;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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
