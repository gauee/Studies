package pl.gauee.android.acm.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

	
	public static final String DBNAME = "ac_manager.db";
    public static final int DBVERSION = 1;
    public static final String TABLE_NAME = "ac_stats";

    public static final String idColName = "_id";
    public static final String startTimeColNam = "startTime";
    public static final String endTimeColName = "endTime";
    
    public DatabaseOpenHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("topics.database", "Creating new database...");
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE ").append(TABLE_NAME).append(" (");
        sqlBuilder.append(idColName).append(" INTEGER PRIMARY KEY, ");
        sqlBuilder.append(startTimeColNam).append(" NUMERIC NOT NULL, ");
        sqlBuilder.append(endTimeColName).append(" NUMERIC NOT NULL ");
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
