package pl.gauee.app.viewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.gauee.app.viewer.comparator.AICLabelAsc;
import pl.gauee.app.viewer.comparator.AICLabelDesc;
import pl.gauee.app.viewer.comparator.AICRateBarAsc;
import pl.gauee.app.viewer.comparator.AICRateBarDesc;
import pl.gauee.app.viewer.comparator.ApplicationInfoComparator;
import pl.gauee.app.viewer.database.CustomApplicationInfo;
import pl.gauee.app.viewer.database.DatabaseAccess;
import pl.gauee.app.viewer.database.DatabaseOpenHelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListAppActivity extends Activity {

	private ApplicationAdapter adapter;
	private List<CustomApplicationInfo> list;
	private int selectedOption = R.id.sort_alf_asc;

	private DatabaseAccess dbAccess = null;
	private DatabaseOpenHelper dbHelper = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_app);
		initDb();

		PackageManager packageManager = getPackageManager();
		list = generateList(packageManager,
				packageManager
				.getInstalledApplications(PackageManager.GET_META_DATA));
		sortListView(new AICLabelAsc(packageManager));

		ListView listView = (ListView) findViewById(R.id.listView1);
		adapter = new ApplicationAdapter(this, R.layout.list_row, list) {
			
			@Override
			void saveInDb(CustomApplicationInfo cai) {
				// TODO Auto-generated method stub
				activitySaveInDb(cai);
				
			}
		};
		listView.setAdapter(adapter);
		getActionBar().setTitle("RankingAplikacji");
		getActionBar().setIcon(R.drawable.star);

	}

	protected void activitySaveInDb(CustomApplicationInfo cai) {
		// TODO Auto-generated method stub
		initDb();
		dbAccess.insert(cai);
	}

	private List<CustomApplicationInfo> generateList(
			PackageManager pm,
			List<ApplicationInfo> installedApplications) {
		// TODO Auto-generated method stub
		initDb();
		List<CustomApplicationInfo> list = new ArrayList<CustomApplicationInfo>();
		
		float rate =0;
		
		List<CustomApplicationInfo> listFromDb = dbAccess.listAll();
		
		for(ApplicationInfo appInfo : installedApplications){
			rate = 0;
			CustomApplicationInfo itemFromDb = getFromDbList(appInfo.loadLabel(pm).toString(),listFromDb);
			if(itemFromDb != null){
				rate = itemFromDb.getRate();
			}
			list.add(new CustomApplicationInfo(appInfo,appInfo.loadLabel(pm).toString(), rate));
		}
		
		return list;
	}

	/**
	 * @param appPackage
	 * @param formDb
	 * @return
	 */
	private CustomApplicationInfo getFromDbList(String appPackage, List<CustomApplicationInfo> formDb) {
		//Log.d("gauee","checking in list size: " + formDb.size());
		for(CustomApplicationInfo cai : formDb){
			Log.d("gauee","Entity "+appPackage +" in list from db: " + cai.toString());
			if(cai.getAppPackage().equals(appPackage)){
				return cai;
			}
		}
		return null;
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAccess.dispose();
    }
    
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.app_viewer_actionbar, menu);

		MenuItem item = menu.add(1, R.id.menu_sort, 20, "Sortowanie");
		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		item.setIcon(android.R.drawable.ic_menu_agenda);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.sort_alf_asc:
			printMessage("Sortowanie A-Z");
			if (selectNewState(item.getItemId())) {
				sortListView(new AICLabelAsc(this.getPackageManager()));
			}
			break;
		case R.id.sort_alf_desc:
			printMessage("Sortowanie Z-A");
			if (selectNewState(item.getItemId())) {
				sortListView(new AICLabelDesc(this.getPackageManager()));
			}
			break;
		case R.id.sort_rate_asc:
			printMessage("NajlepiejOceniane");
			//if (selectNewState(item.getItemId())) {
				sortListView(new AICRateBarDesc(this.getPackageManager()));
			//}
			break;
		case R.id.sort_rate_desc:
			printMessage("NajgorzejOceniane");
			//if (selectNewState(item.getItemId())) {
				sortListView(new AICRateBarAsc(this.getPackageManager()));
			//}
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private boolean selectNewState(int itemId) {
		if (selectedOption == itemId) {
			return false;
		}
		selectedOption = itemId;
		return true;
	}

	private void printMessage(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	private void sortListView(ApplicationInfoComparator comparator) {
		Collections.sort(list, comparator);
		adapter = new ApplicationAdapter(this, R.layout.list_row, list) {
			
			@Override
			void saveInDb(CustomApplicationInfo cai) {
				activitySaveInDb(cai);
			}
		};
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}
}
