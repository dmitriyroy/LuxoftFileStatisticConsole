package dmroy.luxoft.dao;

import dmroy.luxoft.been.Line;
import java.io.File;
import java.util.List;

/**
 *
 * @author dmitriyroy
 */
public interface FileStatisticDaoInterface {
    public void writeIntoFile(List<Line> lineList,String inFile);
    public void writeIntoFile(List<Line> lineList,String inFile,File outFile);
    public void writeIntoFile(List<Line> lineList,String inFile,String outFileName);
    public void writeIntoDB(List<Line> lineList);
}
