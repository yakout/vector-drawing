package fileFilters;


import java.io.File;
import javax.swing.filechooser.FileFilter;

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
