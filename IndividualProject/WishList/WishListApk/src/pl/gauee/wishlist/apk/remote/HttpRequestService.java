package pl.gauee.wishlist.apk.remote;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import android.util.Log;


public class HttpRequestService {
	
	private static HttpRequestService service = new HttpRequestService();
	
	private HttpRequestService() {
		// TODO Auto-generated constructor stub
	}
	
	public static HttpRequestService getService(){
		return service;
	}
	
	public <T extends Object> T getHttpGet(String url, Class<T> classType) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

        try {
            ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), classType);
            return response.getBody();
        } catch (HttpClientErrorException e) {
        	Log.d("error",e.getMessage());
            return null;
        } catch (ResourceAccessException e) {
        	Log.d("error",e.getMessage());
            return null;
        }
    }
}
