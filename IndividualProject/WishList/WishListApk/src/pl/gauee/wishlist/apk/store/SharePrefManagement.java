package pl.gauee.wishlist.apk.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePrefManagement {


	private SharedPreferences preferences;
	private String sharedName = "MySettings";
	
	private String userKey = "userKey";
	private String passKey = "passKey";
	
	private final Context context;
	private final int mode;
	
	public SharePrefManagement(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.mode = context.MODE_PRIVATE;
	}
	
	public void saveUserInPref(String user,String pass){
		preferences = context.getSharedPreferences(sharedName, mode);
		Editor edit = preferences.edit();
		edit.putString(userKey, user);
		edit.putString(passKey, pass);
		edit.commit();
	}
	

	public String getUserName(){
		return context.getSharedPreferences(sharedName, mode).getString(userKey, "");
	}
	
	public String getUserPass(){
		return context.getSharedPreferences(sharedName, mode).getString(passKey, "");
	}
	
}
