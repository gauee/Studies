package com.example.myfirstownapp.services;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.LinkedList;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class HostSearcher extends IntentService {

	private int result = Activity.RESULT_OK;
	private boolean interruped = false;
	public HostSearcher() {
		super(HostSearcher.class.getSimpleName());
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d("debug","Start service");
		interruped = false; 
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		if (extras == null) {
			return;
		}
		Messenger messenger = (Messenger) extras.get("MESSENGER");
		String hosts = "192.168.1.";
		InetAddress host;
		int i=0;
		while(!interruped && (++i)< 255) {
			try {
				host = InetAddress.getByName(hosts + i);
				if (host.isReachable(200)) {
					Message msg = Message.obtain();
					msg.arg1 = result;
					msg.obj = (hosts + i);
					try {
						messenger.send(msg);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		stopSelf();	
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("debug", "destoring service");
		interruped = true;
		
	}

}
