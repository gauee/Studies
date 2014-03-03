package pl.gauee.app.viewer.comparator;

import pl.gauee.app.viewer.ApplicationAdapter;
import pl.gauee.app.viewer.database.CustomApplicationInfo;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class AICRateBarAsc extends ApplicationInfoComparator {

	private final String rateSuf = ApplicationAdapter.rateSuf;
	
	public AICRateBarAsc(PackageManager pm) {
		super(pm);
		// TODO Auto-generated constructor stub
	}


	@Override
	public int compare(CustomApplicationInfo arg0, CustomApplicationInfo arg1){
		return Float.compare(arg0.getRate(), arg1.getRate());
	}
}
