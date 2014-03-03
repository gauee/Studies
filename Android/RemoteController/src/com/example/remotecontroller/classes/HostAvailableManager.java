package com.example.remotecontroller.classes;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

public class HostAvailableManager extends AsyncTask<String, Void, Collection<String>> {

	 @Override
	protected Collection<String> doInBackground(String... params) {
		 Collection<String> list =new LinkedList<String>();
		 try{
			 for(int i=1;i<255;++i){
				 InetAddress tmp = InetAddress.getByName(params[0]+i);
				 //Log.d(HostAvailableManager.class.getName(), tmp.getHostAddress());
				 if(tmp.isReachable(20)){
					 Log.d("HostAvailableManager", "Znalazł " + tmp.getHostName());
					 list.add(params[0]+i);
				 }
			 }
			 Log.d("HostAvailableManager","finished scanning network");
			 return list;
		 }catch(Exception e){
			 Log.d(HostAvailableManager.class.getSimpleName(),e.getMessage());
			 return null;
		 }
	}
}
