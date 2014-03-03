package pl.gauee.daytoday;

import com.example.daytoday.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	public static final String PREFERENCES_NAME = "app_history";
	public static boolean firstTimeRun = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		SharedPreferences prefs = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
		SharedPreferences.Editor edit = prefs.edit();
		if(firstTimeRun){
			firstTimeRun = false;
			if(2 == prefs.getInt("lastAct", -1)){
				((EditText)findViewById(R.id.login_username)).setText(prefs.getString("current_user", "UknownUser"));
				onUserValidate(null);
				return;
			}
		}
		edit.putInt("lastAct", 1);
		edit.commit();
	}
	
	public void onUserValidate(View v){
		if(userNameIsValid()){
			Intent it = new Intent(this,DayResolver.class);
			startActivity(it);
		}
		
	}

	private void saveCurrentUser(String userName) {
		SharedPreferences prefs = getSharedPreferences(PREFERENCES_NAME,
		        MODE_PRIVATE);
		SharedPreferences.Editor edit = prefs.edit();
		edit.putString("current_user", userName);
		edit.commit();
	}

	private boolean userNameIsValid() {
		// TODO Auto-generated method stub
		EditText user = (EditText)findViewById(R.id.login_username);
		String userName = user.getText().toString();
		
		if(!userName.matches("^[a-zA-Z0-9]+$")){
			user.setError("Nazwa musi składać się tylko z cyfr i liter");
			return false;
		}
		
		saveCurrentUser(userName);
		return true;
	}

}
