package pl.gauee.wishlist.apk.listeners;

import pl.gauee.wishlist.apk.activities.ListItemsPreviewActivity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class OnClickListRowListener extends CustomOnClick {

	private final long listId;
	private final boolean isDeleteClick;

	public OnClickListRowListener(Context context, long listId,
			boolean isDeleteClick) {
		super(context);
		this.listId = listId;
		this.isDeleteClick = isDeleteClick;
	}

	@Override
	public void onClick(View v) {
		if (isDeleteClick) {
			Toast.makeText(context, "Usuwamy wiersz", Toast.LENGTH_SHORT).show();
		} else {
			Intent intent = new Intent(context, ListItemsPreviewActivity.class);
			intent.putExtra("listId", listId);
			context.startActivity(intent);
		}
	}

}
