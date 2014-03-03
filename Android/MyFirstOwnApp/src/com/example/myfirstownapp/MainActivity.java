package com.example.myfirstownapp;

import remote.controller.server.messages.SwitchWindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText edT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edT = (EditText)findViewById(R.id.login_login);
		edT.setText(R.string.test_one);
		Button submit = (Button)findViewById(R.id.searchHost);
		new SwitchWindow();
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				edT.setText("Dziala całkiem dobrze!");
				
			}
		});
		
		Button goto2Activity = (Button)findViewById(R.id.main_unlock_screen);
		goto2Activity.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(), ActivityTwo.class));
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
