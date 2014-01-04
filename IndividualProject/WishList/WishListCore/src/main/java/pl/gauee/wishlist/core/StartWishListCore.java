package pl.gauee.wishlist.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class StartWishListCore {

    public static void main(String[] args) {
        String file = "./src/main/resources/rmi-context.xml";
        ApplicationContext context = new FileSystemXmlApplicationContext(file);
        System.out.println("waiting for requests...");
    }
}
