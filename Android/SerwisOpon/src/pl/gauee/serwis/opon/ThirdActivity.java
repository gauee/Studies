package pl.gauee.serwis.opon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class ThirdActivity extends Activity {

	private static final int optionOnePrice = 14;
	private static final int optionTwoPrice = 12;
	private static final float optionThreePrice = 1.8f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		SharedPreferences prefs = getSharedPreferences(
				MainActivity.PREFERENCES_NAME, MODE_PRIVATE);
		boolean box1Checked = prefs.getBoolean("act3_box1", false);
		boolean box2Checked = prefs.getBoolean("act3_box2", false);
		boolean box3Checked = prefs.getBoolean("act3_box3", false);
		((CheckBox) findViewById(R.id.act3_cb1)).setChecked(box1Checked);
		((CheckBox) findViewById(R.id.act3_cb2)).setChecked(box2Checked);
		((CheckBox) findViewById(R.id.act3_cb3)).setChecked(box3Checked);

	}

	public void onClickNext(View v) {
		SharedPreferences prefs = getSharedPreferences(
				MainActivity.PREFERENCES_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();

		boolean box1Checked = ((CheckBox) findViewById(R.id.act3_cb1))
				.isChecked();
		boolean box2Checked = ((CheckBox) findViewById(R.id.act3_cb2))
				.isChecked();
		boolean box3Checked = ((CheckBox) findViewById(R.id.act3_cb3))
				.isChecked();

		editor.putBoolean("act3_box1", box1Checked);
		editor.putBoolean("act3_box2", box2Checked);
		editor.putBoolean("act3_box3", box3Checked);

		addText(editor);
		
		addPrice(editor,box1Checked,box2Checked,box3Checked);
		
		editor.commit();

		Intent it = new Intent("pl.gauee.intent.action.FinalAction");
		startActivity(it);
	}

	private void addText(Editor editor) {
		// TODO Auto-generated method stub

		editor.putString("act3_txt1",((CheckBox)findViewById(R.id.act3_cb1)).getText().toString());
		editor.putString("act3_txt2",((CheckBox)findViewById(R.id.act3_cb2)).getText().toString());
		editor.putString("act3_txt3",((CheckBox)findViewById(R.id.act3_cb3)).getText().toString());
		
	}

	private void addPrice(Editor editor, boolean box1Checked,
			boolean box2Checked, boolean box3Checked) {
		// TODO Auto-generated method stub
		editor.putInt("act3_price1", box1Checked?optionOnePrice:0);
		editor.putInt("act3_price2", box2Checked?optionTwoPrice:0);
		editor.putFloat("act3_price3", box3Checked?optionThreePrice:1);
	}
}
