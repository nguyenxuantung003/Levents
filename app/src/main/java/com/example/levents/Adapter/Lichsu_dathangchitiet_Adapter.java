package com.example.levents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Chitiethoadon_DAO;
import com.example.levents.Interface.OnItemClick;
import com.example.levents.Model.Hoadon;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.databinding.ItemLichsuDathangBinding;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Lichsu_dathangchitiet_Adapter extends RecyclerView.Adapter<Lichsu_dathangchitiet_Adapter.ViewHolder> {
    private ArrayList<Hoadon> hoadons;
        private ArrayList<Hoadonchitiet> hoadonchitiets;
        private Chitiethoadon_DAO chitiethoadonDao;
        private Context context;

    public Lichsu_dathangchitiet_Adapter(ArrayList<Hoadonchitiet> hoadonchitiets, Context context) {
        this.hoadonchitiets = hoadonchitiets;
        this.context = context;
        chitiethoadonDao = new Chitiethoadon_DAO(context);
    }
    private OnItemClick onItemClick;
    public void setOnItemClick(OnItemClick listener) {
        onItemClick = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLichsuDathangBinding binding = ItemLichsuDathangBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Lichsu_dathangchitiet_Adapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Hoadon donhang = hoadons.get(position);
        Locale locale = new Locale("vi", "VN"); // Đặt ngôn ngữ là Tiếng Việt và quốc gia là Việt Nam
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String giaFormatted = numberFormat.format(donhang.getTongTien());
        holder.binding.txtMdonhang.setText("Mã đơn hàng: " +String.valueOf(donhang.getMaDonHang()));
        holder.binding.txtMnguoidung.setText("Mã người dung: " +String.valueOf(donhang.getMaTaiKhoan()));
        holder.binding.txtDHTennguoidung.setText("Tên người dùng: " +donhang.getTenTaiKhoan());
        holder.binding.txtNgayDat.setText("Ngày đặt hàng: " +donhang.getNgayDatHang());
        holder.binding.txtTrangThai.setText("Trạng thái: " + donhang.getTrangthai());
        holder.binding.txtTongTien.setText("Tổng tiền: " +String.valueOf(giaFormatted));
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
