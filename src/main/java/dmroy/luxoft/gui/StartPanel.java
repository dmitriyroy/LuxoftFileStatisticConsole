package dmroy.luxoft.gui;

import dmroy.luxoft.been.Line;
import dmroy.luxoft.dao.FileStatisticDao;
import dmroy.luxoft.service.FileParser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
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
                Object[] options = {"Да","Нет"};
                int n = JOptionPane.showOptionDialog(null,
                                                    "Соранять лог в файл?",
                                                    "Требуется Ваш выбор",
                                                    JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.QUESTION_MESSAGE,
                                                    null,     //do not use a custom Icon
                                                    options,  //the titles of buttons
                                                    options[0]); //default button title
                if(n == 0){
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
                Object[] options = {"Да","Нет"};
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
                        System.out.println("Выбрали ДА");
                        break;
                    default:
                        System.out.println("Выбрали НЕТ");
                }
            }
        });
        // КОНЕЦ - СОБЫТИЯ
    }

    public JPanel getPanel(){
        return mainPanel;
    }
}
