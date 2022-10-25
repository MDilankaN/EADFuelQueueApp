package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.models.User;


public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "fuelqueue";
    private static final int DB_VERSION = 1;

    //
    private static final String USER_TABLE = "usertable";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "username";
    private static final String PASSWORD_COL = "password";
    private static final String LANG = "lang";
    private static final String FUEL_TYPE_COL = "fuel";
    private static final String VEHICLE_TYPE_COL = "vehicletype";
    private static final String VEHICLE_NO_COL = "vehicleno";
    private static final String EMAIL_COL = "email";
    private static final String TYPE = "utype";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + USER_TABLE + " ("
                + ID_COL + " TEXT PRIMARY KEY,"
                + NAME_COL + " TEXT,"
                + PASSWORD_COL + " TEXT,"
                + LANG + " TEXT,"
                + FUEL_TYPE_COL + " TEXT,"
                + VEHICLE_TYPE_COL + " TEXT,"
                + VEHICLE_NO_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + TYPE + " TEXT)";
        db.execSQL(query);
    }

    public void addUserToDB(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID_COL, user.getId());
        values.put(NAME_COL, user.getUserName());
        values.put(PASSWORD_COL, user.getPassword());
        values.put(EMAIL_COL, user.getEmail());
        values.put(TYPE, user.getType());
        values.put(LANG, user.getLanguage());
        values.put(VEHICLE_TYPE_COL, user.getVehicleType());
        values.put(VEHICLE_NO_COL, user.getVehicleNo());
        values.put(FUEL_TYPE_COL, user.getFuelType());

        db.insert(USER_TABLE, null, values);
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }
}
