
package com.example.smartpantrymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class PantryDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pantry.db";
    private static final int DATABASE_VERSION = 1;

    public PantryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE ingredients (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "quantity INTEGER NOT NULL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        // Database upgrade code can be added later.
    }

    public boolean addIngredient(String name, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);

        long result = db.insert("ingredients", null, values);
        return result != -1;
    }

    public ArrayList<String> getAllIngredients() {
        ArrayList<String> ingredients = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT name, quantity FROM ingredients",
                null
        );

        try {
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                int quantity = cursor.getInt(1);

                ingredients.add(
                        name + " - Quantity: " + quantity
                );
            }
        } finally {
            cursor.close();
        }

        return ingredients;
    }

    public boolean updateIngredient(int id, String name, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("quantity", quantity);

        int result = db.update(
                "ingredients",
                values,
                "id = ?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }

    public boolean deleteIngredient(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                "ingredients",
                "id = ?",
                new String[]{String.valueOf(id)}
        );

        return result > 0;
    }
}
