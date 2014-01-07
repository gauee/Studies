package pl.gauee.wishlist.apk.listeners;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class OnClickDeleteItemListener implements OnClickListener {
	private final Context context;
	private final long itemId;

	public OnClickDeleteItemListener(Context context, long itemId) {
		this.context = context;
		this.itemId = itemId;
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(context, "Usuniecie artykułu" + itemId, Toast.LENGTH_SHORT)
				.show();
	}
	
    
}
