package com.example.levents.DAO;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
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
    private static final String COL_MASP = "masanpham";
    private static final String COL_TENSP = "tensanpham";
    private static final String COL_GIA = "gia";
    private static final String COL_MOTA = "mota";
    private static final String COL_ANHSP = "anhsanpham";
    private static final String COL_SOLUONG = "soluong";
    private static final String COL_SOLUONGBANRA = "soluongbanra";

    public Sanpham_DAO(Context context) {
        dBhelper = new DBhelper(context);
    }

    public ArrayList<Sanpham> getsanphamall() {
        ArrayList<Sanpham> list = new ArrayList();
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select sp.masanpham, sp.tensanpham, sp.gia,sp.mota,sp.anhsanpham,sp.soluong, sp.soluongbanra from SANPHAM sp", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Sanpham(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<Sanpham> getsanphamallSapXep() {
        ArrayList<Sanpham> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT sp.masanpham, sp.tensanpham, sp.gia, sp.mota, sp.anhsanpham, sp.soluong, sp.soluongbanra FROM SANPHAM sp ORDER BY sp.soluongbanra DESC", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Sanpham(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public boolean insert(String tensanpham, int gia, String mota, String anhsanpham, int soluong) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensanpham", tensanpham);
        values.put("gia", gia);
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
        SQLiteDatabase db = dBhelper.getWritableDatabase();
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
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from CHITIETHOADON where masanpham = ?", new String[]{String.valueOf(masanpham)});
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

    @SuppressLint("Range")
    public Sanpham getSanPhamById(int masanpham) {
        SQLiteDatabase database = dBhelper.getReadableDatabase();
        Sanpham sanPham = null;

        String[] columns = {COL_MASP, COL_TENSP, COL_GIA, COL_MOTA, COL_ANHSP, COL_SOLUONG,COL_SOLUONGBANRA};
        String selection = COL_MASP + "=?";
        String[] selectionArgs = {String.valueOf(masanpham)};

        Cursor cursor = database.query("SANPHAM", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int maSanPham = cursor.getInt(cursor.getColumnIndex(COL_MASP));
            String tenSanPham = cursor.getString(cursor.getColumnIndex(COL_TENSP));
            int gia = cursor.getInt(cursor.getColumnIndex(COL_GIA));
            String moTa = cursor.getString(cursor.getColumnIndex(COL_MOTA));
            String anhSanPham = cursor.getString(cursor.getColumnIndex(COL_ANHSP));
            int sl = cursor.getInt(cursor.getColumnIndex(COL_SOLUONG));
            int soluongbanra = cursor.getInt(cursor.getColumnIndex(COL_SOLUONGBANRA));
            sanPham = new Sanpham(maSanPham,tenSanPham,gia,moTa,anhSanPham,sl,soluongbanra);
        }

        cursor.close();
        return sanPham;
    } public boolean updateSlSanPham(int maSanPham, int newQuantity,int soluongbanra) {
        SQLiteDatabase database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soluong", newQuantity);
        values.put("soluongbanra",soluongbanra);
        // Đảm bảo rằng điều kiện WHERE sử dụng mã sản phẩm đúng
        String whereClause = "masanpham = ?";
        String[] whereArgs = {String.valueOf(maSanPham)};

        // Thực hiện cập nhật
        int rowsAffected = database.update("SANPHAM", values, whereClause, whereArgs);

        // Trả về true nếu có ít nhất một hàng bị ảnh hưởng
        return rowsAffected > 0;
    }
    public boolean updateSanpham(Sanpham sanpham) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        boolean success = false;
        try {
            ContentValues values = new ContentValues();
            values.put("tensanpham", sanpham.getTensanpham());
            values.put("gia", sanpham.getGia());
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
            values.put("mota", sanpham.getMota());
            values.put("anhsanpham", sanpham.getAnhsanpham());
            values.put("soluong", sanpham.getSoluong());
            values.put("soluongbanra",sanpham.getSoluotbanra());

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
