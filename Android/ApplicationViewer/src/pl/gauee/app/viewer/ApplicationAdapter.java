package pl.gauee.app.viewer;

import java.util.List;

import pl.gauee.app.viewer.database.CustomApplicationInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public abstract class ApplicationAdapter extends ArrayAdapter<CustomApplicationInfo> {

	public static final String rateSuf = "rate_";
	public static String PREFS_NAME = "RatingInfo";
	
	private Context context;
	private List<CustomApplicationInfo> apps;
	private PackageManager packageManager;

	public ApplicationAdapter(Context context, int textViewResourceId,
			List<CustomApplicationInfo> apps) {
		super(context, textViewResourceId, apps);
		this.context = context;
		this.apps = apps;
		this.packageManager = context.getPackageManager();

	}

	@Override
	public int getCount() {
		return ((apps == null) ? 0 : apps.size());
	}

	@Override
	public CustomApplicationInfo getItem(int position) {
		return ((apps == null) ? null : apps.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.list_row, null);
		}

		CustomApplicationInfo data = apps.get(position);
		float appRate = 0;
		if (null != data) {
			TextView appName = (TextView) view.findViewById(R.id.textView1);
			ImageView iconview = (ImageView) view.findViewById(R.id.imageView1);
			
			String appLabel = data.getAppInfo().loadLabel(packageManager).toString();
			appName.setText(appLabel);
			iconview.setImageDrawable(data.getAppInfo().loadIcon(packageManager));
			
			Intent launcher = packageManager.getLaunchIntentForPackage(data.getAppInfo().packageName);
			
			iconview.setOnClickListener(new OnApplicationClickListener(launcher) {
				
				@Override
				public void runByContext() {
					context.startActivity(getToRunIntent());
				}
			});
			RatingBar bar = (RatingBar)view.findViewById(R.id.ratingBar1);
			appRate = data.getRate();
			bar.setRating(appRate);
			bar.setOnRatingBarChangeListener(new SmartOnRatingBarChanged(data) {
				
				@Override
				public void onRatingChanged(RatingBar ratingBar, float rating,
						boolean fromUser) {
					Log.d("gauee","changing rating");
					if(fromUser){
						CustomApplicationInfo appInf = getCustAppInfo();
						appInf.setRate(rating);
						saveInDb(appInf);
					}
				}
			});
		}
		
		return view;
	}
	
	abstract void saveInDb(CustomApplicationInfo cai);
}
