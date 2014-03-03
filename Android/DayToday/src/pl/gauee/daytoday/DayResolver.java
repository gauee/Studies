package pl.gauee.daytoday;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.daytoday.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DayResolver extends Activity {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("gauee","OnCreate");
		setContentView(R.layout.activity_day_resolver);
		SharedPreferences prefs = getSharedPreferences(LoginActivity.PREFERENCES_NAME,
		        MODE_PRIVATE);
		String currentUser = prefs.getString("current_user", "");
		if("".equals(currentUser)){
			Toast.makeText(this, "Somthing gone bad", Toast.LENGTH_LONG).show();
			return;
		}
		((TextView)findViewById(R.id.act2_userName)).setText("Użytkownik: " + currentUser);
		loadHistory(prefs, currentUser);
		SharedPreferences.Editor edit = prefs.edit();
		edit.putInt("lastAct", 2);
		edit.commit();
	}

	private void loadHistory(SharedPreferences prefs, String currentUser) {
		LinearLayout history = (LinearLayout)findViewById(R.id.resolver_historyList);
		Log.d("gauee","loading history");
		for(int i=0;i<5;++i){
			String date = prefs.getString(currentUser+"_date_"+i, "");
			if("".equals(date)){
				break;
			}
			TextView d = new TextView(this);
			d.setText(date);
			history.addView(d,i);
			Log.d("gauee","adding item numer" + i);
		}
		if(history.getChildCount()!=0){
			((TextView)history.getChildAt(0)).setTypeface(null,Typeface.BOLD);
		}
	}

	public void resolveDay(View v) {
		validateDayFormat();
//		if (validateDayFormat()) {
//			//resolveDay();
//		}
	}


	private boolean validateDayFormat() {
		// TODO Auto-generated method stub
		EditText day = (EditText) findViewById(R.id.resolver_day);
		String dateInput = day.getText().toString();
		try {
			Date dayDateToResolve = dateFormat.parse(dateInput);
			PolishWeekDay dayPL = new PolishWeekDay(dayDateToResolve);
			addToHistory(dateInput, dayPL);

		} catch (ParseException e) {
			day.setError("Niepoprawny format daty!");
		}

		return true;
	}

	private void addToHistory(String dateInput, PolishWeekDay dayPL) {
		LinearLayout history = (LinearLayout) findViewById(R.id.resolver_historyList);
		if(history.getChildCount() != 0){
			String prevDate = ((TextView)history.getChildAt(0)).getText().toString();
			if(prevDate.startsWith(dateInput)){
				return;
			}
		}
		TextView dayToHistory = new TextView(this);
		dayToHistory.setText(dateInput + ": " + dayPL.getWeekDay());
		history.addView(dayToHistory, 0);
		makeHistoryStyle();
		saveDateHistory();
//		String s = null;
//		s.substring(0);

	}

	private void saveDateHistory() {
		// TODO Auto-generated method stub
		SharedPreferences prefs = getSharedPreferences(LoginActivity.PREFERENCES_NAME,
		        MODE_PRIVATE);
		String currentUser = prefs.getString("current_user", "");
		
		SharedPreferences.Editor edit = prefs.edit();
		LinearLayout history = (LinearLayout)findViewById(R.id.resolver_historyList);
		
		for(int i=0;i<history.getChildCount();++i){
			edit.putString(currentUser+"_date_"+i, ((TextView)history.getChildAt(i)).getText().toString());
			Log.d("gauee",currentUser+"_date_"+i + " --- " + ((TextView)history.getChildAt(i)).getText().toString());
		}
		edit.commit();
	}

	private void makeHistoryStyle() {
		LinearLayout history = (LinearLayout) findViewById(R.id.resolver_historyList);
		((TextView) history.getChildAt(0)).setTypeface(null, Typeface.BOLD);
		if (history.getChildCount() > 1) {
			((TextView) history.getChildAt(1)).setTypeface(null,
					Typeface.NORMAL);
		}
		if (history.getChildCount() == 6) {
			history.removeViewAt(5);
		}
		
	}

	public void logOut(View v) {
		Intent it = new Intent(this, LoginActivity.class);
		it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(it);
	}
}
