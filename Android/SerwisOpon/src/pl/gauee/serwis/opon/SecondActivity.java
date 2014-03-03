package pl.gauee.serwis.opon;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SecondActivity extends Activity {

	private static final float optionOnePrice = 1.0f;
	private static final float optionTwoPrice = 1.2f;
	private static final float optionThreePrice = 1.8f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		SharedPreferences prefs = getSharedPreferences(
				MainActivity.PREFERENCES_NAME, MODE_PRIVATE);
		int radioId = prefs.getInt("act2_radioId", -1);
		if (radioId != -1) {
			((RadioButton) findViewById(radioId)).setChecked(true);
		}
	}

	public void onClickNext(View v) {
		SharedPreferences prefs = getSharedPreferences(
				MainActivity.PREFERENCES_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		int radioId = ((RadioGroup) findViewById(R.id.act2_rg))
				.getCheckedRadioButtonId();

		if(invalidateInput(radioId)){
			return;
		}
		editor.putInt("act2_radioId", radioId);
		editor.putString("act2_radioTxt", ((RadioButton)findViewById(radioId)).getText().toString());

		addPrice(editor, radioId);

		editor.commit();

		Intent it = new Intent("pl.gauee.intent.action.ThirdAction");
		startActivity(it);
	}

	private void addPrice(Editor editor, int radioId) {
		// TODO Auto-generated method stub
		if(radioId == R.id.act2_rb1){
			editor.putFloat("act2_price", optionOnePrice);
		}else if(radioId == R.id.act2_rb2){
			editor.putFloat("act2_price", optionTwoPrice);
		}else if(radioId == R.id.act2_rb3){
			editor.putFloat("act2_price", optionThreePrice);
		}
		
	}
	
	private boolean invalidateInput(int radioId) {
		if(radioId == -1){
			Toast.makeText(this, "Musisz wybrać rodzaj felg",Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

}
