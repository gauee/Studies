package pl.gauee.wishlist.apk.activities;

import pl.gauee.wishlist.apk.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PreviewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
		getActionBar().setTitle("WishList1.0 - wydarzenia");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case R.id.preview_listAdd:
			startNewActivity(ListAddActivity.class);
			break;
		case R.id.preview_listDelete:
			startNewActivity(ListDeleteActivity.class);
			break;
		case R.id.preview_listPreview:
			startNewActivity(ListPreviewActivity.class);
			break;
		case R.id.preview_UserAccount:
			startNewActivity(UserPreview.class);
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	private <T extends Activity> void startNewActivity(Class<T> class1) {
		Intent intent = new Intent(this, class1);
		startActivity(intent);
	}

}
