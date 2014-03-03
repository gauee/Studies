package pl.gauee.android.acm;

import android.content.Context;
import android.content.Intent;

public class AcConnectReceiver extends AbstractAcReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		saveInSharedPref(context, connectKey, System.currentTimeMillis());
	}
	
}
