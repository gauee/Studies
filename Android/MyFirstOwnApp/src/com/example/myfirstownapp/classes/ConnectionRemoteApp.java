package com.example.myfirstownapp.classes;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import remote.controller.server.communication.messages.BasicDekorator;
import remote.controller.server.communication.messages.MsgCommunication;

import android.os.AsyncTask;

public class ConnectionRemoteApp extends AsyncTask<Object, Integer, Void> {

	private int defaultPort = 45665;

	@Override
	protected Void doInBackground(Object... arg0) {
		// TODO Auto-generated method stub

		Socket socket;
		try {
			socket = new Socket((String) arg0[0], defaultPort);
			MsgCommunication msg = new MsgCommunication(
					(BasicDekorator) arg0[1]);
			ByteBuffer buf = MsgCommunication.getByteBuffer();
			msg.tranferInto(buf);
			socket.getOutputStream().write(buf.array());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
