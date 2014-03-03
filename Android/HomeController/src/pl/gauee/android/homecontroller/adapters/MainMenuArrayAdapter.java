package pl.gauee.android.homecontroller.adapters;

import java.util.List;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MainMenuArrayAdapter extends ArrayAdapter<MenuItem>{

	
	
	public MainMenuArrayAdapter(Context context, int textViewResourceId,
			List<MenuItem> objects) {
		super(context, textViewResourceId, objects);
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}

	@Override
	public MenuItem getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}

}
