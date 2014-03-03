package pl.gauee.wishlist.apk.listeners;

import android.content.Context;
import android.view.View.OnClickListener;

public abstract class CustomOnClick implements OnClickListener {

	protected final Context context;
	
	public CustomOnClick(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
}
