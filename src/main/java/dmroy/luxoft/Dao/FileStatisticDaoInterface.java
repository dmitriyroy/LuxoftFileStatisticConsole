package dmroy.luxoft.dao;

import dmroy.luxoft.been.Line;
import java.io.File;
import java.util.List;

/**
 *
 * @author dmitriyroy
 */
public interface FileStatisticDaoInterface {
    public void writeIntoFile(List<Line> lineList);
    public void writeIntoFile(List<Line> lineList,File outFile);
    public void writeIntoFile(List<Line> lineList,String outFileName);
    public void writeIntoDB(List<Line> lineList);
}
