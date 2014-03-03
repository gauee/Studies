package pl.gauee.android.homecontroller;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		createMainMenu();
	}

	private void createMainMenu() {
		// TODO Auto-generated method stub

		ArrayAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);

		adapter.add("Ogrzewanie");
		adapter.add("Oświetlenie");
		adapter.add("Plany");
		adapter.add("Wykresy");

		ListView mainMenu = (ListView) findViewById(R.id.menuMain);

		mainMenu.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
