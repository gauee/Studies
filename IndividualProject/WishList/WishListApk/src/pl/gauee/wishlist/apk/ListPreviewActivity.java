package pl.gauee.wishlist.apk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pl.gauee.wishlist.apk.adapters.ListAdapter;
import pl.gauee.wishlist.apk.remote.RemoteAccess;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.persistance.WishUser;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListPreviewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_preview);

		List<WishList> lists = RemoteAccess.getInstance().geWishLists();
		

		ListAdapter adapter = new ListAdapter(this, R.layout.row_item_lists,
				lists);
		ListView listView = (ListView) findViewById(R.id.listpreview_lists);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_preview, menu);
		return true;
	}

}
