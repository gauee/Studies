package pl.gauee.wishlist.apk.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import pl.gauee.wishlist.apk.activities.ListItemsPreviewActivity;

public class OnClickListRowListener implements OnClickListener {

	private final Context context;
	private final long listId;

	public OnClickListRowListener(Context context, long listId) {
		super();
		this.context = context;
		this.listId = listId;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, ListItemsPreviewActivity.class);
		intent.putExtra("listId", listId);
		context.startActivity(intent);
	}

}
