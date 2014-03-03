package com.example.remotecontroller;

import com.example.remotecontroller.services.AppConnection;
import com.example.remotecontroller.services.SshConnection;

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

public class MainAppActionActivity extends Activity {
	private String host;

	private Intent appRemoteConnection;
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
		setContentView(R.layout.main_app_action_activity);
		Intent intent = getIntent();
		host = intent.getStringExtra("host");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();		
		appRemoteConnection = new Intent(getApplicationContext(),AppConnection.class);
		appRemoteConnection.putExtra("host",host);
		bindService(appRemoteConnection, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d("debug","exiting from onStop");
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
		sendMsg(AppConnection.EXEC_LOCK_SCREEN);
	}
	
	public void onUnlockScreen(View view){
		Log.d("debug","onUnlockScreen");
		sendMsg(AppConnection.EXEC_UNLOCK_SCREEN);
	}
	
	public void onMute(View view){
		Log.d("debug","onMute");
		sendMsg(AppConnection.EXEC_MUTE);
	}
	
	public void onUnmute(View view){
		Log.d("debug","onUnmute");
		sendMsg(AppConnection.EXEC_UNMUTE);
	}
	
	public void onPowerOff(View view){
		Log.d("debug","onPowerOff");
		sendMsg(AppConnection.EXEC_POWER_OFF);
	}
	
	public void onReboot(View view){
		Log.d("debug","onReboot");
		sendMsg(AppConnection.EXEC_REBOOT);
	}
	
	public void onSoundUp(View view){
		Log.d("debug","onSoundUp");
		sendMsg(AppConnection.EXEC_SOUND_UP);
	}
	
	public void onSoundDown(View view){
		Log.d("debug","onSoundDown");
		sendMsg(AppConnection.EXEC_SOUND_DOWN);
	}
	
	public void onSwitchWindowForward(View view){
		Log.d("debug","onSwitchWindowForward");
		sendMsg(AppConnection.EXEC_SWITCH_WINDOW_FORWARD);
	}
	
	public void onSwitchWindowBackward(View view){
		Log.d("debug","onSwitchWindowBackward");
		sendMsg(AppConnection.EXEC_SWITCH_WINDOW_BACKWARD);
	}
	
	public void onMinimalize(View view){
		Log.d("debug","onMinimalize");
		sendMsg(AppConnection.EXEC_MINIMALIZE);
	}
	
	public void onMaximalize(View view){
		Log.d("debug","onMaximalize");
		sendMsg(AppConnection.EXEC_MAXIMALIZE);
	}
	
	public void onExit(View view){
		finish();
	}
	
}
