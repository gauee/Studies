package pl.gauee.wishlist.apk.activities;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.remote.RemoteAccess;
import pl.gauee.wishlist.utils.persistance.rest.RestWishUser;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserPreview extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_preview);
		
		getRestWishUserData();

		// setUser(user);
	}

	private void getRestWishUserData() {
		new RemoteAccess<RestWishUser> (this){
			
			@Override
			public void onReceivedResult(RestWishUser result) {
				setUser(result);
			}
			
		}.getMe();
		
	}

	private void setUser(RestWishUser user) {
		if (user == null) {
			Log.d("gauee", "user is null");
			return;
		}
		Log.d("gauee", user.toString());
		Log.d("gauee", user.getLogin());
		Log.d("gauee", user.getName());
		setUserLogin(user);
		setUserName(user);
		setUserSurname(user);
		setUserEmail(user);
		setUserMsisdn(user);
	}

	private void setUserSurname(RestWishUser user) {
		EditText surname = (EditText) findViewById(R.id.userpreview_surname);
		surname.setText(user.getSurname());
	}

	private void setUserEmail(RestWishUser user) {
		EditText email = (EditText) findViewById(R.id.userpreview_email);
		email.setText(user.getEmail());
	}

	private void setUserMsisdn(RestWishUser user) {
		EditText phone = (EditText) findViewById(R.id.userpreview_msisdn);
		phone.setText(user.getMsisdn());
	}

	private void setUserName(RestWishUser user) {
		EditText name = (EditText) findViewById(R.id.userpreview_name);
		name.setText(user.getName());
	}

	private void setUserLogin(RestWishUser user) {
		TextView login = (TextView) findViewById(R.id.userpreview_login);
		login.setText(user.getLogin());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_preview, menu);
		return true;
	}

	public void saveChangePersonalData(View v) {
		Toast.makeText(this, "Save changes", Toast.LENGTH_SHORT).show();
		finish();
	}

}
