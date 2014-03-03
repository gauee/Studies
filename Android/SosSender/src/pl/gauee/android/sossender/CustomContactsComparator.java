package pl.gauee.android.sossender;

import java.util.Comparator;

public class CustomContactsComparator implements Comparator<CustomContact>{

	@Override
	public int compare(CustomContact lhs, CustomContact rhs) {
		return lhs.getName().compareTo(rhs.getName());
	}
	
	

}
