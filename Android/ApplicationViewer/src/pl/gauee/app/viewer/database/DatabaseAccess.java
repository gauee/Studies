package pl.gauee.app.viewer.database;

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

	public void insert(CustomApplicationInfo cai) {
		validate();
		ContentValues v = new ContentValues();
		v.put("appPackage", cai.getAppPackage());
		v.put("rate", cai.getRate());
		
		long id = db.insert(DatabaseOpenHelper.TABLE_APP_RATE, null, v);
		if (id >= 0) {
			cai.setId(id);
		}
	}

	public boolean update(CustomApplicationInfo cai) {
		validate();
		ContentValues v = new ContentValues();
		v.put("appPackage", cai.getAppPackage());
		v.put("rate", cai.getRate());
		Log.d("gauee","Save entity to db: "+ cai); 
		int rowsAffected = db.update(DatabaseOpenHelper.TABLE_APP_RATE, v, "_id="
				+ cai.getId(), null);

		return (rowsAffected == 1);
	}

	public boolean delete(CustomApplicationInfo cai) {
		return delete(cai.getId());
	}

	public boolean delete(long id) {
		validate();
		return (1 == db
				.delete(DatabaseOpenHelper.TABLE_APP_RATE, "_id=" + id, null));
	}

	public CustomApplicationInfo getById(long id) {
		validate();
		Cursor cur = null;
		try {
			cur = db.query(true, DatabaseOpenHelper.TABLE_APP_RATE, null /* all */,
					"_id=" + id, null, null, null, null, null);
			List<CustomApplicationInfo> tmpList = new LinkedList<CustomApplicationInfo>();
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

	public List<CustomApplicationInfo> listAll() {
		validate();
		List<CustomApplicationInfo> result = new LinkedList<CustomApplicationInfo>();
		Cursor cur = null;
		try {
			cur = db.query(true, DatabaseOpenHelper.TABLE_APP_RATE, null /* all */,
					null, null, null, null, "appPackage", null);
			extractMoviesFromCursor(result, cur);
		} catch (SQLException e) {
			Log.e("topics.database", "Error searching application database.", e);
		} finally {
			if (cur != null && !cur.isClosed()) {
				cur.close();
			}
		}
		Log.d("guae","in db was : " + result.size() + " elements.");
		return Collections.unmodifiableList(result);
	}

	public Cursor getCursorForAllMovies() {
		validate();
		Cursor cur = null;
		try {
			cur = db.query(true, DatabaseOpenHelper.TABLE_APP_RATE,null /* all */,
					null, null, null, null, "appPackage", null);
		} catch (SQLException e) {
			Log.e("topics.database", "Error searching application database.", e);
			cur = null;
		}
		return cur;
	}

	private void extractMoviesFromCursor(List<CustomApplicationInfo> list, Cursor cur) {
		if (cur.moveToFirst()) {
			for (int i = 0; i < cur.getCount(); i++) {
				CustomApplicationInfo cai = new CustomApplicationInfo();
				cai.setId(cur.getLong(0));
				cai.setAppPackage(cur.getString(1));
				cai.setRate(cur.getFloat(2));
				list.add(cai);

				cur.moveToNext();
			}
		}
	}

	private void validate() {
		if (db == null) {
			throw new IllegalStateException(
					"Illegal access to the disposed MovieDbHelper object.");
		}
	}
}
