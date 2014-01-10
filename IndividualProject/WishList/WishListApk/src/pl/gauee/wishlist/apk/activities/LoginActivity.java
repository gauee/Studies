package pl.gauee.wishlist.apk.activities;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.remote.RemoteAccess;
import pl.gauee.wishlist.apk.store.SharePrefManagement;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		((EditText)findViewById(R.id.login_login)).setText("gauee");
		((EditText)findViewById(R.id.login_pass)).setText("gauee");
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void submitLoginAndPass(View v){
		String login = ((EditText)findViewById(R.id.login_login)).getText().toString();
		String pass = ((EditText)findViewById(R.id.login_pass)).getText().toString();
		
		new RemoteAccess<Boolean>(this) {
			@Override
			public void onReceivedResult(Boolean result) {
				validatedUser(result);
			}

		}.getUserAuthorized(login,pass);

	}
	

	private void validatedUser(Boolean result) {
		if(result){
			String login = ((EditText)findViewById(R.id.login_login)).getText().toString();
			String pass = ((EditText)findViewById(R.id.login_pass)).getText().toString();
			
			new SharePrefManagement(this).saveUserInPref(login, pass);
			
			Intent intent = new Intent(this,PreviewActivity.class);
			startActivity(intent);
		}else{
			EditText loginText = ((EditText)findViewById(R.id.login_login));
			loginText.setError("Login lub hasło nie poprawne");
		}
	}

}
