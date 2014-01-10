package pl.gauee.wishlist.apk.activities;

import java.util.List;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.adapters.ListAdapter;
import pl.gauee.wishlist.apk.remote.RemoteAccess;
import pl.gauee.wishlist.utils.persistance.rest.RestUserLists;
import pl.gauee.wishlist.utils.persistance.rest.RestWishList;
import android.app.Activity;
import android.os.Bundle;
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
		
		new RemoteAccess<RestUserLists>(this) {
			@Override
			public void onReceivedResult(RestUserLists result) {
				// TODO Auto-generated method stub
				setListAdapter(result);
			}
			
			
		}.getUserLists();
	}
	
	private void setListAdapter(RestUserLists result) {
		List<RestWishList> lists = result.getLists();
		

		ListAdapter adapter = new ListAdapter(this, R.layout.row_item_lists,
				lists,
				true);
		ListView listView = (ListView) findViewById(R.id.listpreview_lists);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_delete, menu);
		
		return true;
	}

}
