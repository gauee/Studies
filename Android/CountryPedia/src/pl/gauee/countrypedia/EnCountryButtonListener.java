package pl.gauee.countrypedia;

public abstract class EnCountryButtonListener extends CountryButtonListener {

	private static final int DESCRIPTION[] = new int[] { R.string.en_descr_pl,
			R.string.en_descr_ger, R.string.en_descr_spa, R.string.en_descr_uk,
			R.string.en_descr_usa, R.string.en_descr_rus, };

	public EnCountryButtonListener() {
		super(-1, -1);
	}

	public EnCountryButtonListener(int state, int orientation) {
		super(state, orientation);
		updateFlag();
		updateDescription();
	}

	@Override
	public int getDescrId() {
		return DESCRIPTION[getState()];
	}
}
