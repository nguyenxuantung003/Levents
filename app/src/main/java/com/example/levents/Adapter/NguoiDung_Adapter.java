package com.example.levents.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Khachang_DAO;
import com.example.levents.Database.DBhelper;
import com.example.levents.Model.Khachhang;
import com.example.levents.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NguoiDung_Adapter extends RecyclerView.Adapter<NguoiDung_Adapter.NguoiDungApdater> {
    Context context;
    Khachang_DAO khachangDao;
    DBhelper dBhelper;
    ArrayList<Khachhang> listKh;

    public NguoiDung_Adapter(Context context, Khachang_DAO khachangDao, ArrayList<Khachhang> listKh) {
        this.context = context;
        this.khachangDao = khachangDao;
        this.listKh = listKh;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NguoiDungApdater onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_qlnguoidung, parent, false);
        return new NguoiDungApdater(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiDungApdater holder, @SuppressLint("RecyclerView") int position) {
        Khachhang khachhang = listKh.get(position);
        holder.manguoidung.setText(String.valueOf(khachhang.getMakhachhang()));
        holder.hovaten.setText(khachhang.getHoten());
        holder.email.setText(khachhang.getEmail());
        holder.diachi.setText(khachhang.getDiachi());
        holder.sdt.setText(khachhang.getSodienthoai());
        Picasso.get().load(khachhang.getAnhkhachhang()).into(holder.imageND);
        holder.xoaND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(position);
            }
        });
    }

    private void showDeleteConfirmationDialog(final int position) {
        Khachhang khachhang = listKh.get(position);
        int maNhanVien = khachhang.getMakhachhang();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có chắc muốn xóa người dùng này?")
                .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (khachangDao.xoaKhachhang(maNhanVien)) {
                            // Nếu xóa thành công từ cơ sở dữ liệu, tiếp tục xóa người dùng từ danh sách
                            listKh.remove(position);
                            // Cập nhật danh sách sau khi xóa
                            notifyDataSetChanged();
                        } else {
                            // Xử lý khi không thể xóa từ cơ sở dữ liệu
                            Toast.makeText(context, "Không thể xóa người dùng từ cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.dismiss();
                    }
                });
        // Hiển thị dialog
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return listKh.size();
    }

    static class NguoiDungApdater extends RecyclerView.ViewHolder {
        ImageView imageND, xoaND;
        TextView manguoidung, hovaten, email, diachi, sdt, tien;

        public NguoiDungApdater(@NonNull View itemView) {
            super(itemView);
            xoaND = itemView.findViewById(R.id.img_xoaND);
            imageND = itemView.findViewById(R.id.img_nguoidung);
            manguoidung = itemView.findViewById(R.id.tv_manguoidung);
            hovaten = itemView.findViewById(R.id.tv_tennguoidung);
            email = itemView.findViewById(R.id.tv_emailnguoidung);
            diachi = itemView.findViewById(R.id.tv_diachinguoidung);
            sdt = itemView.findViewById(R.id.tv_sdtnguoidung);
        }
    }
}
