package com.example.keputrian.helper;

import static java.sql.Types.INTEGER;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    static final String DATABASE_NAME = "keputrian";

    //table format mens//
    private static final String format_mens = "formatMens";
    private static final String id = "id";
    private static final String tanggal_mulai = "tanggalMulai";
    private static final String tanggal_selesai = "tanggalSelesai";
    private static final String tanggal_mandi = "tanggalMandi";
    private static final String jam_mandi = "jamMandi";

    //tabel qodlo//

    private static final String qodlo_puasa = "qodloPuasa";
    private static final String id_qodlo = "id";
    private static final String tanggal_qodlo = "tanggalQodlo";
    private static final String is_checked = "is_checked";

    public Helper(Context context){
        super( context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //tabel format mens//
        final String SQL_CREATE_TABLE = "CREATE TABLE format_mens (id INTEGER PRIMARY KEY autoincrement, tanggal_mulai DATE NOT NULL, tanggal_selesai DATE, tanggal_mandi DATE, jam_mandi TIME)";
        db.execSQL(SQL_CREATE_TABLE);

        //tabel format qodlo//
//        String createTableQodloQuery = "CREATE TABLE" + qodlo_puasa + "(" + id_qodlo + INTEGER PRIMARY KEY autoincrement;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS format_mens");
        onCreate(db);

    }

    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM format_mens";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("tanggal_mulai", cursor.getString(1));
                map.put("tanggal_selesai", cursor.getString(2));
                map.put("tanggal_mandi", cursor.getString(3));
                map.put("jam_mandi", cursor.getString(4));
                list.add(map);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert(String tanggal_mulai, String tanggal_selesai, String tanggal_mandi, String jam_mandi){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO format_mens (tanggal_mulai,tanggal_selesai,tanggal_mandi,jam_mandi) VALUES('"+tanggal_mulai+"', '"+tanggal_selesai+"', '"+tanggal_mandi+"', '"+jam_mandi+"')";
        database.execSQL(QUERY);
    }

    public void update(int id, String tanggal_mulai, String tanggal_selesai, String tanggal_mandi, String jam_mandi){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE format_mens SET tanggal_mulai = '"+tanggal_mulai+"', tanggal_selesai = '"+tanggal_selesai+"', tanggal_mandi = '"+tanggal_mandi+"', jam_mandi = '"+jam_mandi+"' WHERE id = "+id;
        database.execSQL(QUERY);
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM format_mens WHERE id="+id;
        database.execSQL(QUERY);
    }
}
