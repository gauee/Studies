package pl.gauee.app.viewer.database;

import android.content.pm.ApplicationInfo;

public class CustomApplicationInfo {

	private long id;
	private ApplicationInfo appInfo;
	private String appPackage;
	private float rate;
	
	public CustomApplicationInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomApplicationInfo(ApplicationInfo appInfo,String appPackage,float rate) {
		// TODO Auto-generated constructor stub
		this.appInfo = appInfo;
		this.appPackage = appPackage;
		this.rate = rate;
	}

	
	
	
	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}




	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public ApplicationInfo getAppInfo() {
		return appInfo;
	}

	public float getRate() {
		return rate;
	}
	
	public void setRate(float rate){
		this.rate = rate;
	}

	public String getAppPackage() {
		return appPackage;
	}

	@Override
	public String toString() {
		return "CustomApplicationInfo [id=" + id + ", appInfo=" + appInfo
				+ ", appPackage=" + appPackage + ", rate=" + rate + "]";
	}
	
	
	
}
