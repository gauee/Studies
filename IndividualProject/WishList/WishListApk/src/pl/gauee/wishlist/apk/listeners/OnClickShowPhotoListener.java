package pl.gauee.wishlist.apk.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class OnClickShowPhotoListener  extends CustomOnClick{
	private final long itemId;

	public OnClickShowPhotoListener(Context context, long itemId) {
		super(context);
		this.itemId = itemId;
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(context, "Podgląd " + itemId, Toast.LENGTH_SHORT)
				.show();
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setType("image/*");
//		intent.setData(Uri.parse("android.resource://pl.gauee.wishlist.apk/drawable/insert_image"));
//		intent.setData(Uri.parse("R.drawable.insert_image"));
//		intent.setData(Uri.parse("file:///android_asset/photo.jpg"));
//		intent.setData(Uri.parse("http://www.google.com/intl/en_ALL/images/srpr/logo1w.png"));
		
//		context.startActivity(intent);
		
	}

}
