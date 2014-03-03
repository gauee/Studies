package com.example.myfirstownapp.services;

import remote.controller.server.messages.LockScreen;
import remote.controller.server.messages.Mute;
import remote.controller.server.messages.PowerOff;
import remote.controller.server.messages.Reboot;
import remote.controller.server.messages.SwitchWindow;
import remote.controller.server.messages.UnlockScreen;
import remote.controller.server.messages.Unmute;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

import com.example.myfirstownapp.classes.ConnectionRemoteApp;

public class AppConnection extends Service {

	private String host;

	public static final int EXEC_LOCK_SCREEN = 1;
	public static final int EXEC_UNLOCK_SCREEN = 2;
	public static final int EXEC_MUTE = 10;
	public static final int EXEC_UNMUTE = 11;
	public static final int EXEC_SOUND_UP = 20;
	public static final int EXEC_SOUND_DOWN = 21;
	public static final int EXEC_SWITCH_WINDOW = 30;
	public static final int EXEC_SWITCH_WINDOW_FORWARD = 31;
	public static final int EXEC_SWITCH_WINDOW_BACKWARD = 32;
	public static final int EXEC_END_SWITCH_WINDOW = 33;
	public static final int EXEC_POWER_OFF = 40;
	public static final int EXEC_REBOOT = 50;

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
				new ConnectionRemoteApp().execute(host, new Unmute());
				break;
			case EXEC_SOUND_DOWN:
				Toast.makeText(getApplicationContext(),
						"Sound down remote host", Toast.LENGTH_SHORT).show();
				new ConnectionRemoteApp().execute(host, new Unmute());
				break;
			case EXEC_SWITCH_WINDOW:
				Toast.makeText(getApplicationContext(),
						"Switch window start remote host", Toast.LENGTH_SHORT)
						.show();
				sw = new SwitchWindow();
				sw.setFirst(true);
				new ConnectionRemoteApp().execute(host, sw);
				break;
			case EXEC_SWITCH_WINDOW_FORWARD:
				Toast.makeText(getApplicationContext(),
						"Switch forward window remote host", Toast.LENGTH_SHORT)
						.show();
				sw = new SwitchWindow();
				sw.setForward(true);
				new ConnectionRemoteApp().execute(host, sw);
				break;
			case EXEC_SWITCH_WINDOW_BACKWARD:
				Toast.makeText(getApplicationContext(),
						"Switch backward window remote host",
						Toast.LENGTH_SHORT).show();
				sw = new SwitchWindow();
				sw.setForward(false);
				new ConnectionRemoteApp().execute(host, sw);
				break;
			case EXEC_END_SWITCH_WINDOW:
				Toast.makeText(getApplicationContext(),
						"End switching window remote host", Toast.LENGTH_SHORT)
						.show();
				sw = new SwitchWindow();
				sw.setLast(true);
				new ConnectionRemoteApp().execute(host,sw);
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

			}
		}
	}
}
