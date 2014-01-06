package pl.gauee.wishlist.apk;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListDeleteActivity extends Activity {

	private final String titleName = "Wybierz listę do usunięcia:";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_preview);
		TextView title = (TextView) findViewById(R.id.listpreview_title);
		title.setText(titleName);
		
		String [] lists = new String[]{
				"Lista1",
				"Lista2",
				"Lista3",
				"Lista4",
				"Lista5",
				"Lista6",
				"Lista7"
		};
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1,
				lists);
		
		ListView listView = (ListView)findViewById(R.id.listpreview_lists);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_delete, menu);
		
		return true;
	}

}
