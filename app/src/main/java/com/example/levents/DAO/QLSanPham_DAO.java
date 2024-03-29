package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Giohang;
import com.example.levents.Model.QLsanpham;

import java.util.ArrayList;

public class QLSanPham_DAO {
    DBhelper dbs;
    public QLSanPham_DAO(Context context) {
        dbs = new DBhelper(context);
    }


    public ArrayList<QLsanpham> getsanphamall() {
        ArrayList<QLsanpham> list = new ArrayList();
        SQLiteDatabase database = dbs.getReadableDatabase();
        Cursor cursor = database.rawQuery("select sp.masanpham, sp.tensanpham, sp.gia, lsp.maloaisanpham,lsp.tenloaisanpham,sp.mota,sp.anhsanpham,sp.soluong, sp.soluongbanra from SANPHAM sp, LOAISANPHAM lsp where sp.maloaisanpham = lsp.maLoaisanpham", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new QLsanpham(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7),cursor.getInt(8)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public boolean insert(String tensanpham, int gia, int maloaisanpham, String mota, String anhsanpham, int soluong) {
        SQLiteDatabase db = dbs.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensanpham", tensanpham);
        values.put("gia", gia);
        values.put("maloaisanpham", maloaisanpham);
        values.put("mota", mota);
        values.put("anhsanpham", anhsanpham);
        values.put("soluong", soluong);
        values.put("soluongbanra",0);
        long check = db.insert("SANPHAM", null, values);
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean update(int masanpham, String tensanpham, int gia, int maloaisanpham, String mota, String anhsanpham, int soluong) {
        SQLiteDatabase db = dbs.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensanpham", tensanpham);
        values.put("gia", gia);
        values.put("maloaisanpham", maloaisanpham);
        values.put("mota", mota);
        values.put("anhsanpham", anhsanpham);
        values.put("soluong", soluong);
        long check = db.update("SANPHAM", values, "masanpham = ?", new String[]{String.valueOf(masanpham)});
        if (check == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int delete(int masanpham) {
        SQLiteDatabase db = dbs.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from CHITIETDONHANG where masanpham = ?", new String[]{String.valueOf(masanpham)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = db.delete("SANPHAM", "masanpham = ?", new String[]{String.valueOf(masanpham)});
        if (check == -1) {
            return 0;
        } else {
            return 1;
        }
    }

}
