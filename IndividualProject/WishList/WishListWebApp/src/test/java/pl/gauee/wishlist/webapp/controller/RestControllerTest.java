/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gauee.wishlist.webapp.controller;

import java.util.Collections;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pl.gauee.wishlist.utils.PageUtils;
import pl.gauee.wishlist.utils.persistance.rest.RestWishUser;
import pl.gauee.wishlist.utils.persistance.WishUser;

/**
 *
 * @author gauee
 */
public class RestControllerTest extends TestCase {

    public RestControllerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    private String baseUrl = "http://192.168.1.15:8080/wishListWebApp/";

    private <T extends Object> T getHttpGet(String url, Class<T> classType) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

        try {
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), classType);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            return null;
        } catch (ResourceAccessException e) {
            return null;
        }
    }

    public void testIsAuthenticated() {
        String url = baseUrl+ PageUtils.restUserAuth;
        boolean isAuth = getHttpGet(url, Boolean.class);
        assertEquals(true, isAuth);
        
    }

    public void testGetMineUser() {
        String url = baseUrl + PageUtils.restUserMe;
        RestWishUser user = getHttpGet(url, RestWishUser.class);
        System.out.println(user);
        assertEquals("gauee", user.getLogin());
    }
}
