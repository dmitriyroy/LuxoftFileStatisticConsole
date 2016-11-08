package dmroy.luxoft.util;

import java.awt.FileDialog;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author dmitriyroy
 */
public class FileUtils {
    
    public static File getFile(String dialogName){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setEnabled(true);
        
        FileDialog dialog = new FileDialog(frame,dialogName);
//        dialog.setFocusable(true);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        String filePath = dialog.getDirectory() + dialog.getFile();
        frame.dispose();
//        System.out.println("filePath = " + filePath);
        return new File(filePath);
    }
}
