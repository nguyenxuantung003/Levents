package com.example.levents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Chitiethoadon_DAO;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.databinding.ItemLsdathangchitietBinding;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ls_dathang_chitiet_Adapter extends RecyclerView.Adapter<ls_dathang_chitiet_Adapter.ViewHolder> {
    private ArrayList<Hoadonchitiet> list;
    private Context context;
    private Chitiethoadon_DAO dao;
    public ls_dathang_chitiet_Adapter(ArrayList<Hoadonchitiet> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new Chitiethoadon_DAO(context);
    }
    @NonNull
    @Override
    public ls_dathang_chitiet_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLsdathangchitietBinding binding = ItemLsdathangchitietBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ls_dathang_chitiet_Adapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ls_dathang_chitiet_Adapter.ViewHolder holder, int position) {
        Locale locale = new Locale("vi", "VN"); // Đặt ngôn ngữ là Tiếng Việt và quốc gia là Việt Nam
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String giaFormatted = numberFormat.format(list.get(position).getThanhTien());
        holder.binding.txtDonGia.setText("Giá: " + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtMaDonHang.setText("Mã đơn hàng: " + String.valueOf(list.get(position).getMaDonHang()));
        holder.binding.txtMaSanPham.setText("Mã sản phẩm: " + String.valueOf(list.get(position).getMaSanPham()));
        holder.binding.txtThanhTien.setText("Thành tiền: " + String.valueOf(giaFormatted));
        holder.binding.txtSoLuong.setText("Số lượng: " + String.valueOf(list.get(position).getSoLuong()));
        holder.binding.txttensanpham.setText(list.get(position).getTenSanPham());
        Picasso.get().load(list.get(position).getAnhsanpham()).into(holder.binding.imgAnhsp);
        Hoadonchitiet ct = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemLsdathangchitietBinding binding;

        public ViewHolder(ItemLsdathangchitietBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
