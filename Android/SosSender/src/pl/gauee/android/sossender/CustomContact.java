package pl.gauee.android.sossender;

public class CustomContact {

	private final String name;
	private final String phoneNum;
	
	public CustomContact(String name, String phoneNum) {
		super();
		this.name = name;
		this.phoneNum = phoneNum;
	}

	@Override
	public String toString() {
		return "CustomContact [name=" + name + ", phoneNum=" + phoneNum + "]";
	}

	public String getName() {
		return name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

}
