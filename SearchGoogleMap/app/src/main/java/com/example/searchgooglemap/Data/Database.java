package com.example.searchgooglemap.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.sql.SQLClientInfoException;

public class Database extends SQLiteOpenHelper {

    /**
     *
     * Attributes
     *
     */

    public final String DBName = "SearchMaps.db";

    /**
     *
     * getting database information
     *
     * @param context gets the context
     */
    public Database(Context context) {
        super(context, "SearchMaps.db", null, 1);
    }

    /**
     *
     * Creates a users table to store or retrieve data
     *
     *
     * @param Database to access database
     */
    @Override
    public void onCreate(SQLiteDatabase Database) {
        Database.execSQL("CREATE Table users(username TEXT primary key, password TEXT)");
    }

    /**
     *
     * Checks if the users table exists
     *
     * @param Database to access database
     * @param oldVersion checking versions
     * @param newVersion checking versions
     */
    @Override
    public void onUpgrade(SQLiteDatabase Database, int oldVersion, int newVersion) {
        Database.execSQL("DROP Table if exists users");
    }

    /**
     *
     * Checking the login for the user
     *
     *
     * @param username to get input username
     * @param password to get input password
     * @return true or false
     */
    public Boolean Login(String username, String password) {
        SQLiteDatabase Database = this.getWritableDatabase();
        Cursor SQL = Database.rawQuery("SELECT * " +
                "FROM users " +
                "WHERE username = ? AND password = ?",
                // checking fields here
                new String[]{username,password});

        // checking credentials
        if (SQL.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     *
     * Registering a new user in the system
     *
     * @param username to get username input
     * @param password to get password
     * @param password2 to get repeat password
     * @return true or false
     */
    public Boolean Register(String username, String password, String password2) {
        SQLiteDatabase Database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", username);
        Long res = Database.insert("users", null, values);

        // checking registration
        if (res == 1) {
            return false;
        } else {
            return true;
        }
    }
}
