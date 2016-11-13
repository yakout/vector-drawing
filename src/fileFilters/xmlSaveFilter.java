package fileFilters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * filter for xml files
 * @author said
 */
public class xmlSaveFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()){
            return false ;
        }
        String s = f.getName();
        return s.endsWith(".xml")||s.endsWith(".XML");
    }

    @Override
    public String getDescription() {
        return ".xml,.XML";
    }
}
