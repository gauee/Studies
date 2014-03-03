package pl.gauee.app.viewer;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class OnApplicationClickListener implements OnClickListener{

	private Intent toRunIntent;

	public OnApplicationClickListener(Intent toRun) {
		this.toRunIntent = toRun;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		toRunIntent.setAction(Intent.ACTION_MAIN);
		toRunIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		runByContext();
	}
	
	abstract public void runByContext();

	public Intent getToRunIntent() {
		return toRunIntent;
	}

	public void setToRunIntent(Intent toRunIntent) {
		this.toRunIntent = toRunIntent;
	}
	
}
