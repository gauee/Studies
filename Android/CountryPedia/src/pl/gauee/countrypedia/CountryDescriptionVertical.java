package pl.gauee.countrypedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDescriptionVertical extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_country_description_vertical);
		fillAllViews();
	}

	private void fillAllViews() {
		Intent iam = getIntent();
		addCountryFlag(iam);
		addCountryDescription(iam);
	}

	private void addCountryFlag(Intent iam) {
		ImageView img = (ImageView) findViewById(R.id.country_flag);
		img.setImageResource(iam.getIntExtra("flagId", R.drawable.pl));
	}

	private void addCountryDescription(Intent iam) {
		TextView descr = (TextView) findViewById(R.id.country_descr);
		descr.setText(getResources().getString(
				iam.getIntExtra("descrId", R.string.pl_descr_pl)));
		descr.setMovementMethod(new ScrollingMovementMethod());
	}

	public void goBackToWelcome(View v) {
		onBackPressed();
	}
}
