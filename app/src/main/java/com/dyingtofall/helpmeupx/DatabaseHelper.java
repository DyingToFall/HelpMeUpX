package com.dyingtofall.helpmeupx;

/**
 * Created by ptsushima on 9/9/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "HelpMeUp.db";
    public static final String Event_Record = "EventRecord";
    //Tables Named
    private static final String TABLE_EVENT = "event";
    private static final String TABLE_DEVICE_RELIABILITY = "deviceReliability";
    private static final String TABLE_EMERGENCY = "emergency";
    private static final String TABLE_FALL = "fall";
    private static final String TABLE_PANIC = "panic";
   //Event Columns
    public static final String EVENT_COLUMN_EVENT_ID = "eventID";
    public static final String EVENT_COLUMN_DATE = "date";
    public static final String EVENT_COLUMN_TIME = "time";
    public static final String EVENT_COLUMN_LOCATION = "location";
    //Device Columns
    public static final String DEVICE_RELIABILITY_COLUMN_ID = "DeviceReliabilityID";
    public static final String DEVICE_RELIABILITY_COLUMN_TYPE = "DeviceType";
    public static final String DEVICE_RELIABILITY_COLUMN_DURATION = "Duration";
    public static final String DEVICE_RELIABILITY_COLUMN_EVENT_ID = "eventID";
    //Emergency Columns
    public static final String EMERGENCY_COLUMN_ID = "EmergencyID";
    public static final String EMERGENCY_COLUMN_TIMEOUT ="TimeOut";
    public static final String EMERGENCY_COLUMN_NUMBER_OF_CONTACTS ="NumberOfContacts";
    public static final String EMERGENCY_COLUMN_MESSAGE_ATTEMPTS = "MessageAttempts";
    public static final String EMERGENCY_COLUMN_RESPONSE_TIME = "ResponseTime";
    public static final String EMERGENCY_COLUMN_EVENT_ID = "emergentyEventID";
    //Fall Columns
    public static final String FALL_COLUMN_EVENT_ID = "fallEventID";
    public static final String FALL_COLUMN_SEVERITY = "Severity";
    public static final String FALL_COLUMN_EMERGENCY_ID = "EmergencyID";
    //Panic Columns
    public static final String PANIC_COLUMN_EVENT_ID = "panicEventID";
    public static final String PANIC_COLUMN_ORGANIZATION = "Organization";
    public static final String PANIC_COLUMN_EMERGENCY_ID = "EmergencyID";

    String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT + "( " +
            EVENT_COLUMN_EVENT_ID + " INTEGER PRIMARY KEY, " +
            EVENT_COLUMN_DATE + " TEXT, " +
            EVENT_COLUMN_TIME + " TEXT, " +
            EVENT_COLUMN_LOCATION + " TEXT" +
            ");";

    String CREATE_DEVICE_RELIABILITY_TABLE = "CREATE TABLE " + TABLE_DEVICE_RELIABILITY + "( " +
            DEVICE_RELIABILITY_COLUMN_ID + " INTEGER PRIMARY KEY, " +
            DEVICE_RELIABILITY_COLUMN_TYPE + " TEXT, " +
            DEVICE_RELIABILITY_COLUMN_DURATION + " TEXT, " +
            DEVICE_RELIABILITY_COLUMN_EVENT_ID + " TEXT" +
            ");";

    String CREATE_EMERGENCY_TABLE = "CREATE TABLE " + TABLE_EMERGENCY + "( " +
            EMERGENCY_COLUMN_EVENT_ID + " INTEGER PRIMARY KEY, " +
            EMERGENCY_COLUMN_TIMEOUT + " TEXT, " +
            EMERGENCY_COLUMN_NUMBER_OF_CONTACTS + " TEXT, " +
            EMERGENCY_COLUMN_MESSAGE_ATTEMPTS + " TEXT, " +
            EMERGENCY_COLUMN_RESPONSE_TIME + " TEXT, " +
            EMERGENCY_COLUMN_ID + " TEXT" +
            ");";

    String CREATE_FALL_TABLE = "CREATE TABLE " + TABLE_FALL + "( " +
            FALL_COLUMN_EVENT_ID + " INTEGER PRIMARY KEY, " +
            FALL_COLUMN_SEVERITY + " TEXT," +
            FALL_COLUMN_EMERGENCY_ID + " TEXT" +
            ");";

    String CREATE_PANIC_TABLE = "CREATE TABLE " + TABLE_PANIC + "( " +
            PANIC_COLUMN_EVENT_ID + " INTEGER PRIMARY KEY, " +
            PANIC_COLUMN_ORGANIZATION + " TEXT, " +
            PANIC_COLUMN_EMERGENCY_ID + " TEXT" +
            ");";



    private HashMap hp;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        /*db.execSQL(
                "create table EventRecord " +
                        "(eventID integer primary key, date text, time text, location text)"
        );
        db.execSQL("create table DeviceReliability" + "(DeviceReliabilityID integer primary key, DeviceType text, Duration text, eventID integer)");
        db.execSQL("create table Emergency" + "(EmergencyID integer primary key, TimeOut text, NumberOfContacts text, MessageAttempts text, ResponseTime text, eventID integer )" );
        db.execSQL("create table Fall" + "(FallID integer primary key, Security text, EmergencyID integer)");
        db.execSQL("create table Panic" + "(PanicID integer primary key, Organization text, EmergencyID integer)");*/


        db.execSQL(CREATE_EVENT_TABLE);
        db.execSQL(CREATE_DEVICE_RELIABILITY_TABLE);
        db.execSQL(CREATE_EMERGENCY_TABLE);
        db.execSQL(CREATE_FALL_TABLE);
        db.execSQL(CREATE_PANIC_TABLE);
    }

    void addEvent()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_COLUMN_DATE, "trying to work");
        values.put(EVENT_COLUMN_TIME, "random time");
        values.put(EVENT_COLUMN_LOCATION, "who knows where");

        db.insert(TABLE_EVENT, null, values);
        db.close();;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertEventRecord(String date, String time, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("location", location);
        db.insert("EventRecord", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from EventRecord where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Event_Record);
        return numRows;
    }

    /*public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("EventRecord", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }*/

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("EventRecord",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }


    public ArrayList<String> getAllEvents()
    {
        addEvent();
        ArrayList<String> array_list = new ArrayList<String>();
        String selectQuery = "SELECT * FROM " + TABLE_EVENT;
        SQLiteDatabase db = this.getWritableDatabase();




        //hp = new HashMap();
        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(selectQuery, null );
        res.moveToFirst();

        while
                (res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(EVENT_COLUMN_DATE)));
            res.moveToNext();
        }
        return array_list;
    }



}