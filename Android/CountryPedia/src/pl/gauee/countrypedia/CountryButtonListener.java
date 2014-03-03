package pl.gauee.countrypedia;

import android.content.res.Configuration;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class CountryButtonListener implements OnClickListener {

	private static final int[] COUNTRY_FLAGS = new int[] { R.drawable.pl,
			R.drawable.ger, R.drawable.spa, R.drawable.uk, R.drawable.usa,
			R.drawable.rus };

	private final int state;
	private final int orientation;

	public CountryButtonListener(int state, int orientation) {
		this.state = state;
		this.orientation = orientation;
	}

	public int getState() {
		return state;
	}

	public int getFlagId() {
		return COUNTRY_FLAGS[state];
	}

	public void updateDescription() {
		if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
			return;
		}

		if (getState() == SelectedCountryInfo.getInstance()
				.getSelectedCountryId()) {
			updateCurrentCountryDescription();
		}
	}

	public void updateFlag() {
		if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
			return;
		}
		if (getState() == SelectedCountryInfo.getInstance()
				.getSelectedCountryId()) {
			updateCurrentCountryFlag();
		}
	}

	@Override
	public void onClick(View v) {
		SelectedCountryInfo.getInstance().setSelectedCountryId(getState());
		if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
			onClickInHorizontal();
		} else {
			onClickInVertical();
		}
	}

	public abstract int getDescrId();

	public abstract void updateCurrentCountryDescription();

	public abstract void updateCurrentCountryFlag();

	public abstract void onClickInVertical();

	public abstract void onClickInHorizontal();

}
