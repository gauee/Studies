package com.example.remotecontroller.services;

import com.example.remotecontroller.classes.ConnectionRemoteApp;

import remote.controller.server.messages.*;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;


public class AppConnection extends Service {

	private String host;

	public static final int EXEC_LOCK_SCREEN = 1;
	public static final int EXEC_UNLOCK_SCREEN = 2;
	public static final int EXEC_MUTE = 10;
	public static final int EXEC_UNMUTE = 11;
	public static final int EXEC_SOUND_UP = 20;
	public static final int EXEC_SOUND_DOWN = 21;
	public static final int EXEC_SWITCH_WINDOW_FORWARD = 31;
	public static final int EXEC_SWITCH_WINDOW_BACKWARD = 32;
	public static final int EXEC_POWER_OFF = 40;
	public static final int EXEC_REBOOT = 50;
	public static final int EXEC_MINIMALIZE = 60;
	public static final int EXEC_MAXIMALIZE = 70;

	final Messenger mMessenger = new Messenger(new IncomingHandler());

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		host = intent.getExtras().getString("host");

		return mMessenger.getBinder();
	}

	class IncomingHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			SwitchWindow sw;
			switch (msg.what) {
			case EXEC_LOCK_SCREEN:
				Toast.makeText(getApplicationContext(), "Lock remote screen",
						Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new LockScreen());
				break;
			case EXEC_UNLOCK_SCREEN:
				Toast.makeText(getApplicationContext(), "Unlock remote screen",
						Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new UnlockScreen());
				break;
			case EXEC_MUTE:
				Toast.makeText(getApplicationContext(), "Mute remote host",
						Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new Mute());
				break;
			case EXEC_UNMUTE:
				Toast.makeText(getApplicationContext(), "Unmute remote host",
						Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new Unmute());
				break;
			case EXEC_SOUND_UP:
				Toast.makeText(getApplicationContext(), "Sound up remote host",
						Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new SoundUp());
				break;
			case EXEC_SOUND_DOWN:
				Toast.makeText(getApplicationContext(),
						"Sound down remote host", Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new SoundDown());
				break;
			case EXEC_SWITCH_WINDOW_FORWARD:
				Toast.makeText(getApplicationContext(),
						"Switch forward window remote host", Toast.LENGTH_SHORT)
						.show();
				sw = new SwitchWindow(true);
				new ConnectionRemoteApp().execute(host, sw);
				break;
			case EXEC_SWITCH_WINDOW_BACKWARD:
				Toast.makeText(getApplicationContext(),
						"Switch backward window remote host",
						Toast.LENGTH_SHORT).show();
				sw = new SwitchWindow(false);
				new ConnectionRemoteApp().execute(host, sw);
				break;
			case EXEC_POWER_OFF:
				Toast.makeText(getApplicationContext(), "PowerOff remote host",
						Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new PowerOff());
				break;
			case EXEC_REBOOT:
				Toast.makeText(getApplicationContext(), "Reboot remote host",
						Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new Reboot());
				break;
			case EXEC_MINIMALIZE:
				Toast.makeText(getApplicationContext(), "Minimalize window on remote host",
						Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new MinimalizeWindow());
				break;
			case EXEC_MAXIMALIZE:
				Toast.makeText(getApplicationContext(), "Maximalize window on remote host",
						Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new MaximalizeWindow());
				break;

			}
		}
	}
}
