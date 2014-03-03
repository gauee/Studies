package com.example.remotecontroller;

import java.net.InetSocketAddress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends Activity {

	private EditText hostIn;
	private EditText loginIn;
	private EditText passIn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		
		hostIn = (EditText)findViewById(R.id.login_host_insert);
		Intent in = getIntent();
		String hostTmp = null;
		hostTmp = in.getStringExtra("host");
		if(null == hostTmp){
			hostTmp = "unknown";
		}
		hostIn.setText(hostTmp);
		
		loginIn = (EditText)findViewById(R.id.login_login_insert);
		passIn = (EditText)findViewById(R.id.login_pass_insert);
		
		Button connect = (Button)findViewById(R.id.login_connect);
		
		connect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(LogInActivity.class.getSimpleName(), "Data: Host=" + hostIn.getText().toString() + " loginIn="+loginIn.getText().toString() + " passIn="+passIn.getText().toString());
				Intent intent = new Intent(getApplicationContext(),MainActionActivity.class);
				Bundle b = new Bundle();
				b.putString("host", hostIn.getText().toString());
				intent.putExtra("host",hostIn.getText().toString());
				startActivity(intent);
				finish();
			}
		});
	}
}
