/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmroy.luxoft.service;

import dmroy.luxoft.been.Line;
import java.io.File;
import java.util.List;

/**
 *
 * @author dmitriyroy
 */
public interface FileParserInterface {
//    public File getFileToParse();
//    public File getFileForLog();
    public List<Line> parseFile();
}
