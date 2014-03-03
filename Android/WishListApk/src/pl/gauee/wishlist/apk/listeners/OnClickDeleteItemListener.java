package pl.gauee.wishlist.apk.listeners;

import pl.gauee.wishlist.apk.remote.RemoteAccess;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

abstract public class OnClickDeleteItemListener extends CustomOnClick {
	private final long itemId;

	public OnClickDeleteItemListener(Context context, long itemId) {
		super(context);
		this.itemId = itemId;
	}

	@Override
	public void onClick(View v) {
		new RemoteAccess<Boolean>(context) {
			@Override
			public void onReceivedResult(Boolean result) {
				onDeleteItem(itemId);
			}
		}.deleteItem(itemId);
	}

	abstract public void onDeleteItem(long itemId2);
	
}
