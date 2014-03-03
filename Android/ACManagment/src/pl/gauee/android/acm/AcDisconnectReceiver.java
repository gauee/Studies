package pl.gauee.android.acm;

import pl.gauee.android.acm.db.CustomChargingInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AcDisconnectReceiver extends AbstractAcReceiver {

	public void onReceive(android.content.Context context,
			android.content.Intent intent) {
		saveInSharedPref(context, disconnectKey, System.currentTimeMillis());
		CustomChargingInfo cci = saveInDb(context);
		if (cci != null) {
			createNotification(context, cci);
		}
	}

}
