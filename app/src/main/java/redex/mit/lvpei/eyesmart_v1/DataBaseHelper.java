package redex.mit.lvpei.eyesmart_v1;

/**
 * Created by arjunpola on 15/07/14.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
    public DataBaseHelper(Context context, String name,CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE);
        _db.execSQL(LoginDataBaseAdapter.CREATE_PATIENT_TABLES);
//        _db.execSQL(LoginDataBaseAdapter.CREATE_STATES_TABLE);
//        _db.execSQL(LoginDataBaseAdapter.CREATE_DIST_TABLE);
//        _db.execSQL(LoginDataBaseAdapter.POPULATE_STATES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
// Log the version upgrade.
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        onCreate(_db);
    }
}