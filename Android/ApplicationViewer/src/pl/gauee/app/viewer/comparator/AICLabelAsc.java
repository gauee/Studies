package pl.gauee.app.viewer.comparator;

import pl.gauee.app.viewer.database.CustomApplicationInfo;
import android.content.pm.PackageManager;

public class AICLabelAsc extends ApplicationInfoComparator {

	public AICLabelAsc(PackageManager pm) {
		super(pm);
	}


	@Override
	public int compare(CustomApplicationInfo arg0, CustomApplicationInfo arg1) {
		// TODO Auto-generated method stub
		return arg0.getAppInfo().loadLabel(packageManager).toString()
				.compareToIgnoreCase(arg1.getAppInfo().loadLabel(packageManager).toString());
	};

}
