package com.cuantocuesta.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

  // Database Version
  private static final int DATABASE_VERSION = 1;

  // Database Name
  private static final String DATABASE_NAME = "Listings";

  // Contacts table name
  private static final String TABLE_LIKED_ITEMS = "LikedItems";

  // Contacts Table Columns names
  private static final String KEY_ID = "id";
  private static final String ITEM_ID = "item_meli_id";

  public DatabaseHandler(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_LIKED_ITEMS + "("
      + KEY_ID + " INTEGER PRIMARY KEY," + ITEM_ID + " TEXT)";
    db.execSQL(CREATE_CONTACTS_TABLE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIKED_ITEMS);
    onCreate(db);
  }

  public void addLikedItem(String likedItemID) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(ITEM_ID,likedItemID);

    db.insert(TABLE_LIKED_ITEMS, null, values);
    db.close();
  }

  public List<String> getAllLikedItems() {
    List<String> likedItemsList = new ArrayList<String>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + TABLE_LIKED_ITEMS;

    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    if (cursor.moveToFirst()) {
      do {
        likedItemsList.add(cursor.getString(1));
      } while (cursor.moveToNext());
    }

    return likedItemsList;
  }

  public int getLikedItemsCount() {
    String countQuery = "SELECT  * FROM " + TABLE_LIKED_ITEMS;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);
    cursor.close();
    return cursor.getCount();
  }

  public void deleteLikedItem(String itemMeliID) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_LIKED_ITEMS, ITEM_ID + " = ?",
      new String[] { itemMeliID });
    db.close();
  }

  public void deleteAllLikedItems(String itemMeliID) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_LIKED_ITEMS, null,null);
    db.close();
  }
}