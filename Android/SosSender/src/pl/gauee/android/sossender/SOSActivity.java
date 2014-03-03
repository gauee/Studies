package pl.gauee.android.sossender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class SOSActivity extends Activity implements LocationListener {

	private DateFormat formater = new SimpleDateFormat(
			"dd/MM/yyyy 'at' HH:mm:ss");
	private LocationManager locationManager;
	private String provider;
	private String curLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sos);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		provider = locationManager.getBestProvider(criteria, false);
		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			onLocationChanged(location);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.so, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent intent = new Intent(this, PreferenceView.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void doSos(View v) {
		String date = formater.format(new Date());
		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(this);
		String phoneNum = sp.getString("callListPref", "-1");
		boolean isGeoActive = sp.getBoolean("isGeoActive", false);
		String[] numbersSms = getSelectedMsisdnForSms(sp);

		sendSms(date, isGeoActive, numbersSms);
		makeCall(phoneNum);
	}

	private void sendSms(String date, boolean isGeoActive, String[] numbersSms) {
StringBuilder sb  = new StringBuilder();
		
		for (String msisdn : numbersSms) {
			sb.append(";")
			.append(msisdn);
		}
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Proszę o pomoc!\n")
		.append("Data: " + date);
		if(isGeoActive){
			messageBuilder.append("Moja lokalizja to ")
			.append(getCurrentLocation())
			.append("\n");
		}
		
		Uri sendSmsTo = Uri.parse("smsto:" + sb.substring(1));
		Intent sendSms = new Intent(Intent.ACTION_SENDTO,sendSmsTo);
		sendSms.putExtra("sms_body", messageBuilder.toString());
		
		startActivity(sendSms);
	}


	private void makeCall(String phoneNum) {
		String uri = "tel:" + phoneNum;
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse(uri));

		startActivity(callIntent);
	}

	private String getCurrentLocation() {
		return provider + ":" + curLocation;
	}

	private String[] getSelectedMsisdnForSms(SharedPreferences sp) {
		Set<String> numbersForSms = sp.getStringSet("smsListPref", null);
		String[] numbersSms;
		if (null != numbersForSms) {
			numbersSms = numbersForSms.toArray(new String[] {});
		} else {
			numbersSms = new String[] { "" };
		}
		return numbersSms;
	}

	@Override
	public void onLocationChanged(Location location) {
		double lat = location.getLatitude();
		double lng = location.getLongitude();

		curLocation = lat + "_" + lng;
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

}
