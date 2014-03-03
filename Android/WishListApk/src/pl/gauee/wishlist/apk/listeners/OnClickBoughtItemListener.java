package pl.gauee.wishlist.apk.listeners;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.remote.RemoteAccess;
import pl.gauee.wishlist.utils.persistance.rest.RestWishItem;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

abstract public class OnClickBoughtItemListener extends CustomOnClick {
	private final long itemId;
	
	public OnClickBoughtItemListener(Context context, long itemId) {
		super(context);
		this.itemId = itemId;
	}

	@Override
	public void onClick(View v) {
		new RemoteAccess<Boolean>(context) {
			@Override
			public void onReceivedResult(Boolean result) {
				if(result){
					Toast.makeText(context, "Przedmiot został kupiony", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(context, "Kupno przedmiotu odwołano", Toast.LENGTH_SHORT).show();
				}
				refreshAdapter(result);
				
			}
		}.changeBuyStatusFor(itemId);
	}
	
	public long getItemId() {
		return itemId;
	}

	public abstract void refreshAdapter(boolean isBought);
}
