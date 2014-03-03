package pl.gauee.countrypedia;

public abstract class PlCountryButtonListener extends CountryButtonListener {

	private static final int DESCRIPTION[] = new int[] { R.string.pl_descr_pl,
			R.string.pl_descr_ger, R.string.pl_descr_spa, R.string.pl_descr_uk,
			R.string.pl_descr_usa, R.string.pl_descr_rus, };

	public PlCountryButtonListener() {
		super(-1, -1);
	}

	public PlCountryButtonListener(int state, int orientation) {
		super(state, orientation);
		updateFlag();
		updateDescription();
	}

	@Override
	public int getDescrId() {
		return DESCRIPTION[getState()];
	}

}
