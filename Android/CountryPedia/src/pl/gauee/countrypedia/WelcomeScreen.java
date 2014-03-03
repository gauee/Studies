package pl.gauee.countrypedia;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeScreen extends Activity {

	private static final int[] PL_COUNTRIES = new int[] {
			R.string.pl_country_pl, R.string.pl_country_ger,
			R.string.pl_country_spa, R.string.pl_country_uk,
			R.string.pl_country_usa, R.string.pl_country_rus, };

	private static final int[] EN_COUNTRIES = new int[] {
			R.string.en_country_pl, R.string.en_country_ger,
			R.string.en_country_spa, R.string.en_country_uk,
			R.string.en_country_usa, R.string.en_country_rus, };

	private static boolean englishChosen;

	public static boolean isEnglishChosen() {
		return englishChosen;
	}

	public static void setEnglishChosen(boolean englishChosen) {
		WelcomeScreen.englishChosen = englishChosen;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setOrientationLayout();
		setLanguageOnCreate();
	}

	private void setLanguageOnCreate() {
		if (isEnglishChosen()) {
			translatingEn();
		} else {
			translatingPl();
		}
	}

	private void setOrientationLayout() {
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			setContentView(R.layout.activity_welcome_screen_land);
		} else {
			setContentView(R.layout.activity_welcome_screen);
		}
	}

	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setOrientationLayout();
	}

	public void translateToPl(View v) {
		translatingPl();
	}

	private void translatingPl() {
		addPolishContryNames();
		disablePolishLanguageButton();
		setEnglishChosen(false);
	}

	public void translateToEn(View v) {
		translatingEn();
	}

	private void translatingEn() {
		addEnglishContryNames();
		disableEnglishLanguageButton();
		setEnglishChosen(true);
	}

	private void disableEnglishLanguageButton() {
		setButtonsActivated((Button) findViewById(R.id.language_pl),
				(Button) findViewById(R.id.language_en));
	}

	private void disablePolishLanguageButton() {
		setButtonsActivated((Button) findViewById(R.id.language_en),
				(Button) findViewById(R.id.language_pl));
	}

	private void setButtonsActivated(Button activate, Button deactivate) {
		activate.setActivated(true);
		deactivate.setActivated(false);
	}

	private void addEnglishContryNames() {
		LinearLayout countryList = (LinearLayout) findViewById(R.id.country_list);
		countryList.removeAllViews();
		int i = 0;

		final Intent it = new Intent(this, CountryDescriptionVertical.class);

		for (int c : EN_COUNTRIES) {
			Button child = new Button(this);
			child.setText(getResources().getString(c));
			child.setOnClickListener(new EnCountryButtonListener(i++,
					getResources().getConfiguration().orientation) {

				@Override
				public void onClickInHorizontal() {
					updateCurrentCountryFlag();
					updateCurrentCountryDescription();
				}

				@Override
				public void onClickInVertical() {
					it.putExtra("flagId", getFlagId());
					it.putExtra("descrId", getDescrId());
					startActivity(it);
				}

				@Override
				public void updateCurrentCountryDescription() {
					TextView description = (TextView) findViewById(R.id.country_descr);
					description
							.setMovementMethod(new ScrollingMovementMethod());
					description.setText(getResources().getString(getDescrId()));
				}

				@Override
				public void updateCurrentCountryFlag() {
					ImageView img = (ImageView) findViewById(R.id.country_flag);
					img.setImageResource(getFlagId());
				}
			});

			countryList.addView(child, countryList.getChildCount());
		}
	}

	private void addPolishContryNames() {
		LinearLayout countryList = (LinearLayout) findViewById(R.id.country_list);
		countryList.removeAllViews();
		int i = 0;
		final Intent it = new Intent(this, CountryDescriptionVertical.class);

		for (int c : PL_COUNTRIES) {
			Button child = new Button(this);
			child.setText(getResources().getString(c));
			child.setOnClickListener(new PlCountryButtonListener(i++,
					getResources().getConfiguration().orientation) {

				@Override
				public void onClickInHorizontal() {
					updateCurrentCountryFlag();
					updateCurrentCountryDescription();
				}

				@Override
				public void onClickInVertical() {
					it.putExtra("flagId", getFlagId());
					it.putExtra("descrId", getDescrId());
					startActivity(it);
				}

				@Override
				public void updateCurrentCountryDescription() {
					TextView description = (TextView) findViewById(R.id.country_descr);
					description
							.setMovementMethod(new ScrollingMovementMethod());
					description.setText(getResources().getString(getDescrId()));
				}

				@Override
				public void updateCurrentCountryFlag() {
					ImageView img = (ImageView) findViewById(R.id.country_flag);
					img.setImageResource(getFlagId());

				}
			});

			countryList.addView(child, countryList.getChildCount());
		}
	}
}
