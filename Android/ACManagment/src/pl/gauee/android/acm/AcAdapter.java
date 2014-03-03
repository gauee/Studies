package pl.gauee.android.acm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pl.gauee.android.acm.db.CustomChargingInfo;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AcAdapter extends ArrayAdapter<CustomChargingInfo> {

	private Context context;
	private List<CustomChargingInfo> cciList;

	public AcAdapter(Context context, int textViewResourceId,
			List<CustomChargingInfo> apps) {
		super(context, textViewResourceId, apps);
		this.context = context;
		this.cciList = apps;
	}

	@Override
	public int getCount() {
		return ((cciList == null) ? 0 : cciList.size());
	}

	@Override
	public CustomChargingInfo getItem(int position) {
		return ((cciList == null) ? null : cciList.get(position));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.cci_row, null);
		}

		CustomChargingInfo data = cciList.get(position);
		if (null != data) {

			TextView dayView = (TextView) view.findViewById(R.id.cci_day);
			TextView durationView = (TextView) view
					.findViewById(R.id.cci_duration_time);

			dayView.setText(data.getStartDay());
			durationView.setText(CustomChargingInfo.getDuration(data.getStopCharging()
					- data.getStartCharging()));

			TextView startView = (TextView) view
					.findViewById(R.id.cci_start_time);
			TextView endView = (TextView) view.findViewById(R.id.cci_end_time);

			startView.setText(data.getStartInHours());
			endView.setText(data.getEndInHours());

		}

		return view;
	}


	
	
	public CharSequence getAverageDaily(){
		long averageTime=0;
		int days = 0;
		String prevDay = "";
		for(CustomChargingInfo cci : cciList){
			String curDay = cci.getStartDay().toString();
			if(!prevDay.equals(curDay)){
				prevDay = curDay;
				++days;
			}
			averageTime += cci.getStopCharging()-cci.getStartCharging();
		}
		
		if(days==0){
			return "brak danych";
		}
		
		
		
		return CustomChargingInfo.getDuration(averageTime/days);
	}

	public CharSequence getLastDayInfo() {
		long averageTime=0;
		String prevDay = "";
		for(CustomChargingInfo cci : cciList){
			String curDay = cci.getStartDay().toString();
			if(!prevDay.equals(curDay)){
				prevDay = curDay;
				averageTime = 0;
			}
			averageTime += cci.getStopCharging()-cci.getStartCharging();
		}
		
		return CustomChargingInfo.getDuration(averageTime);
	}
}
