/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmroy.luxoft.dao;

import dmroy.luxoft.been.Line;
import dmroy.luxoft.util.FileUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;

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
    public void writeIntoFile(List<Line> lineList) {
        writeIntoFile(lineList, FileUtils.getFile("Введите название файл для вывода результата."));
    }

    @Override
    public void writeIntoFile(List<Line> lineList, String outFileName) {
        writeIntoFile(lineList, new File(outFileName));
    }

    @Override
    public void writeIntoFile(List<Line> lineList,File outFile) {
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
