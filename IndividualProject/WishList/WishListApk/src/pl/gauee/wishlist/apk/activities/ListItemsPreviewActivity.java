package pl.gauee.wishlist.apk.activities;

import pl.gauee.wishlist.apk.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.gauee.wishlist.apk.adapters.ItemListAdapter;
import pl.gauee.wishlist.apk.adapters.ListAdapter;
import pl.gauee.wishlist.apk.remote.RemoteAccess;
import pl.gauee.wishlist.utils.persistance.WishItem;
import pl.gauee.wishlist.utils.persistance.WishList;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class ListItemsPreviewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_preview);
		
		WishList list = RemoteAccess.getInstance().getWishList();
		
		TextView title = (TextView) findViewById(R.id.listpreview_title);
		title.setText("Lista " + list.getName());
		
		Log.d("gauee","Size of items in list: " + list.getListItems().size());
		
		List<WishItem> items = new ArrayList<WishItem>(list.getListItems());
		
		Log.d("gauee","Size of items: " + items.size());
		
		ItemListAdapter adapter = new ItemListAdapter(this,
				R.layout.row_item_items,
				items);
		
		Log.d("gauee", "size of adapter:"+adapter.getCount());
		
		ListView listView = (ListView) findViewById(R.id.listpreview_lists);
		listView.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_items_preview, menu);
		return true;
	}

}
