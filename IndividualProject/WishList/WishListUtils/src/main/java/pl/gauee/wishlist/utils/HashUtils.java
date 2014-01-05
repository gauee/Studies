/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

/**
 *
 * @author gauee
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class HashUtils {

    private static HashUtils instance = new HashUtils();
    private MessageDigest mdSHA256;
    private Logger logger = Logger.getLogger(HashUtils.class);

    private HashUtils() {
        try {
            this.mdSHA256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            logger.error(ex);
            this.mdSHA256 = null;
        }
    }

    public static HashUtils getInstance() {
        return instance;
    }

    public String hashSHA256(String stringToHash) {
        if (mdSHA256 == null) {
            return "";
        }
        if (stringToHash == null) {
            stringToHash = "";
        }
        StringBuilder sb = new StringBuilder();
        mdSHA256.update(stringToHash.getBytes());

        byte[] digest = mdSHA256.digest();

        for (int i = 0; i < digest.length; ++i) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
