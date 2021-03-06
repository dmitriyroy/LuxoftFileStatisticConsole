package dmroy.luxoft.dao;

import dmroy.luxoft.been.Line;
import dmroy.luxoft.util.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Types;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author dmitriyroy
 */
@Repository("fileStatisticDao")
public class FileStatisticDao implements FileStatisticDaoInterface{

    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public FileStatisticDao() {
    }

    @Override
    public void writeIntoFile(List<Line> lineList, String inFile) {
        boolean goodFile = true;
        do{
            File file = FileUtils.getFile("Введите название файл для вывода результата.");
            if(!file.getAbsolutePath().equals(inFile)){
                writeIntoFile(lineList,inFile, file);
                goodFile = false;
            }else{
                JOptionPane.showMessageDialog(null,
                                            "Нельзя вывести лог в самого себя.",
                                            "Внимание!",
                                            JOptionPane.WARNING_MESSAGE);
            }
        }while(goodFile);
    }

    @Override
    public void writeIntoFile(List<Line> lineList, String inFile, String outFileName) {
        if(!outFileName.equals(inFile)){
            writeIntoFile(lineList, inFile, new File(outFileName));
        }
    }

    @Override
    public void writeIntoFile(List<Line> lineList,String inFile ,File outFile) {
        try {
            FileOutputStream os = new FileOutputStream(outFile);
            os.write("\n".getBytes());
            for(Line line:lineList){
                os.write(line.toString().getBytes());
                os.write("\n".getBytes());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileStatisticDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileStatisticDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void writeIntoDB(List<Line> lineList) {
        // нужно делать проверку на наличие данных по такому файлу в базе
        // пока сделано такой заглушкой
        // просто удаляем данные по этому файлу
        // прим.: можно было обеспечить транзакционность, и пр....
        String fileName = null;
        if(lineList.size() > 0){
            fileName = lineList.get(0).getFileName();
            jdbcTemplate.update("DELETE file_statistic FROM file_statistic WHERE FILE_NAME = ? ",
                                new Object[] { fileName},
                                new int[]{Types.VARCHAR});
        }
        String SQL_QUERY = "INSERT INTO file_statistic (               "
                                                   + "  FILE_ID,       "
                                                   + "  FILE_NAME,     "
                                                   + "  LINE_NUMBER,   "
                                                   + "  MIN_WORD,      "
                                                   + "  MAX_WORD,      "
                                                   + "  MIN_WORD_LEN,  "
                                                   + "  MAX_WORD_LEN,  "
                                                   + "  AVG_WORD_LEN,  "
                                                   + "  ALL_WORD_LEN,  "
                                                   + "  WORDS_CNT      "
                                                   + " )               "
                          + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        for(Line line:lineList){
            try{
                jdbcTemplate.update(SQL_QUERY,
                                   new Object[] {line.getFileId(), line.getFileName(), line.getLineNumber(), line.getMinWord(),
                                   line.getMaxWord(), line.getMinWordLength(), line.getMaxWordLength(),
                                   line.getAverageWordLength(), line.getAllWordsLength(), line.getWordsCount()},
                                   new int[]{Types.BIGINT, Types.VARCHAR, Types.INTEGER, Types.VARCHAR,
                                             Types.VARCHAR, Types.INTEGER, Types.INTEGER,
                                             Types.INTEGER, Types.BIGINT, Types.INTEGER});
//                Logger.getLogger(FileStatisticDao.class.getName()).log(Level.INFO, "Файл {0}, трока № {1} добавлена в базу данных.", new Object[]{line.getFileName(), line.getLineNumber()});
//                System.out.println("Файл " + line.getFileName() + ", строка № " + line.getLineNumber() + " добавлена в базу данных.");
            }catch(DataAccessException e){
                Logger.getLogger(FileStatisticDao.class.getName()).log(Level.WARNING, "Ошибка добавления строки № {0} файла {1}.", new Object[]{line.getLineNumber(), line.getFileName()} );
                System.out.println("Ошибка добавления строки № " + line.getLineNumber() + " файла " + line.getFileName() + " в базу данных.");
            }
        }
        Logger.getLogger(FileStatisticDao.class.getName()).log(Level.INFO, "В базу данных добавлена информация по файлу {0}, вставлено {1} строк.", new Object[]{fileName, lineList.size()});
    }



}
