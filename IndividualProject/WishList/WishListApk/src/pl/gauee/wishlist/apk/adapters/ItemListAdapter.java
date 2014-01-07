package pl.gauee.wishlist.apk.adapters;

import java.util.List;

import pl.gauee.wishlist.apk.R;
import pl.gauee.wishlist.apk.listeners.OnClickBoughtItemListener;
import pl.gauee.wishlist.apk.listeners.OnClickDeleteItemListener;
import pl.gauee.wishlist.apk.listeners.OnClickPreviewItemListener;
import pl.gauee.wishlist.utils.persistance.WishItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemListAdapter extends ArrayAdapter<WishItem>{

	private final Context context;
	private final List<WishItem> items;
	
	public ItemListAdapter(Context context, 
			int textViewResourceId,
			List<WishItem> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.items = objects;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ((items == null) ? 0 : items.size());
	}
	
	@Override
	public WishItem getItem(int position) {
		// TODO Auto-generated method stub
		return ((items == null ) ? null : items.get(position));
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = convertView;
		
		if(view == null){
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.row_item_items, null);
		}
		
		WishItem item = items.get(position);
		if(item != null){
			TextView itemLabel = (TextView)view.findViewById(R.id.rowItem_itemLabel);
			ImageView imageBuy = (ImageView)view.findViewById(R.id.rowItem_itemBought);
			ImageView imagePreview = (ImageView)view.findViewById(R.id.rowItem_itemPreview);
			ImageView imageDelete = (ImageView)view.findViewById(R.id.rowItem_itemDelete);
			
			
			itemLabel.setText(item.getName());
			
			imageBuy.setOnClickListener(new OnClickBoughtItemListener(context, item.getId()));
			imagePreview.setOnClickListener(new OnClickPreviewItemListener(context, item));
			imageDelete.setOnClickListener(new OnClickDeleteItemListener(context, item.getId()));
		}
		
		return view;
	
	}

}
