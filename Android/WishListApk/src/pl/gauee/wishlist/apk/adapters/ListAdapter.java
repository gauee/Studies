package pl.gauee.wishlist.apk.adapters;

import java.util.Iterator;
import java.util.List;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.listeners.OnClickListRowListener;
import pl.gauee.wishlist.utils.persistance.rest.RestWishList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapter extends ArrayAdapter<RestWishList> {

	private Context context;
	private List<RestWishList> lists;
	private final boolean isForDelete;
	private int layoutId;

	public ListAdapter(Context context, int textViewResourceId,
			List<RestWishList> objects, boolean isForDelete) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layoutId = textViewResourceId;
		this.lists = objects;
		this.isForDelete = isForDelete;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ((lists == null) ? 0 : lists.size());
	}

	@Override
	public RestWishList getItem(int position) {
		// TODO Auto-generated method stub
		return ((lists == null) ? null : lists.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View view = convertView;
		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(layoutId, null);
		}

		RestWishList listName = lists.get(position);
		if (null != listName) {

			OnClickListener previewButton = new OnClickListRowListener(context,
					listName.getId(), isForDelete) {

				@Override
				public void reloadListView() {
					reloadAdapterView(getListId());
				}
			};

			if (!isForDelete) {
				TextView listLabel = (TextView) view
						.findViewById(R.id.rowItem_listLabel);
				listLabel.setText(listName.getName());
				ImageView previewList = ((ImageView) view
						.findViewById(R.id.rowItem_preview));
				previewList.setFocusable(true);
				previewList.setOnClickListener(previewButton);

				ImageView editList = ((ImageView) view
						.findViewById(R.id.rowItem_edit));
				editList.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						printMsg("Choose edit");
					}
				});
				ImageView shareList = ((ImageView) view
						.findViewById(R.id.rowItem_share));
				shareList.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						printMsg("Choose share");
					}
				});
				ImageView deleteList = ((ImageView) view
						.findViewById(R.id.rowItem_delete));
				deleteList.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						printMsg("Choose delete");
					}
				});
			} else {
				Button listLabel = (Button) view
						.findViewById(R.id.rowItem_listLabel);
				listLabel.setText(listName.getName());
				listLabel.setOnClickListener(previewButton);
			}

		}

		return view;
	}

	private void printMsg(String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public void reloadAdapterView(long listId) {
		if (isForDelete) {
			Iterator<RestWishList> it = lists.iterator();
			while (it.hasNext()) {
				RestWishList list = it.next();
				if (list.getId() == listId) {
					it.remove();
					break;
				}
			}
		}

		this.notifyDataSetChanged();
	}

}
