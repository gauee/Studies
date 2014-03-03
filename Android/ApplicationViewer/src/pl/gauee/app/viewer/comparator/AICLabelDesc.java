package pl.gauee.app.viewer.comparator;

import pl.gauee.app.viewer.database.CustomApplicationInfo;
import android.content.pm.PackageManager;

public class AICLabelDesc extends ApplicationInfoComparator {

	public AICLabelDesc(PackageManager pm) {
		super(pm);
	}

	public int compare(android.content.pm.ApplicationInfo lhs,
			android.content.pm.ApplicationInfo rhs) {
		return rhs.loadLabel(packageManager).toString()
				.compareToIgnoreCase(lhs.loadLabel(packageManager).toString());
	}

	@Override
	public int compare(CustomApplicationInfo arg0, CustomApplicationInfo arg1) {
		// TODO Auto-generated method stub
		return arg1.getAppInfo().loadLabel(packageManager).toString()
				.compareToIgnoreCase(arg0.getAppInfo().loadLabel(packageManager).toString());
	};


}
