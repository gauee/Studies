package pl.gauee.wishlist.apk.listeners;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public abstract class OnClickTakePhotoListener extends CustomOnClick{
	private final long itemId;

	public OnClickTakePhotoListener(Context context, long itemId) {
		super(context);
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
