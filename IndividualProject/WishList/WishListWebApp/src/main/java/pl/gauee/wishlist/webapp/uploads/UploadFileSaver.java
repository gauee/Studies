/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.uploads;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import pl.gauee.wishlist.utils.persistance.WishItem;

/**
 *
 * @author gauee
 */
public class UploadFileSaver {

    private static final String outputFileDir = "/home/gauee/Pulpit/tmp/uploadedFiles/";
    private static final String outputResourceFileDir = "uploadedFiles/";
    public static final String imageExtension = ".jpg";
    private final CommonsMultipartFile fileToUpload;
    private String finalFilePath;
    private String finalResourceFilePath;
    private final Logger logger = Logger.getLogger(UploadFileSaver.class);

    protected UploadFileSaver() {
        fileToUpload = null;
    }

    public UploadFileSaver(CommonsMultipartFile fileToUpload) {
        this.fileToUpload = fileToUpload;
        generateFinalOutputFile(fileToUpload.getOriginalFilename());
    }

    public void uploadFile() {
        if (fileToUpload == null) {
            setFinalFilePath("");
            logger.warn("FileToUpload was null");
            return;
        }
        try {
            InputStream inStream = fileToUpload.getInputStream();

            logger.info("FileDest: " + getFinalFilePath());

            File outFile = new File(getFinalFilePath());
            File outFileDir = getOutputDirectory(outFile);
            if (!outFileDir.exists()) {
                outFileDir.mkdirs();
            }
            if (outFile.exists()) {
                outFile.renameTo(new File(outFile.getAbsolutePath() + ".old"));
            } else {
                outFile.createNewFile();
            }
            OutputStream outStrem = new FileOutputStream(outFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inStream.read(bytes)) != -1) {
                outStrem.write(bytes, 0, read);
            }
        } catch (IOException ex) {
            logger.error(ex);
        }

    }

    public String getFinalFilePath() {
        return finalFilePath;
    }

    public void setFinalFilePath(String finalFilePath) {
        this.finalFilePath = finalFilePath;
    }

    private void generateFinalOutputFile(String fileName) {
        setFinalFilePath(outputFileDir + fileName);
        setFinalResourceFilePath(outputResourceFileDir + fileName);
    }

    public String getFinalResourceFilePath() {
        return finalResourceFilePath;
    }

    public void setFinalResourceFilePath(String finalResourceFilePath) {
        this.finalResourceFilePath = finalResourceFilePath;
    }

    public void uploadFileWihUniqueFilePath(String loginCurrentLoggedUser, long listId, WishItem tmpItem) {
        generateFinalOutputFile(new StringBuilder()
                .append(loginCurrentLoggedUser)
                .append(File.separator)
                .append(listId)
                .append(File.separator)
                .append(tmpItem.getName())
                .append("_")
                .append(System.currentTimeMillis() % 100)
                .append(imageExtension)
                .toString());
        uploadFile();
        tmpItem.setPhotoUrl(getFinalResourceFilePath());
    }

    protected File getOutputDirectory(File outFilePath) {
        return new File(outFilePath.getAbsolutePath().substring(0, outFilePath.getAbsolutePath().lastIndexOf(File.separator)));
    }
}
