package pl.gauee.wishlist.apk.remote;

import java.util.Collections;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

abstract class RemoteAsyncTaskPost<T> extends AsyncTask<String, Void, T> {

	private final String username;
	private final String password;
	protected static final String TAG = RemoteAsyncTaskPost.class.getSimpleName();
	private ProgressDialog progressDialog;
	//private boolean destroyed = false;
	private final Context context;
	private final String urlRequest;
	
	private Object[] objects;

	public RemoteAsyncTaskPost(String login, String pass, Context context,
			String urlRequest,
			Object ... objects) {
		// TODO Auto-generated constructor stub
		this.urlRequest = urlRequest;
		this.context = context;
		this.username = login;
		this.password = pass;
		this.objects = objects;
	}

	// ***************************************
	// Public methods
	// ***************************************
	public void showLoadingProgressDialog() {
		this.showProgressDialog("Loading. Please wait...");
	}

	public void showProgressDialog(CharSequence message) {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(context);
			progressDialog.setIndeterminate(true);
		}

		progressDialog.setMessage(message);
		progressDialog.show();
	}

	public void dismissProgressDialog() {
		if (progressDialog != null) {// && !destroyed) {
			progressDialog.dismiss();
		}
	}

	@Override
	protected void onPreExecute() {
		showLoadingProgressDialog();
	}

	@Override
	protected T doInBackground(String... params) {
		HttpAuthentication authHeader = new HttpBasicAuthentication(username,
				password);
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAuthorization(authHeader);
		requestHeaders.setAccept(Collections
				.singletonList(MediaType.APPLICATION_JSON));
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(
				new MappingJacksonHttpMessageConverter());

		
		
		try {
			// Make the network request
			Log.d("gauee","Authenticate with params:" + username + " " + password);
			Log.d("gauee", urlRequest);
			
			ResponseEntity<T> response = restTemplate.exchange(urlRequest,
					HttpMethod.POST,
					new HttpEntity<Object>(objects[0],requestHeaders),
					getClassType());
			
			return response.getBody();
		} catch (HttpClientErrorException e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			return null;
		} catch (ResourceAccessException e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			return null;
		}
	}

	@Override
	protected void onPostExecute(T result) {
		dismissProgressDialog();
		executeOnFinished(result);
	}

	public abstract Class<T> getClassType();

	public abstract void executeOnFinished(T result);
}
