package pl.gauee.android.acm.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomChargingInfo {

	private long id;
	private long startCharging;
	private long stopCharging;
	private DateFormat dayFormat = new SimpleDateFormat("dd.MM");
	private DateFormat hourFormate = new SimpleDateFormat("HH:mm:ss");
	private static final int ms = 1000;
	private static final int min = 60;
	private static final int hour = 60 * min;

	public CustomChargingInfo() {
		// TODO Auto-generated constructor stub
	}

	public CustomChargingInfo(long startCharging, long stopCharging) {
		super();
		this.startCharging = startCharging;
		this.stopCharging = stopCharging;
	}

	public long getId() {
		return id;
	}

	public long getStartCharging() {
		return startCharging;
	}

	public long getStopCharging() {
		return stopCharging;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStartCharging(long startCharging) {
		this.startCharging = startCharging;
	}

	public void setStopCharging(long stopCharging) {
		this.stopCharging = stopCharging;
	}

	@Override
	public String toString() {
		return "CustomChargingInfo [id=" + id + ", startCharging="
				+ startCharging + ", stopCharging=" + stopCharging + "]";
	}

	public CharSequence getStartInHours() {
		return hourFormate.format(new Date(startCharging));
	}

	public CharSequence getEndInHours() {
		return hourFormate.format(new Date(startCharging));
	}

	public CharSequence getStartDay() {
		return dayFormat.format(new Date(startCharging));
	}

	public static CharSequence getDuration(long duration) {
		// int duration = (int) (stopCharging-startCharging);
		duration /= ms;
		StringBuilder sb = new StringBuilder();
		sb.append(duration / hour).append(" h ");
		duration %= hour;
		return sb.append(duration / min).append(" min ").append(duration % min)
				.append(" secs.").toString();
	}

}
