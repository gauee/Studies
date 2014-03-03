package pl.gauee.android.acm;

import pl.gauee.android.acm.db.CustomChargingInfo;
import pl.gauee.android.acm.db.DatabaseAccess;
import pl.gauee.android.acm.db.DatabaseOpenHelper;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public abstract class AbstractAcReceiver extends BroadcastReceiver {

	private static final String spName = "AcManager";

	protected String connectKey= "ac_con_event";
	protected String disconnectKey = "ac_disc_event";
	
	private DatabaseAccess dbAccess = null;
	private DatabaseOpenHelper dbHelper = null;

	private int notifiId = 87352191;
	
	protected void saveInSharedPref(Context context, String key, long value) {
		SharedPreferences sp = context.getSharedPreferences(spName,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	protected long getFromSharedPref(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(spName,
				Context.MODE_PRIVATE);
		long rslt = sp.getLong(key, -1);
		cleanUp(context, key);
		return rslt;
	}

	protected void cleanUp(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(spName,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove(key);
		editor.commit();
	}
	
	public static void cleanAll(Context context){
		SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.clear();
		edit.commit();
	}
	

	protected CustomChargingInfo saveInDb(Context context) {
		long start = getFromSharedPref(context, connectKey);
		long end = getFromSharedPref(context, disconnectKey);
		
		if(start == -1 || end == -1){
			Log.d("gauee", "something gone wrong.");
			return null;
		}
		
		CustomChargingInfo cci = new CustomChargingInfo(start,end);
		initDb(context);
		dbAccess.insert(cci);
		return cci;
		
	};
	
	private void initDb(Context context) {
		// TODO Auto-generated method stub
		if (dbHelper == null) {
			dbHelper = new DatabaseOpenHelper(context);
		}
		if (dbAccess == null) {
			dbAccess = new DatabaseAccess(dbHelper.getWritableDatabase());
		}
	}



	protected void createNotification(Context context,CustomChargingInfo cci) {
		Intent intent = new Intent(context,FirstActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pIntent = PendingIntent.getActivity(context, 1, intent,0);
		
		if(context instanceof FirstActivity){
			((FirstActivity)context).refreshListView();
		}
		
		Notification noti = new Notification.Builder(context)
		.setContentTitle("Zakończono ładowanie telefonu")
		.setContentText("Ładowano: " + CustomChargingInfo.getDuration(cci.getStopCharging()-cci.getStartCharging()))
		.setContentIntent(pIntent)
		.setSmallIcon(R.drawable.ac_manager)
		.build();
		
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		noti.flags |= Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(notifiId, noti);
		
	}
	
}
