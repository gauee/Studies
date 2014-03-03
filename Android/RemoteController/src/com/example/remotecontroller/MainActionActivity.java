package com.example.remotecontroller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.remotecontroller.services.SshConnection;

public class MainActionActivity extends Activity {
	private String host;
	private String login;
	private String pass;

	private Intent sshConnection;
	private Messenger mService = null;
	private boolean mBound;
	
	private ServiceConnection mConnection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.d("debug","disconnected");
			mService = null;
			mBound = false;
		}
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.d("debug","connected");
			mService = new Messenger(service);
			mBound = true;
			
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_action_activity);
		Intent intent = getIntent();
		host = intent.getStringExtra("host");
		login = intent.getStringExtra("login");
		pass = intent.getStringExtra("pass");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();		
		sshConnection = new Intent(getApplicationContext(),SshConnection.class);
		sshConnection.putExtra("host",host);
		sshConnection.putExtra("login", login);
		sshConnection.putExtra("pass", pass);
		bindService(sshConnection, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(mBound){
			unbindService(mConnection);
			mBound = false;
		}
	}
	
	public void sendMsg(int msg){
		if(!mBound){
			Log.d("debug","un bind!!!");
			return;
		}
	
		Message message =  Message.obtain(null,msg,0,0);
		try {
			mService.send(message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

	
	public void onLockScreen(View view){
		Log.d("debug","onLockScreen");
		sendMsg(SshConnection.EXEC_LOCK_SCREEN);
	}
	
	public void onUnlockScreen(View view){
		Log.d("debug","onUnlockScreen");
		sendMsg(SshConnection.EXEC_UNLOCK_SCREEN);
	}
	
	public void onMute(View view){
		Log.d("debug","onMute");
		sendMsg(SshConnection.EXEC_MUTE);
	}
	
	public void onUnmute(View view){
		Log.d("debug","onUnmute");
		sendMsg(SshConnection.EXEC_UNMUTE);
	}
	
	public void onPowerOff(View view){
		Log.d("debug","onPowerOff");
		sendMsg(SshConnection.EXEC_POWER_OFF);
	}
}
