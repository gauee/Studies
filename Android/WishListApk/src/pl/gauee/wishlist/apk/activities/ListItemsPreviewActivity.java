package pl.gauee.wishlist.apk.activities;

import java.util.List;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.adapters.ItemListAdapter;
import pl.gauee.wishlist.apk.remote.RemoteAccess;
import pl.gauee.wishlist.utils.persistance.rest.RestWishItem;
import pl.gauee.wishlist.utils.persistance.rest.RestWishList;
import pl.gauee.wishlist.utils.persistance.rest.RestWishListWithItems;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class ListItemsPreviewActivity extends Activity {

	private long listId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_preview);

		listId = getIntent().getLongExtra("listId", 0);

		downloadData(listId);

	}

	private void downloadData(long listId) {
		new RemoteAccess<RestWishListWithItems>(this) {

			@Override
			public void onReceivedResult(RestWishListWithItems result) {
				setItemAdapter(result);
			}

		}.getMyList(listId);
	}

	protected void setItemAdapter(RestWishListWithItems result) {
		List<RestWishItem> items = result.getItems();
		RestWishList list = result.getList();

		TextView title = (TextView) findViewById(R.id.listpreview_title);
		title.setText("Lista " + list.getName());

		ItemListAdapter adapter = new ItemListAdapter(this,
				R.layout.row_item_items, items);

		ListView listView = (ListView) findViewById(R.id.listpreview_lists);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_items_preview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.listItems_sendSms:
			startSmsActivity();
			break;
		case R.id.listItems_refresh:
			reloadListView();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private void reloadListView() {
		// TODO Auto-generated method stub
		downloadData(listId);
	}

	private void startSmsActivity() {
		// TODO Auto-generated method stub
		ListView listView = (ListView) findViewById(R.id.listpreview_lists);
		ItemListAdapter adapter = (ItemListAdapter) listView.getAdapter();
		StringBuilder sb = new StringBuilder();
		for (RestWishItem item : adapter.getItems()) {
			if (!item.isBought()) {
				sb.append(item.getName()).append("-")
						.append(item.getDescription()).append("-")
						.append(item.getPrice()).append("zł,\n");
			}
		}

		Intent iSms = new Intent(Intent.ACTION_VIEW);
		iSms.putExtra("sms_body", sb.toString());
		iSms.setType("vnd.android-dir/mms-sms");
		// iSms.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(iSms);
	}

}
