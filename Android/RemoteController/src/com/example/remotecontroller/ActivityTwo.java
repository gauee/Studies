package com.example.remotecontroller;

import java.util.LinkedList;
import java.util.List;

import com.example.remotecontroller.services.HostSearcher;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class ActivityTwo extends ListActivity {

	private ArrayAdapter<String> adapter;
	private List<String> val;
	private Intent searcherHosts;
	private String currentSelectedHost;

	private Handler handler = new Handler() {
		public void handleMessage(Message message) {
			Object path = message.obj;
			addNewHost(path.toString());
			Log.d("handleMsg", path.toString());

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_layout);
		val = new LinkedList<String>();
		adapter = new ArrayAdapter<String>(this,
				R.layout.own_check_text_view, val);
		setListAdapter(adapter);

	}

	private void addNewHost(String host) {
		// TODO Auto-generated method stub
		val.add(host);
		adapter.notifyDataSetChanged();
	}

	public void onClick(View view) {
		Log.d("debug", "onClick execute");
		if(searcherHosts != null){
			stopService(searcherHosts);
		}
		searcherHosts = new Intent(this, HostSearcher.class);
		Messenger messenger = new Messenger(handler);
		searcherHosts.putExtra("MESSENGER", messenger);
		//Clearing
		val.clear();
		currentSelectedHost = "";
		startService(searcherHosts);
	}
	
	public void onConnect(View view){
		Log.d("debug","onConnect execute");
		stopService(searcherHosts);
		Intent Login = new Intent(getApplicationContext(),LogInActivity.class);
		Login.putExtra("host", currentSelectedHost);
		startActivity(Login);
		finish();
	}
	
	public void onConnectApp(View view) {
		Intent intent = new Intent(getApplicationContext(),MainAppActionActivity.class);
//		String tmp = ((EditText)findViewById(R.id.editText1)).getText().toString();
//		if(!"".equals(tmp)){
//			currentSelectedHost = tmp;
//		}
//		Log.d("debug",currentSelectedHost);
//		
		intent.putExtra("host", currentSelectedHost);
		startActivity(intent);
		finish();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		currentSelectedHost = (String) this.getListAdapter().getItem(position);
		Log.d("debug","TO jes host: =" + currentSelectedHost);
		
	}
}
