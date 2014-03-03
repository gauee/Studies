package com.example.myfirstownapp.classes;

import java.io.IOException;
import java.util.Properties;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import android.os.AsyncTask;
import android.util.Log;

public class ExecuteOnRemoteHost extends AsyncTask<String, Integer, Void> {

	private Session session;
	private Channel channel;

	@Override
	protected Void doInBackground(String... params) {
		if (params.length != 4) {
			return null;
		}

		JSch jsch = new JSch();
		try {
			session = jsch.getSession(params[0], params[2]);
			session.setPassword(params[1]);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(params[3]);
			channel.connect();
			channel.run();

			if (params[3].startsWith("sudo")) {
				Log.d("debuug", "sudo trzeba dodac haslo");
				try {
					channel.getOutputStream().write(
							(params[1] + "\n").getBytes());
					channel.getOutputStream().flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			channel.disconnect();
			session.disconnect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
