package pl.gauee.wishlist.apk.listeners;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class OnClickDeleteItemListener extends CustomOnClick {
	private final long itemId;

	public OnClickDeleteItemListener(Context context, long itemId) {
		super(context);
		this.itemId = itemId;
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(context, "Usuniecie artykułu" + itemId, Toast.LENGTH_SHORT)
				.show();
	}
	
    
}
