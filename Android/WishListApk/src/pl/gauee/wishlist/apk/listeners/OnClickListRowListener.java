package pl.gauee.wishlist.apk.listeners;

import pl.gauee.wishlist.apk.activities.ListItemsPreviewActivity;
import pl.gauee.wishlist.apk.remote.RemoteAccess;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

abstract public class OnClickListRowListener extends CustomOnClick {

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
			new RemoteAccess<Boolean>(context) {

				@Override
				public void onReceivedResult(Boolean result) {
					reloadListView();
				}
				
				
			}.deleteList(listId);
		} else {
			Intent intent = new Intent(context, ListItemsPreviewActivity.class);
			intent.putExtra("listId", listId);
			context.startActivity(intent);
		}
	}
	
	

	
	public long getListId() {
		return listId;
	}

	abstract public void reloadListView();
}
