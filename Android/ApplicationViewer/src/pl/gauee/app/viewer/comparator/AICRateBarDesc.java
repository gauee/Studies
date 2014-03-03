package pl.gauee.app.viewer.comparator;

import pl.gauee.app.viewer.ApplicationAdapter;
import pl.gauee.app.viewer.database.CustomApplicationInfo;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class AICRateBarDesc extends ApplicationInfoComparator {

	private final String rateSuf = ApplicationAdapter.rateSuf;
	
	public AICRateBarDesc(PackageManager pm) {
		super(pm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(CustomApplicationInfo arg0, CustomApplicationInfo arg1) {
		// TODO Auto-generated method stub
		return Float.compare(arg1.getRate(), arg0.getRate());
	}
}