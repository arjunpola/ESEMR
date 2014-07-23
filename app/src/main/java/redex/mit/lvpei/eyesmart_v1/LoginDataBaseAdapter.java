package redex.mit.lvpei.eyesmart_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
            Patient.CheckinTime +" text ,"+
            Patient.VisionTechnician +" text)";

    static  final String CREATE_STATES_TABLE = "CREATE TABLE IF NOT EXISTS `tbl_states` (\n" +
            "  `tstate_id` INTEGER AUTOINCREMENT,\n" +
            "  `tstate_country_id` INTEGER NOT NULL,\n" +
            "  `tstate_name` varchar(150) NOT NULL,\n" +
            "  PRIMARY KEY  (`tstate_id`),\n" +
            ")";

    static final String CREATE_DIST_TABLE = "CREATE TABLE IF NOT EXISTS `tbl_districts` (\n" +
            "  `tdis_id` INTEGER AUTOINCREMENT,\n" +
            "  `tdis_state_id` INTEGER NOT NULL,\n" +
            "  `tdis_name` varchar(150) NOT NULL,\n" +
            "  PRIMARY KEY  (`tdis_id`),\n" +
            "  KEY `tdis_state_id` (`tdis_state_id`)\n" +
            ")";

    static final String POPULATE_STATES = "INSERT INTO `tbl_states` (`tstate_id`, `tstate_country_id`, `tstate_name`) VALUES\n" +
            "(1, 101, 'Andhra Pradesh'),\n" +
            "(2, 101, 'Delhi'),\n" +
            "(3, 101, 'West Bengal'),\n" +
            "(4, 101, 'Orissa'),\n" +
            "(5, 101, 'Maharashtra'),\n" +
            "(6, 101, 'Mizoram'),\n" +
            "(7, 101, 'Manipur'),\n" +
            "(8, 101, 'Meghalaya'),\n" +
            "(9, 101, 'Nagaland'),\n" +
            "(10, 101, 'Tripura'),\n" +
            "(11, 101, 'Sikkim'),\n" +
            "(12, 101, 'Pondicherry'),\n" +
            "(13, 101, 'Goa'),\n" +
            "(14, 101, 'Chhattisgarh'),\n" +
            "(15, 101, 'Kerala'),\n" +
            "(16, 101, 'Daman & Diu'),\n" +
            "(17, 101, 'Arunachal Pradesh'),\n" +
            "(18, 101, 'Bihar'),\n" +
            "(19, 101, 'Haryana'),\n" +
            "(20, 101, 'Jammu and Kashmir'),\n" +
            "(21, 101, 'Karnataka'),\n" +
            "(22, 101, 'Rajasthan'),\n" +
            "(23, 101, 'Tamil Nadu'),\n" +
            "(24, 101, 'Assam'),\n" +
            "(25, 101, 'Uttarakhand'),\n" +
            "(26, 101, 'Madhya Pradesh'),\n" +
            "(27, 101, 'Uttar Pradesh'),\n" +
            "(28, 101, 'Gujarat'),\n" +
            "(29, 101, 'Himachal Pradesh'),\n" +
            "(30, 101, 'Jharkand'),\n" +
            "(31, 101, 'Punjab'),\n" +
            "(32, 101, 'Telangana');";


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

    public void getStates()
    {
        Cursor cursor = db.query("tbl_states",null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            Log.v("State",cursor.getString(cursor.getColumnIndex("tstate_name")));
        }
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

    public  long InsertPatientEntry(Patient p)
    {
        ContentValues newValues = new ContentValues();
        newValues.put(Patient.First_Name, p.getfName());
        newValues.put(Patient.Last_Name, p.getlName());
        newValues.put(Patient.Date_Of_Birth, p.getDob());
        newValues.put(Patient.VisionTechnician, p.getVisionTech());
        newValues.put(Patient.CheckinTime, p.getCheckinTime());


        Log.v("Stored",p.getfName());

        return   db.insert("PATIENT", null, newValues);

    }

    public List<Patient> GetAllPatient()
    {

        String selectQuery="select * from "+"PATIENT";
        Cursor cursor = db.rawQuery(selectQuery, null);
        String[] data = null;
        List<Patient> Patients = new ArrayList<Patient>();
        int i=0;
        if (cursor.moveToFirst())
        {
            Log.v("asdf",cursor.getString(cursor.getColumnIndex(Patient.First_Name)));
         do {
              Patient p = new Patient();

              p.setfName(cursor.getString(cursor.getColumnIndex(Patient.First_Name)));
              p.setlName(cursor.getString(cursor.getColumnIndex(Patient.Last_Name)));
              p.setDob(cursor.getString(cursor.getColumnIndex(Patient.Date_Of_Birth)));
              p.setVisionTech(cursor.getString(cursor.getColumnIndex(Patient.VisionTechnician)));
              p.setGender(cursor.getString(cursor.getColumnIndex(Patient.Gender)));
              Patients.add(p);
             cursor.moveToNext();
          }while (!cursor.isLast());
            return  Patients;
                    
        }
        else
        {

            return  null;
        }

    }
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