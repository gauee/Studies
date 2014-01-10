package pl.gauee.wishlist.apk.adapters;

import java.util.List;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.listeners.OnClickListRowListener;
import pl.gauee.wishlist.utils.persistance.WishList;
import pl.gauee.wishlist.utils.persistance.rest.RestWishList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<RestWishList> {

	private Context context;
	private List<RestWishList> list;
	private final boolean isForDelete;

	public ListAdapter(Context context, int textViewResourceId,
			List<RestWishList> objects, boolean isForDelete) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = objects;
		this.isForDelete = isForDelete;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ((list == null) ? 0 : list.size());
	}

	@Override
	public RestWishList getItem(int position) {
		// TODO Auto-generated method stub
		return ((list == null) ? null : list.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View view = convertView;
		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.row_item_lists, null);
		}

		RestWishList listName = list.get(position);
		if (null != listName) {
			TextView listLabel = (TextView) view
					.findViewById(R.id.rowItem_lists);
			listLabel.setText(listName.getName());

			listLabel.setOnClickListener(new OnClickListRowListener(context,
					listName.getId(),
					isForDelete));

		}

		return view;
	}
}
