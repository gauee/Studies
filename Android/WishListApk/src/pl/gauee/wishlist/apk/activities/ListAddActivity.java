package pl.gauee.wishlist.apk.activities;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.remote.RemoteAccess;
import pl.gauee.wishlist.utils.persistance.rest.RestWishList;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

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
		String listName = ((EditText)findViewById(R.id.addlist_listname)).getText().toString();
		EditText myEditText = (EditText) findViewById(R.id.addlist_listname);
		InputMethodManager imm = (InputMethodManager)getSystemService(
			      Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
		RestWishList newList = new RestWishList();
		newList.setName(listName);
		new RemoteAccess<Boolean>(this) {
			@Override
			public void onReceivedResult(Boolean result) {
				Log.d("gauee", "received result from post" + result);
				closeActivity(result);
			}

		}.createNewList(newList);
		
	}
	
	private void closeActivity(boolean result){
		if(result){
			Toast.makeText(this, "Lista została zapisana", Toast.LENGTH_SHORT).show();
		}else{
			((EditText)findViewById(R.id.addlist_listname)).setError("Nie udało się zapisać listy");
		}
	}
}
