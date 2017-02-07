package fileFilters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * filter for json files
 * @author YS team
 */
public class JasonSaveFileFilter extends FileFilter {
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return false;
        }
        String s = f.getName();
        return s.endsWith(".json") || s.endsWith(".JSON");
    }

    public String getDescription() {
        return ".json,.JSON";
    }
}
