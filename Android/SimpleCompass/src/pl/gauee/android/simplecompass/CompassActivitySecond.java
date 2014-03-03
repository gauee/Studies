package pl.gauee.android.simplecompass;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class CompassActivitySecond extends Activity implements
		SensorEventListener {

	private ImageView image;
	private float curretnDegree = 0f;
	private SensorManager mSensorManager;

	TextView tvHeadling;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_layout_second);

		image = (ImageView) findViewById(R.id.imageViewCompass);
		tvHeadling = (TextView) findViewById(R.id.tvHeading);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		float degree = Math.round(arg0.values[0]);

		tvHeadling.setText("Azymut: " + Float.toString(degree) + " stopni");

		RotateAnimation ra = new RotateAnimation(curretnDegree, -degree,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		ra.setDuration(100);

		ra.setFillAfter(true);

		image.startAnimation(ra);
		curretnDegree = -degree;

	}

}
