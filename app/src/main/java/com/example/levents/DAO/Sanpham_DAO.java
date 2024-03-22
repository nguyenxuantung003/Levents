package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Sanpham;

import java.util.ArrayList;
public class Sanpham_DAO {

    DBhelper dBhelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public ArrayList<Sanpham> getALlsanpham(){
        ArrayList<Sanpham> list = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM SANPHAM", null);
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Sanpham sanpham = new Sanpham();
                    sanpham.setMasanpham(cursor.getInt(0));
                    sanpham.setTensanpham(cursor.getString(1));
                    sanpham.setGia(cursor.getInt(2));
                    sanpham.setMaloaisanpham(cursor.getInt(3));
                    sanpham.setMota(cursor.getString(4));
                    sanpham.setAnhsanpham(cursor.getString(5));
                    sanpham.setSoluong(cursor.getInt(6));
                    list.add(sanpham);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e){
            Log.i(TAG, "Lỗi", e);
        }
        return list;
    }
}
