package pl.gauee.serwis.opon;

import android.R.bool;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String PREFERENCES_NAME = "config";
	private static final int optionOnePrice = 40;
	private static final int optionTwoPrice = 22;
	
	private static boolean firstRunApp = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences prefs = getSharedPreferences(PREFERENCES_NAME,
		        MODE_PRIVATE);
		boolean propsExist = prefs.getBoolean("propsExist", false);
		SharedPreferences.Editor editor = prefs.edit();
		
		if(firstRunApp || !propsExist){
			Log.d("gauee","cleaning preferences");
			firstRunApp = false;
			editor.clear();
			editor.putBoolean("propsExist", true);
			editor.commit();
		}else{
			int radioId = prefs.getInt("act1_radioId", -1);
			if(radioId != -1){
				((RadioButton)findViewById(radioId)).setChecked(true);
			}
			((EditText)findViewById(R.id.act1_carId)).setText(prefs.getString("carId", ""));
			((EditText)findViewById(R.id.act1_tel)).setText(prefs.getString("phone", ""));
		}
		
	}
	
	
	public void onClickNext(View v) {
		SharedPreferences prefs = getSharedPreferences(PREFERENCES_NAME,
		        MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		
		
		int radioId = ((RadioGroup)findViewById(R.id.act1_rg)).getCheckedRadioButtonId();
		String carId = ((EditText)findViewById(R.id.act1_carId)).getText().toString();
		String phoneNum = ((EditText)findViewById(R.id.act1_tel)).getText().toString();

		if(invalidateInput(radioId,carId,phoneNum)){
			return;
		}
		
		Log.d("main", "act1_radioId: " + radioId);
		Log.d("main", "carId: " + carId);
		Log.d("main", "phone: " + phoneNum);
		
		editor.putInt("act1_radioId",radioId);
		editor.putString("act1_radioTxt",((RadioButton)findViewById(radioId)).getText().toString());
		editor.putString("carId", carId);
		editor.putString("phone", phoneNum);
		
		addPrice(editor,radioId);
		
		editor.commit();
		
		Intent it = new Intent("pl.gauee.intent.action.SecondAction");
        startActivity(it);
	}


	private boolean invalidateInput(int radioId, String carId, String phoneNum) {
		if(radioId == -1){
			Toast.makeText(this, "Musisz wybrać rodzaj usługi",Toast.LENGTH_SHORT).show();
			return true;
		}
		if("".equals(carId)){
			Toast.makeText(this, "Musisz wprowadzic nr rej samochodu",Toast.LENGTH_SHORT).show();
			return true;
		}
		if("".equals(phoneNum)){
			Toast.makeText(this,"Musisz podać numer tel",Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}


	private void addPrice(Editor editor, int radioId) {
		// TODO Auto-generated method stub
		if(radioId == R.id.act1_rb1){
			editor.putInt("act1_price", optionOnePrice);
		}else if(radioId == R.id.act1_rb2){
			editor.putInt("act1_price", optionTwoPrice);
		}
		
	}

}
