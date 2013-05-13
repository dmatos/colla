/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer.view;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class JarFileChooserFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = CustomFileChooser.getExtension(f);
        if (extension != null) {
            if (extension.equals(CustomFileChooser.jar)){
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Jar files (*.jar)";
    }

}
