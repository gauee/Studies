package pl.gauee.app.viewer.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	
	public static final String DBNAME = "appRates.db";
    public static final int DBVERSION = 1;
    public static final String TABLE_APP_RATE = "appRate";

    public DatabaseOpenHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("topics.database", "Creating new database...");
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE ").append(TABLE_APP_RATE).append(" (");
        sqlBuilder.append("_id INTEGER PRIMARY KEY, ");
        sqlBuilder.append("appPackage TEXT NOT NULL, ");
        sqlBuilder.append("rate REAL NOT NULL ");
        sqlBuilder.append(");");

        try {
            db.execSQL(sqlBuilder.toString());
        } catch (SQLException ex) {
            Log.e("topics.database", "Error creating application database.", ex);
        }
        Log.d("topics.database", "... database creation finished.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No update so far
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

}
