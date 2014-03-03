package pl.gauee.android.sossender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.provider.ContactsContract;
import android.util.Log;

public class PreferenceView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preference_view);

		MySettingsFragment.contacts = getContacts();

		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new MySettingsFragment())
				.commit();

	}

	private List<CustomContact> getContacts() {
		String phoneNumber = "";
		List<CustomContact> rslt = new ArrayList<CustomContact>();
		ContentResolver cr = getBaseContext().getContentResolver();

		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
				null, null, null);

		if (cur.getCount() > 0) {

			Log.i("AutocompleteContacts", "Reading   contacts........");

			int k = 0;
			String name = "";

			while (cur.moveToNext()) {

				String id = cur.getString(cur
						.getColumnIndex(ContactsContract.Contacts._ID));
				name = cur
						.getString(cur
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

				// Check contact have phone number
				if (Integer
						.parseInt(cur.getString(cur
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {

					// Create query to get phone number by contact id
					Cursor pCur = cr.query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = ?", new String[] { id }, null);
					int j = 0;

					while (pCur.moveToNext()) {
						// Sometimes get multiple data
						if (j == 0) {
							// Get Phone number
							phoneNumber = ""
									+ pCur.getString(pCur
											.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

							// Add contacts names to adapter
							CustomContact cc = new CustomContact(name,
									phoneNumber);
							rslt.add(cc);
							j++;
							k++;
						}
					}
					pCur.close();
				}

			}
		}
		cur.close();
		
		Collections.sort(rslt,new CustomContactsComparator());
		
		return rslt;
	}

	public static class MySettingsFragment extends PreferenceFragment {
		static protected List<CustomContact> contacts;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			addPreferencesFromResource(R.xml.pref_main);

			ListPreference callListPreference = (ListPreference) findPreference("callListPref");
			MultiSelectListPreference smsListPreference = (MultiSelectListPreference) findPreference("smsListPref");
			

			
			setCallList(callListPreference);
			setSmsList(smsListPreference);
		}

		private void setCallList(ListPreference callListPreference) {
			String[] labels;
			String[] values;
			String[] defaults;
			if (contacts != null && !contacts.isEmpty()) {
				labels = new String[contacts.size()];
				values = new String[contacts.size()];
				defaults = new String[contacts.size()];
				int i = 0;
				for (CustomContact cc : contacts) {
					labels[i] = cc.getName();
					values[i] = cc.getPhoneNum();
					defaults[i] = "";

					++i;
				}
			} else {

				labels = new String[]{"option1"};
				values = new String[]{"1"};
				defaults = new String[]{""};
			}

			callListPreference.setEntries(labels);
			callListPreference.setEntryValues(values);
			callListPreference.setDefaultValue(defaults);
		}

		private void setSmsList(MultiSelectListPreference listPreference) {
			listPreference.setTitle("Lista kontaktów");
			String[] labels;
			String[] values;
			String[] defaults;
			if (contacts != null && !contacts.isEmpty()) {
				labels = new String[contacts.size()];
				values = new String[contacts.size()];
				defaults = new String[contacts.size()];
				int i = 0;
				for (CustomContact cc : contacts) {
					labels[i] = cc.getName();
					values[i] = cc.getPhoneNum();
					defaults[i] = "";

					++i;
				}
			} else {

				labels = new String[]{"option1"};
				values = new String[]{"1"};
				defaults = new String[]{""};
			}

			listPreference.setEntries(labels);
			listPreference.setEntryValues(values);
			listPreference.setDefaultValue(defaults);
		}
	}

}
