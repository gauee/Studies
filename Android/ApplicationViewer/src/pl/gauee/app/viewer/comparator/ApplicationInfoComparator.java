package pl.gauee.app.viewer.comparator;

import java.util.Comparator;

import pl.gauee.app.viewer.database.CustomApplicationInfo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

public abstract class ApplicationInfoComparator implements Comparator<CustomApplicationInfo> {

	protected final PackageManager packageManager;
	
	public ApplicationInfoComparator(PackageManager pm) {
		// TODO Auto-generated constructor stub
		this.packageManager = pm;
	}
}
