package pl.gauee.wishlist.apk.listeners;

import pl.gauee.wishlist.apk.ItemDetailsActivity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public abstract class OnClickTakePhotoListener implements OnClickListener {
	private final Context context;
	private final long itemId;

	public OnClickTakePhotoListener(Context context, long itemId) {
		this.context = context;
		this.itemId = itemId;
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(context, "ZrobienieZdjęcia" + itemId, Toast.LENGTH_SHORT)
				.show();
		startCamera();
	}

	abstract public void startCamera();

}
