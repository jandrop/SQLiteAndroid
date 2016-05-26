package es.atrapandocucarachas.sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import es.atrapandocucarachas.sqlite.model.Person;

/**
 * @author Alejandro Platas Mallo
 * @version X.XX
 * @since 26/5/16
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myData.db";
    private static final String DATABASE_TABLE = "person";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + DATABASE_TABLE +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "surname TEXT NOT NULL, " +
                    "age INTEGER, " +
                    "phone TEXT, " +
                    "address TEXT);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    public long insert(Person p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", p.getName());
        values.put("surname", p.getSurname());
        values.put("age", p.getAge());
        values.put("phone", p.getPhone());
        values.put("address", p.getAddress());

        return db.insert(DATABASE_TABLE, null, values);
    }

    public int update(long id, Person p) {
        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("name", p.getName());
        values.put("surname", p.getSurname());
        values.put("age", p.getAge());
        values.put("phone", p.getPhone());
        values.put("address", p.getAddress());

        // Which row to update, based on the ID
        String selection = "_id" + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};

        int count = db.update(
                DATABASE_TABLE,
                values,
                selection,
                selectionArgs);
        db.close();
        return count;
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();

        // Define 'where' part of query.
        String selection = "_id" + " LIKE ?";

        // Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(id)};

        // Issue SQL statement.
        db.delete(DATABASE_TABLE, selection, selectionArgs);

        db.close();
    }

    public ArrayList<Person> list() {
        ArrayList<Person> pList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                "_id",
                "name",
                "surname",
                "age",
                "phone",
                "address"
        };

        String sortOrder = "surname DESC";

        Cursor c = db.query(
                DATABASE_TABLE,  // The table to query
                projection,      // The columns to return
                null,            // The columns for the WHERE clause
                null,            // The values for the WHERE clause
                null,            // don't group the rows
                null,            // don't filter by row groups
                sortOrder        // The sort order
        );

        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndexOrThrow("_id"));
                String name = c.getString(c.getColumnIndexOrThrow("name"));
                String surname = c.getString(c.getColumnIndexOrThrow("surname"));
                int age = c.getInt(c.getColumnIndexOrThrow("age"));
                String phone = c.getString(c.getColumnIndexOrThrow("phone"));
                String address = c.getString(c.getColumnIndexOrThrow("address"));

                pList.add(new Person(id, name, surname, age, phone, address));
            } while (c.moveToNext());
        }
        c.close();
        return pList;
    }
}
