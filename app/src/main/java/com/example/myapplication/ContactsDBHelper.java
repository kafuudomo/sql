package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ContactsDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="MyDatabase.db";

    public ContactsDBHelper(Context context){

        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE " + Contact.TABLE_NAME + "(" + Contact.COLUMN_ID + " INTERGER PRIMARY KEY, " + Contact.COLUMN_NAME
        +" TEXT, "+ Contact.COLUMN_PHONE_NUMBER + " TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS "+ Contact.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertContact(Contact contact){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Contact.COLUMN_NAME, contact.getName());
        values.put(Contact.COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
        db.insert(Contact.TABLE_NAME, null, values);
        return true;
    }

    public void updateContact(Contact contact){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Contact.COLUMN_NAME, contact.getName());
        values.put(Contact.COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
        db.update(Contact.TABLE_NAME, values, Contact.COLUMN_ID + " = ?",
                new String[]{Integer.toString(contact.getId())});


    }

    public void deleteContact(Integer id){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(Contact.TABLE_NAME, Contact.COLUMN_ID + " = ?",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<Contact> getAllContacts(){

        ArrayList<Contact> contactArrayList=new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM "+Contact.TABLE_NAME, null);
        res.moveToFirst();

        while(!res.isAfterLast()){

            Contact contact=new Contact();
            contact.setId(res.getInt(res.getColumnIndex(Contact.COLUMN_ID)));
            contact.setName(res.getString(res.getColumnIndex(Contact.COLUMN_NAME)));
            contact.setPhoneNumber(res.getString(res.getColumnIndex(Contact.COLUMN_PHONE_NUMBER)));
            contactArrayList.add(contact);
            res.moveToNext();
        }
        res.close();
        return contactArrayList;

    }
}
