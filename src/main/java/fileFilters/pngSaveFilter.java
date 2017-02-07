package fileFilters;


import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * filter for png files
 * @author said
 */
public class pngSaveFilter extends FileFilter {
	public boolean accept(File f) {
		if (f.isDirectory()) {
           return false;
        }
		String s = f.getName();
		return s.endsWith(".png")||s.endsWith(".PNG");
    }

    public String getDescription()
  {
      return ".png,.PNG";
  }
}
