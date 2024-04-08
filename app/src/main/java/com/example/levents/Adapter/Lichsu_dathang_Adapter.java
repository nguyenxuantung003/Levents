package com.example.levents.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Donhang_DAO;
import com.example.levents.Interface.OnItemClick;
import com.example.levents.Model.Hoadon;
import com.example.levents.databinding.ItemLichsuDathangBinding;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Lichsu_dathang_Adapter extends RecyclerView.Adapter<Lichsu_dathang_Adapter.ViewHolder> {
private ArrayList<Hoadon> hoadons;
private Donhang_DAO donhangDao;
private Context context;

    public Lichsu_dathang_Adapter( Context context,ArrayList<Hoadon> hoadons) {
        this.hoadons = hoadons;
        this.context = context;
        donhangDao = new Donhang_DAO(context);
    }
    private OnItemClick onItemClick;
    public void setOnItemClick(OnItemClick listener) {
        onItemClick = listener;
    }

    public Lichsu_dathang_Adapter(List<Hoadon> hoadons) {
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLichsuDathangBinding binding = ItemLichsuDathangBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hoadon donhang = hoadons.get(position);
        if (donhang != null) {
            // Kiểm tra đối tượng Hoadon có null hay không trước khi truy cập dữ liệu
            Log.d("HoadonData", "Mã đơn hàng: " + donhang.getMaDonHang());
            Log.d("HoadonData", "Mã người dùng: " + donhang.getMaTaiKhoan());
            Log.d("HoadonData", "Tên người dùng: " + donhang.getTenTaiKhoan());
            Log.d("HoadonData", "Ngày đặt hàng: " + donhang.getNgayDatHang());
            Log.d("HoadonData", "Trạng thái: " + donhang.getTrangthai());
            Log.d("HoadonData", "Tổng tiền: " + donhang.getTongTien());
            Locale locale = new Locale("vi", "VN"); // Đặt ngôn ngữ là Tiếng Việt và quốc gia là Việt Nam
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
            String giaFormatted = numberFormat.format(donhang.getTongTien());
            // Tiếp tục gán dữ liệu cho các view khác trong item
            holder.binding.txtMdonhang.setText("Mã đơn hàng: " + String.valueOf(donhang.getMaDonHang()));
            holder.binding.txtMnguoidung.setText("Mã người dùng: " + String.valueOf(donhang.getMaTaiKhoan()));
            holder.binding.txtDHTennguoidung.setText("Tên người dùng: " + donhang.getTenTaiKhoan());
            holder.binding.txtNgayDat.setText("Ngày đặt hàng: " + donhang.getNgayDatHang());
            holder.binding.txtTrangThai.setText("Trạng thái: " + donhang.getTrangthai());
            holder.binding.txtTongTien.setText("Tổng tiền: " + String.valueOf(giaFormatted));
        } else {
            Log.e("HoadonData", "Đối tượng Hoadon null tại vị trí: " + position);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(holder.getAdapterPosition());

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hoadons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemLichsuDathangBinding binding;
        public ViewHolder(ItemLichsuDathangBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
