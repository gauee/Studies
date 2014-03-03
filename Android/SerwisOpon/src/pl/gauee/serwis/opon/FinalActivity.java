package pl.gauee.serwis.opon;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinalActivity extends Activity {

	private String totalPrice;
	DecimalFormat priceFormatter = new DecimalFormat("##zł");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_final);
		SharedPreferences prefs = getSharedPreferences(
				MainActivity.PREFERENCES_NAME, MODE_PRIVATE);

		String firstOption = prefs.getString("act1_radioTxt", "---");
		String secondOption = prefs.getString("act2_radioTxt", "----");

		totalPrice = priceFormatter.format(countTotalPrice(prefs));

		List<String> params = new LinkedList<String>();
		params.add("Rodzaj usługi: " + firstOption);
		params.add("Rodzaj felg: " + secondOption);
		params.add("Dodatkowo:");
		checkComboBoxesThirdAct(prefs, params);
		StringBuilder sb = new StringBuilder();
		for (String p : params) {
			sb.append(p);
			sb.append("\n");
		}
		((TextView) findViewById(R.id.act4_txt)).setText(sb.toString());
		((TextView) findViewById(R.id.act4_totalPrice)).setText("Razem: "
				+ totalPrice);

	}

	private void checkComboBoxesThirdAct(SharedPreferences prefs,
			List<String> params) {
		if (prefs.getBoolean("act3_box1", false)) {
			params.add(prefs.getString("act3_txt1", "-----"));
		}

		if (prefs.getBoolean("act3_box2", false)) {
			params.add(prefs.getString("act3_txt2", "-----"));
		}

		if (prefs.getBoolean("act3_box3", false)) {
			params.add(prefs.getString("act3_txt3", "-----"));
		}

	}

	private float countTotalPrice(SharedPreferences prefs) {

		return roundNotToCoins((prefs.getInt("act1_price", 0)
				* prefs.getFloat("act2_price", 1)
				+ prefs.getInt("act3_price1", 0) + prefs.getInt("act3_price2",
				0)) * prefs.getFloat("act3_price3", 1));
	}

	private static int roundNotToCoins(float f) {

		int tmp = ((int) f / 10) * 10;
		if (f - tmp < 4.44) {
			return tmp;
		}

		return tmp + 10;
	}

	public void onClickReset(View v) {
		Intent it = new Intent(this, MainActivity.class);
		SharedPreferences prefs = getSharedPreferences(
				MainActivity.PREFERENCES_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();

		editor.putBoolean("propsExist", false);
		editor.commit();

		it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(it);
	}

	public void onClickSendSMS(View v) {
		SharedPreferences prefs = getSharedPreferences(
				MainActivity.PREFERENCES_NAME, MODE_PRIVATE);
		String phone = prefs.getString("phone", "508215398");
		String carId = prefs.getString("carId", "SG 87697");

		Intent iSms = new Intent(Intent.ACTION_VIEW);
		iSms.putExtra("address", phone);
		StringBuilder sb = new StringBuilder();
		sb.append("Usługa wulkanizacyjna dla samochodu: ");
		sb.append(carId);
		sb.append("\n");
		sb.append("Wynosi: ");
		sb.append(totalPrice);
		iSms.putExtra("sms_body", sb.toString());
		iSms.setType("vnd.android-dir/mms-sms");
		iSms.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(iSms);
	}
}
