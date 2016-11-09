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
                System.out.println("Кнопка ФАЙЛ");
                FileParser fileParser = new FileParser();
//                if(args.length > 0){
//                    fileParser = new FileParser(new File(args[0]));
//                }else{
//                    fileParser = new FileParser();
//                }
                List<Line> lineList = fileParser.parseFile();
                Set<String> globalWordSet = fileParser.getGlobalWordSet();
                int globalMinWordLength = fileParser.getGlobalMinWordLength();
                int globalMaxWordLength = fileParser.getGlobalMaxWordLength();
                String globalMinWord = fileParser.getGlobalMinWord();
                String globalMaxWord = fileParser.getGlobalMaxWord();
                int lineNumber = fileParser.getLineNumber();

                FileStatisticDao statistic = new FileStatisticDao();
                statistic.writeIntoFile(lineList);
//                if(args.length > 1){
//                    statistic.writeIntoFile(lineList, args[1]);
//                }else{
//                    statistic.writeIntoFile(lineList);
//                }
                statistic.writeIntoDB(lineList);

                System.out.println("rows = " + lineNumber
                                + "; globalWordsCount = " + globalWordSet.size()
                                + "; globalMinWord = " + globalMinWord
                                + "; globalMinWordLength = " + globalMinWordLength
                                + "; globalMaxWord = " + globalMaxWord
                                + "; globalMaxWordLength = " + globalMaxWordLength);
            }
        });
        addFolder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Кнопка КАТАЛОГ");

            }
        });
        // КОНЕЦ - СОБЫТИЯ
    }

    public JPanel getPanel(){
        return mainPanel;
    }
}
