package dmroy.luxoft.gui;

import dmroy.luxoft.been.Line;
import dmroy.luxoft.dao.FileStatisticDao;
import dmroy.luxoft.service.FileParser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.*;

public class StartPanel extends JPanel{
    // панели
    private JPanel mainPanel = null;
    private JPanel mainCenterArea = null;
    private JPanel centerArea = null;
    private JPanel centerButtonArea = null;
    // кнопки
    private JButton addFile = null;
    private JButton addFolder = null;

    private final int COLOR_RED = 20;//230;//137;//145
    private final int COLOR_GREEN = 35;//235;//197;//177
    private final int COLOR_BLUE = 73;//249;//197;//202
    
    public StartPanel() throws BadLocationException, SQLException{
        
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(COLOR_RED,COLOR_GREEN,COLOR_BLUE));

        mainCenterArea = new JPanel(new GridBagLayout());
        centerArea = new JPanel(new BorderLayout());
        centerArea.setSize(300, 100);

        centerButtonArea = new JPanel(new GridLayout(2,1));
        addFile = new JButton("Обработать один файл");
        addFolder = new JButton("Обработать каталог");
        addFile.setFocusable(false);
        addFolder.setFocusable(false);
        addFile.setPreferredSize(new Dimension(300,50));
        centerButtonArea.add(addFile);
        centerButtonArea.add(addFolder);

        centerArea.add(centerButtonArea, BorderLayout.CENTER);

        mainCenterArea.add(centerArea);
        mainPanel.add(mainCenterArea,new GridBagConstraints(0, 0, 0, 0, 0, 0,
                                                            GridBagConstraints.CENTER,
                                                            GridBagConstraints.CENTER,
                                                            new Insets(0,0,0,0), 0, 0));

        // НАЧАЛО - СОБЫТИЯ
        addFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                FileParser fileParser = new FileParser();
                List<Line> lineList = fileParser.parseFile();

                FileStatisticDao statistic = new FileStatisticDao();
                Object[] options = {"Нет","Да"};
                int n = JOptionPane.showOptionDialog(null,
                                                    "Сохранять лог в файл?",
                                                    "Требуется Ваш выбор",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.QUESTION_MESSAGE,
                                                    null,     //do not use a custom Icon
                                                    options,  //the titles of buttons
                                                    options[0]); //default button title
                if(n == 1){
                    statistic.writeIntoFile(lineList);
                }

                statistic.writeIntoDB(lineList);

                // вывод статистики в консоль
//                Set<String> globalWordSet = fileParser.getGlobalWordSet();
//                int globalMinWordLength = fileParser.getGlobalMinWordLength();
//                int globalMaxWordLength = fileParser.getGlobalMaxWordLength();
//                String globalMinWord = fileParser.getGlobalMinWord();
//                String globalMaxWord = fileParser.getGlobalMaxWord();
//                int lineNumber = fileParser.getLineNumber();
//                System.out.println("rows = " + lineNumber
//                                + "; globalWordsCount = " + globalWordSet.size()
//                                + "; globalMinWord = " + globalMinWord
//                                + "; globalMinWordLength = " + globalMinWordLength
//                                + "; globalMaxWord = " + globalMaxWord
//                                + "; globalMaxWordLength = " + globalMaxWordLength);
            }
        });
        addFolder.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                FileParser fileParser = new FileParser();
                Object[] options = {"Нет","Да"};
                int n = JOptionPane.showOptionDialog(null,
                                                    "Обрабатывать вложенные каталоги?",
                                                    "Требуется Ваш выбор",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.QUESTION_MESSAGE,
                                                    null,     //do not use a custom Icon
                                                    options,  //the titles of buttons
                                                    options[0]); //default button title
                switch(n){
                    case 0:
                        System.out.println("Выбрали НЕТ");
                        JFileChooser folderChooserNo = new JFileChooser();   
                        //file.setCurrentDirectory(new File("."));  // установка директории старта по умолчанию
//                        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);                             
                        folderChooserNo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);                             
                        folderChooserNo.showOpenDialog(null);
                        File chooseCDirectoryNo = folderChooserNo.getSelectedFile();
                        File[] fileArr = chooseCDirectoryNo.listFiles();
                        for(File f:fileArr){
                            if(f.isFile()){
                                FileStatisticDao statistic = new FileStatisticDao();
                                statistic.writeIntoDB(fileParser.parseFile());
                            }
                        }
//                        File chooseCDirectory = folderChooser.getCurrentDirectory();
                        break;
                    default:
                        System.out.println("Выбрали ДА");
                        JFileChooser folderChooserYes = new JFileChooser();   
                        //file.setCurrentDirectory(new File("."));  // установка директории старта по умолчанию
//                        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);                             
                        folderChooserYes.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);                             
                        folderChooserYes.showOpenDialog(null);
                        File chooseCDirectoryYes = folderChooserYes.getSelectedFile();
                        String folderPath = chooseCDirectoryYes.getAbsolutePath();
//                        System.out.println("dirPath = " + folderPath);
                        try {
                            List<String> fileList = showFilesAndDirectoryes(chooseCDirectoryYes);
                            for(String file:fileList){
                                
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(StartPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            }
        });
        // КОНЕЦ - СОБЫТИЯ
    }

    public JPanel getPanel(){
        return mainPanel;
    }
    
    public static List<String> showFilesAndDirectoryes (File f) throws Exception  {
        List<String> outList = new ArrayList<>();
        if (!f.isDirectory ()) {
            outList.add(f.getCanonicalPath());
//            System.out.println (f.getCanonicalPath());
        }
        
        if (f.isDirectory ()) { 
            try {
                File[] child = f.listFiles();
                
                for (int i = 0; i < child.length; i++) {
//                    System.out.println(child[i].getParent());  
                    outList.addAll(showFilesAndDirectoryes (child[i]));     
                }
         
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        return outList;
    }
}
