package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
    @SuppressLint("Range")
    public ArrayList<Sanpham> getAllSanpham() {
        ArrayList<Sanpham> list = new ArrayList<>();
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM SANPHAM", null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Sanpham sanpham = new Sanpham();
                    sanpham.setMasanpham(cursor.getInt(cursor.getColumnIndex("masanpham")));
                    sanpham.setTensanpham(cursor.getString(cursor.getColumnIndex("tensanpham")));
                    sanpham.setGia(cursor.getInt(cursor.getColumnIndex("gia")));
                    sanpham.setMaloaisanpham(cursor.getInt(cursor.getColumnIndex("maloaisanpham")));
                    sanpham.setMota(cursor.getString(cursor.getColumnIndex("mota")));
                    sanpham.setAnhsanpham(cursor.getString(cursor.getColumnIndex("anhsanpham")));
                    sanpham.setSoluong(cursor.getInt(cursor.getColumnIndex("soluong")));
                    list.add(sanpham);
                } while (cursor.moveToNext());
            }
            if (cursor != null) {
                cursor.close(); // Đóng con trỏ sau khi sử dụng xong
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy dữ liệu sản phẩm từ cơ sở dữ liệu", e);
        }
        return list;
    }
    @SuppressLint("Range")
    public Sanpham getSanphamById(int masanpham) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Sanpham sanpham = null;
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM SANPHAM WHERE masanpham = ?", new String[]{String.valueOf(masanpham)});
            if (cursor != null && cursor.moveToFirst()) {
                sanpham = new Sanpham();
                sanpham.setMasanpham(cursor.getInt(cursor.getColumnIndex("masanpham")));
                sanpham.setTensanpham(cursor.getString(cursor.getColumnIndex("tensanpham")));
                sanpham.setGia(cursor.getInt(cursor.getColumnIndex("gia")));
                sanpham.setMaloaisanpham(cursor.getInt(cursor.getColumnIndex("maloaisanpham")));
                sanpham.setMota(cursor.getString(cursor.getColumnIndex("mota")));
                sanpham.setAnhsanpham(cursor.getString(cursor.getColumnIndex("anhsanpham")));
                sanpham.setSoluong(cursor.getInt(cursor.getColumnIndex("soluong")));
            }
            if (cursor != null) {
                cursor.close(); // Đóng con trỏ sau khi sử dụng xong
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy dữ liệu sản phẩm từ cơ sở dữ liệu", e);
        }
        return sanpham;
    }
    public boolean updateSanpham(Sanpham sanpham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("tensanpham", sanpham.getTensanpham());
            values.put("gia", sanpham.getGia());
            values.put("maloaisanpham", sanpham.getMaloaisanpham());
            values.put("mota", sanpham.getMota());
            values.put("anhsanpham", sanpham.getAnhsanpham());
            values.put("soluong", sanpham.getSoluong());

            int rowsAffected = db.update("SANPHAM", values, "masanpham = ?", new String[]{String.valueOf(sanpham.getMasanpham())});
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi cập nhật sản phẩm trong cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
    public boolean deleteSanpham(int masanpham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            int rowsAffected = db.delete("SANPHAM", "masanpham = ?", new String[]{String.valueOf(masanpham)});
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi xóa sản phẩm khỏi cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }
    public boolean insertSanpham(Sanpham sanpham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("tensanpham", sanpham.getTensanpham());
            values.put("gia", sanpham.getGia());
            values.put("maloaisanpham", sanpham.getMaloaisanpham());
            values.put("mota", sanpham.getMota());
            values.put("anhsanpham", sanpham.getAnhsanpham());
            values.put("soluong", sanpham.getSoluong());

            long newRowId = db.insert("SANPHAM", null, values);
            success = newRowId != -1;
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi chèn sản phẩm vào cơ sở dữ liệu", e);
        } finally {
            db.close();
        }
        return success;
    }

}
