package pl.gauee.android.acm;

import java.util.List;

import pl.gauee.android.acm.db.CustomChargingInfo;
import pl.gauee.android.acm.db.DatabaseAccess;
import pl.gauee.android.acm.db.DatabaseOpenHelper;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends Activity {

	private static final String LABEL_LAST_DAY = "LastDayChargingTime: ";
	private static final String LABEL_AVR_DAILY = "AvrDailyChargingTime: ";
	private DatabaseAccess dbAccess = null;
	private DatabaseOpenHelper dbHelper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initDb();
		reloadData();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_refresh:
			refreshListView();
			break;
		case R.id.action_delete_all:
			deleteAllRows();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void deleteAllRows() {
		dbAccess.deleteAll();
		refreshListView();
	}

	public void refreshListView() {
		reloadData();
	}

	private void reloadData() {
		List<CustomChargingInfo> list = dbAccess.listAll();

		ListView listView = (ListView) findViewById(R.id.main_list_charging);
		AcAdapter adapter = new AcAdapter(this, R.layout.cci_row, list);
		listView.setAdapter(adapter);

		TextView avrTime = (TextView) findViewById(R.id.main_average_charging_time);
		avrTime.setText(LABEL_AVR_DAILY+adapter.getAverageDaily());
		
		TextView lastDay = (TextView) findViewById(R.id.main_last_day_charging_time);
		lastDay.setText(LABEL_LAST_DAY+adapter.getLastDayInfo());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		dbAccess.dispose();
		AbstractAcReceiver.cleanAll(this);
		// this.deleteDatabase("ac_manager.db");
	}

	private void initDb() {
		// TODO Auto-generated method stub
		if (dbHelper == null) {
			dbHelper = new DatabaseOpenHelper(this);
		}
		if (dbAccess == null) {
			dbAccess = new DatabaseAccess(dbHelper.getWritableDatabase());
		}
	}

}
