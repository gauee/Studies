package com.example.remotecontroller.services;

import com.example.remotecontroller.classes.ExecuteOnRemoteHost;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;



public class SshConnection extends Service {

	public static final int EXEC_MUTE = 0;
	public static final int EXEC_UNMUTE = 1;
	public static final int EXEC_LOCK_SCREEN = 2;
	public static final int EXEC_UNLOCK_SCREEN = 3;
	public static final int EXEC_POWER_OFF = 4;

	private static final String COMMAND_LOCK_SCREEN = "export DISPLAY=:0; gnome-screensaver; gnome-screensaver-command -l;";
	private static final String COMMAND_UNLOCK_SCREEN = "export DISPLAY=:0; gnome-screensaver; gnome-screensaver-command -d;";
	private static final String COMMAND_MUTE = "amixer set Master 0";
	private static final String COMMAND_UNMUTE = "amixer set Master 100";
	private static final String COMMAND_POWER_OFF = "sudo -S -p '' shutdown 1";
	
	private String login;
	private String pass;
	private String host;

	final Messenger mMessenger = new Messenger(new IncomingHandler());

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		host = intent.getExtras().getString("host");
		login = intent.getExtras().getString("login");
		pass = intent.getExtras().getString("pass");

		return mMessenger.getBinder();
	}

	class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case EXEC_LOCK_SCREEN:
				Toast.makeText(getApplicationContext(), "Lock remote screen",
						Toast.LENGTH_SHORT).show();
				new ExecuteOnRemoteHost().execute(login,pass,host,COMMAND_LOCK_SCREEN);
				break;
			case EXEC_UNLOCK_SCREEN:
				Toast.makeText(getApplicationContext(), "Unlock remote screen",
						Toast.LENGTH_SHORT).show();
				new ExecuteOnRemoteHost().execute(login,pass,host,COMMAND_UNLOCK_SCREEN);
				break;
			case EXEC_MUTE:
				Toast.makeText(getApplicationContext(), "Mute remote host",
						Toast.LENGTH_SHORT).show();
				new ExecuteOnRemoteHost().execute(login,pass,host,COMMAND_MUTE);
				break;
			case EXEC_UNMUTE:
				Toast.makeText(getApplicationContext(), "Unmute remote host",
						Toast.LENGTH_SHORT).show();
				new ExecuteOnRemoteHost().execute(login,pass,host,COMMAND_UNMUTE);
				break;
			case EXEC_POWER_OFF:
				Toast.makeText(getApplicationContext(),
						"Power off remote host", Toast.LENGTH_SHORT).show();
				new ExecuteOnRemoteHost().execute(login,pass,host,COMMAND_POWER_OFF);
				break;
			}
		}
	}

}
