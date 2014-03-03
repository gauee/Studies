package pl.gauee.wishlist.apk.listeners;

import pl.gauee.wishlist.apk.activities.ItemDetailsActivity;
import pl.gauee.wishlist.utils.persistance.rest.RestWishItem;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class OnClickPreviewItemListener extends CustomOnClick{
	private final RestWishItem item;

	public OnClickPreviewItemListener(Context context, RestWishItem item) {
		super(context);
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
