package pl.gauee.wishlist.apk.listeners;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickShowPhotoListener  implements OnClickListener {
	private final Context context;
	private final long itemId;

	public OnClickShowPhotoListener(Context context, long itemId) {
		this.context = context;
		this.itemId = itemId;
	}

	@Override
	public void onClick(View v) {
//		
//		Intent intent = new Intent(context, PhotoPreviewActivity.class);
//                
//                context.startActivity(intent);
//		
	}

}
