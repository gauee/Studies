package pl.gauee.wishlist.apk.activities;

import pl.gauee.wishlist.apk.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ListAddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_add);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_add, menu);
		return true;
	}

	
	public void createNewList(View v){
		this.finish();
	}
}
