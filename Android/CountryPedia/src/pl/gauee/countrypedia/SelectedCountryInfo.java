package pl.gauee.countrypedia;

public class SelectedCountryInfo {

	private static final SelectedCountryInfo instance = new SelectedCountryInfo();
	private int selectedCountryId = -1;

	private SelectedCountryInfo() {
		// TODO Auto-generated constructor stub
	}

	public static SelectedCountryInfo getInstance() {
		return instance;
	}

	public int getSelectedCountryId() {
		return selectedCountryId;
	}

	public void setSelectedCountryId(int selectedCountryId) {
		this.selectedCountryId = selectedCountryId;
	}

}
