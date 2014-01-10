package pl.gauee.wishlist.apk.listeners;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.remote.RemoteAccess;
import pl.gauee.wishlist.utils.persistance.rest.RestWishItem;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

public class OnClickBoughtItemListener extends CustomOnClick {
	private final long itemId;
	private final ImageView imgV;

	public OnClickBoughtItemListener(Context context, ImageView img,long itemId) {
		super(context);
		this.itemId = itemId;
		this.imgV = img;
	}

	@Override
	public void onClick(View v) {
		new RemoteAccess<Boolean>(context) {
			@Override
			public void onReceivedResult(Boolean result) {
				if(result){
					Toast.makeText(context, "Przedmiot został kupiony", Toast.LENGTH_SHORT).show();
					if(imgV != null){
						imgV.setImageResource(R.drawable.boughtnot);
					}
				}else{
					Toast.makeText(context, "Kupno przedmiotu odwołano", Toast.LENGTH_SHORT).show();
					if(imgV != null){
						imgV.setImageResource(R.drawable.bought);
					}
					
				}
			}
		}.changeBuyStatusFor(itemId);
	}

}
