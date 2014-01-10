package pl.gauee.wishlist.apk.activities;

import pl.gauee.wishlist.apk.*;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ListItemsPreview extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_items_preview);
		
		TextView title = (TextView) findViewById(R.id.listitempreview_title);
		title.setText("Lista numer " + getIntent().getLongExtra("listId", -1));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_items_preview, menu);
		return true;
	}

}
