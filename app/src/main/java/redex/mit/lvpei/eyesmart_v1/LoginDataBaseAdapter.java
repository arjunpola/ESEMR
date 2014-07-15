package redex.mit.lvpei.eyesmart_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by arjunpola on 15/07/14.
 */

//class Patient
//{
//    static final String First_Name = "FIRSTNAME";
//    static final String Last_Name = "LASTNAME";
//    static final String Gender = "GENDER";
//    static final String Age = "AGE";
//    static final String Date_Of_Birth = "DOB";
//    static final String Email_ID = "EMAILID";
//    static final String PhoneNumber = "PHONENUMBER";
//    static final String Country="COUNTRY";
//    static final String State="STATE";
//    static final String District="DISTRICT";
//
//    static final String Location="LOCATION";
//    static final String PinCode="PINCODE";
//
//    static final String Address="ADDRESS";
//
//    static final String ReferalName="REFERALNAME";
//
//    static final String RefAddress="REFERALADDRESS";
//
//    static final String VisionTechnician="VISIONTECHNICIAN";
//
//}


public class LoginDataBaseAdapter {

    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"EMAIL text primary key,"+ "PIN integer) ";



    static final String CREATE_PATIENT_TABLES ="create table "+"PATIENT"+
            "("+Patient.First_Name+" text ,"+
            Patient.Last_Name+" text ,"+
            Patient.Gender+" BOOLEAN ,"+
            Patient.Age+" integer,"+
            Patient.Date_Of_Birth+" date ,"+
            Patient.Email_ID+" text ,"+
            Patient.PhoneNumber+" integer ,"+
            Patient.Country+" text ,"+
            Patient.State+" text ,"+
            Patient.District +" text ,"+
            Patient.PinCode +" integer ,"+
            Patient.Address +" text ,"+
            Patient.ReferalName +" text ,"+
            Patient.RefAddress +" text ,"+
            Patient.VisionTechnician +" text ,"+")";



    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String email,int pin)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("EMAIL", email);
        newValues.put("PIN",pin);

        db.insert("LOGIN", null, newValues);
    }

    public void clearLogin()
    {
        db.execSQL("delete from login");
    }

    public int deleteEntry(String email)
    {
        String where="Email=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{email}) ;
        return numberOFEntriesDeleted;
    }

    public int insertPatientData()
    {

        return -1;
    }

//    public  void InsertPatientEntry(Patient p)
//    {
//        ContentValues newValues = new ContentValues();
//        newValues.put(Patient.First_Name, p.getfName());
//        newValues.put(Patient.Last_Name, p.getlName());
//        newValues.put(Patient.Date_Of_Birth, p.getfName());
//        newValues.put(Patient.Email_ID, p.getfName());
//        newValues.put(Patient.Location, p.getfName());
//        newValues.put(Patient.PhoneNumber, p.getfName());
//        newValues.put(Patient.Country, p.getfName());
//        newValues.put(Patient.State, p.getfName());
//        newValues.put(Patient.Location, p.getfName());
//
//        newValues.put(Patient.State, p.getfName());
//
//        newValues.put(Patient.State, p.getfName());
//
//
//
//        db.insert("LOGIN", null, newValues);
//
//    }

    public int getSingleEntry(String email)
    {
        Cursor cursor=db.query("LOGIN", null, " EMAIL=?", new String[]{email}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return -1;
        }
        cursor.moveToFirst();
        int pin= cursor.getInt(cursor.getColumnIndex("PIN"));
        cursor.close();
        return pin;
    }

//    public String getAllTags(String a) {
//
//        Cursor c = db.rawQuery("SELECT * FROM " + "LOGIN" + " where SECURITYHINT = '" +a + "'" , null);
//        String str = null;
//        if (c.moveToFirst()) {
//            do {
//                str = c.getString(c.getColumnIndex("PASSWORD"));
//            } while (c.moveToNext());
//        }
//        return str;
//    }

    public void updateEntry(String email,int pin)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("PIN", pin);

        String where="EMAIL = ?";
        db.update("LOGIN",updatedValues, where, new String[]{email});
    }


}