/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.uploads;

import java.io.File;
import static junit.framework.Assert.assertEquals;

/**
 *
 * @author gauee
 */
public class UploadFileSaverTest {

    public UploadFileSaverTest() {
    }

    public void setUp() {
    }

    public void testUploadFile() {
    }

    public void testGetFinalFilePath() {
    }

    public void testSetFinalFilePath() {
    }

    public void testGetFinalResourceFilePath() {
    }

    public void testSetFinalResourceFilePath() {
    }

    public void testUploadFileWihUniqueFilePath() {
    }

    public void testGetOutputDirectory() {
        File file = new File("/output/dir/is/just/there/file.txt");
        UploadFileSaver fileSaver = new UploadFileSaver();
        File outDir = fileSaver.getOutputDirectory(file);
        assertEquals("/output/dir/is/just/there", outDir.getAbsolutePath());
    }
}