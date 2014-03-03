package pl.gauee.app.viewer;

import pl.gauee.app.viewer.database.CustomApplicationInfo;
import android.widget.RatingBar;

public abstract class SmartOnRatingBarChanged implements RatingBar.OnRatingBarChangeListener {

	private String packageName;
	private CustomApplicationInfo custAppInfo;
	
	public SmartOnRatingBarChanged(String packageName) {
		this.packageName = packageName;
	}
	
	public SmartOnRatingBarChanged(CustomApplicationInfo applicationInfo) {
		this.custAppInfo = applicationInfo;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public CustomApplicationInfo getCustAppInfo() {
		return custAppInfo;
	}

	public void setCustAppInfo(CustomApplicationInfo custAppInfo) {
		this.custAppInfo = custAppInfo;
	}
	
	
}
