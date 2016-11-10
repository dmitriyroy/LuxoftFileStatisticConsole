package dmroy.luxoft;

import dmroy.luxoft.gui.MainFrame;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import static org.springframework.context.i18n.LocaleContextHolder.setLocale;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author dmitriyroy
 */
public class Main {

//    public static FileStatisticDao fileStatisticDao;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String fileToWork = "D:/!!!_tmp/Books/Кирпичи.txt";
//        String fileToWork = "D:/!!!_tmp/Books/eroshort.txt";
//        String fileToWorkResult = "D:/!!!_tmp/Books/Кирпичи_result.txt";
//        String path = Main.class.getResource("../../beans.xml").toString().substring(6);
//        new FileSystemXmlApplicationContext(path);
        new ClassPathXmlApplicationContext("beans.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext(path);
//        BeanFactory factory = (BeanFactory) context;
//        fileStatisticDao = (FileStatisticDao) factory.getBean("fileStatisticDao");
/*
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
*/

        try {
            // устанавливаем русскую раскладку в приложении
            Locale loc = new Locale("ru","RU");
            setLocale(loc);

            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            //new InitOrderTable().run();
            //new InitCakeTable().run();
            new InitMainFrame().run();
            //new InitCustomersTable().run();

//            Thread thread1 = new Thread (new UpdateTables());
//            thread1.start();
        }
            catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        }

    }
    public static class InitMainFrame implements Runnable{

        public InitMainFrame(){
        }
            @Override
            public void run(){
            try {
                new MainFrame();
            } catch (BadLocationException | SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
