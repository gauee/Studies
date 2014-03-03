package pl.gauee.android.acm.db;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseAccess {
	private SQLiteDatabase db;

	public DatabaseAccess(SQLiteDatabase db) {
		this.db = db;
	}

	public void dispose() {
		if (db != null && db.isOpen()) {
			db.close();
		}
		db = null;
	}

	public void insert(CustomChargingInfo cci) {
		validate();
		ContentValues v = new ContentValues();
		v.put(DatabaseOpenHelper.startTimeColNam, cci.getStartCharging());
		v.put(DatabaseOpenHelper.endTimeColName, cci.getStopCharging());

		long id = db.insert(DatabaseOpenHelper.TABLE_NAME, null, v);
		if (id >= 0) {
			cci.setId(id);
		}
	}

	public boolean update(CustomChargingInfo cci) {
		validate();
		ContentValues v = new ContentValues();
		Log.d("gauee", "save:" + cci.toString());
		v.put(DatabaseOpenHelper.startTimeColNam, cci.getStartCharging());
		v.put(DatabaseOpenHelper.endTimeColName, cci.getStopCharging());

		int rowsAffected = db.update(DatabaseOpenHelper.TABLE_NAME, v,
				DatabaseOpenHelper.idColName + "=" + cci.getId(), null);

		return (rowsAffected == 1);
	}

	public boolean delete(CustomChargingInfo cci) {
		return delete(cci.getId());
	}

	public boolean delete(long id) {
		validate();
		return (1 == db.delete(DatabaseOpenHelper.TABLE_NAME,
				DatabaseOpenHelper.idColName + "=" + id, null));
	}

	public CustomChargingInfo getById(long id) {
		validate();
		Cursor cur = null;
		try {
			cur = db.query(true, DatabaseOpenHelper.TABLE_NAME, null /* all */,
					"_id=" + id, null, null, null, null, null);
			List<CustomChargingInfo> tmpList = new LinkedList<CustomChargingInfo>();
			extractMoviesFromCursor(tmpList, cur);
			if (tmpList != null && !tmpList.isEmpty()) {
				return tmpList.get(0);
			} else {
				return null;
			}
		} catch (SQLException e) {
			Log.e("topics.database", "Error searching application database.", e);
			return null;
		} finally {
			if (cur != null && !cur.isClosed()) {
				cur.close();
			}
		}
	}

	public List<CustomChargingInfo> listAll() {
		validate();
		List<CustomChargingInfo> result = new LinkedList<CustomChargingInfo>();
		Cursor cur = null;
		try {
			cur = db.query(true, DatabaseOpenHelper.TABLE_NAME, null /* all */,
					null, null, null, null, DatabaseOpenHelper.startTimeColNam
							+ " DESC", null);
			extractMoviesFromCursor(result, cur);
		} catch (SQLException e) {
			Log.e("topics.database", "Error searching application database.", e);
		} finally {
			if (cur != null && !cur.isClosed()) {
				cur.close();
			}
		}
		Log.d("guae", "in db was : " + result.size() + " elements.");
		return Collections.unmodifiableList(result);
	}

	public Cursor getCursorForAllMovies() {
		validate();
		Cursor cur = null;
		try {
			cur = db.query(true, DatabaseOpenHelper.TABLE_NAME, null /* all */,
					null, null, null, null, DatabaseOpenHelper.startTimeColNam,
					null);
		} catch (SQLException e) {
			Log.e("topics.database", "Error searching application database.", e);
			cur = null;
		}
		return cur;
	}

	private void extractMoviesFromCursor(List<CustomChargingInfo> list,
			Cursor cur) {
		if (cur.moveToFirst()) {
			for (int i = 0; i < cur.getCount(); i++) {
				CustomChargingInfo cci = new CustomChargingInfo();
				cci.setId(cur.getLong(0));
				cci.setStartCharging(cur.getLong(1));
				cci.setStopCharging(cur.getLong(2));
				list.add(cci);

				Log.d("gauee", "get" + cci.toString());
				cur.moveToNext();
			}
		}
	}

	public void deleteAll() {
		db.delete(DatabaseOpenHelper.TABLE_NAME, null, null);
	}

	private void validate() {
		if (db == null) {
			throw new IllegalStateException(
					"Illegal access to the disposed MovieDbHelper object.");
		}
	}
}
