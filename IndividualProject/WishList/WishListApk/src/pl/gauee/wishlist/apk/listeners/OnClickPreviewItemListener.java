package pl.gauee.wishlist.apk.listeners;

import pl.gauee.wishlist.utils.persistance.WishItem;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import pl.gauee.wishlist.apk.activities.ItemDetailsActivity;

public class OnClickPreviewItemListener implements OnClickListener {
	private final Context context;
	private final WishItem item;

	public OnClickPreviewItemListener(Context context, WishItem item) {
		this.context = context;
		this.item = item;
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(context, "Podglad artykułu" + item.getId(),
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(context, ItemDetailsActivity.class);
		intent.putExtra("wishItem", item);
		context.startActivity(intent);

	}
}
