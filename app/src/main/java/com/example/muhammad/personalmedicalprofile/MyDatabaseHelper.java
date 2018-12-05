package com.example.muhammad.personalmedicalprofile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    //First Table
    private static final String DB_NAME = "doctor.db";
    private static final int DB_VERSION = 4;
    private static final String TABLE_NAME = "doctor_details";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "Name";
    public static final String KEY_DETAILS = "details";
    public static final String KEY_APPOINTMENT = "appoinment";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";

    //Second Table
    private static final String SECOND_TABLE = "medical_details";
    private static final String ID = "_id";
    private static final String IMAGE = "image";
    private static final String DOCTOR_NAME = "Doctor";
    private static final String DOCTOR_DETAILS = "Details";
    private static final String DATE = "Date";



    private Context context;

    //Create First Table
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("
            +KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_NAME+" VARCHAR(30), "
            +KEY_DETAILS+" VARCHAR(30), "+KEY_APPOINTMENT+" VARCHAR(40), "+KEY_EMAIL+" VARCHAR(30),"
            +KEY_PHONE+" INTEGER);";

    //Create Second Table
    private static final String CREATE_TABLE_MEDICAL = "CREATE TABLE "+SECOND_TABLE+" ("
            +ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DOCTOR_NAME+" VARCHAR(30), "
            +DOCTOR_DETAILS+" VARCHAR(30), "
            +DATE+" VARCHAR(30));";

    //Drop Table
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    //Read Data
    private static final String READ_DATA = "SELECT * FROM "+TABLE_NAME;
    private static final String READ_MEDICAL_DATA = "SELECT * FROM "+SECOND_TABLE;

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    //Select Doctor Name
    private static final String DOC_NAME = "SELECT Name FROM "+TABLE_NAME;

    //Select Doctor Details
    private static final String DOC_DETAILS = "SELECT details FROM "+TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        
        try{
            Toast.makeText(context, "onCreate Method is Called", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
            sqLiteDatabase.execSQL(CREATE_TABLE_MEDICAL);
        }catch (Exception e){
            Toast.makeText(context, "Error : "+e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Toast.makeText(context, "onUpgrade Method is Called", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }catch (Exception e){
            Toast.makeText(context, "Error : "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(String name, String details, String appointment, String email, String phone){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_DETAILS, details);
        contentValues.put(KEY_APPOINTMENT, appointment);
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY_PHONE, phone);
        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }

    public Cursor showAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(READ_DATA, null);
        return cursor;
    }

    // Show doctor name
    public Cursor showDocName(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor docName = sqLiteDatabase.rawQuery(DOC_NAME, null);
        return docName;
    }
    //Show doctor details
    public Cursor showDocDetails(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor docName = sqLiteDatabase.rawQuery(DOC_DETAILS, null);
        return docName;
    }


    public Cursor showAllMedicalData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(READ_MEDICAL_DATA, null);
        return cursor;
    }

    public Boolean updateData(String id, String name, String details, String appointment, String email, String phone){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, id);
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_DETAILS, details);
        contentValues.put(KEY_APPOINTMENT, appointment);
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY_PHONE, phone);
        sqLiteDatabase.update(TABLE_NAME, contentValues, KEY_ID+" = ?", new String[] {String.valueOf(id)});
        return true;
    }









    public long addData(String name, String details, String date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DOCTOR_NAME, name);
        contentValues.put(DOCTOR_DETAILS, details);
        contentValues.put(DATE, date);
        long rowId = sqLiteDatabase.insert(SECOND_TABLE,null,contentValues);
        return rowId;
    }


}
