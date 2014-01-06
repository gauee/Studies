/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.controller;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author gauee
 */
public class UploadedFile {

    private MultipartFile file;

    public UploadedFile() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
