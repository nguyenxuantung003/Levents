package com.example.levents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Chitiethoadon_DAO;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.databinding.ItemChitiethoadonBinding;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Hoadonchitiet_Adapter extends RecyclerView.Adapter<Hoadonchitiet_Adapter.ViewHolder> {
    private ArrayList<Hoadonchitiet> list;
    private Context context;
    private Chitiethoadon_DAO chitiethoadonDao;
    public Hoadonchitiet_Adapter(ArrayList<Hoadonchitiet> list, Context context) {
        this.list = list;
        this.context = context;
        chitiethoadonDao = new Chitiethoadon_DAO(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemChitiethoadonBinding binding = ItemChitiethoadonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Hoadonchitiet_Adapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Locale locale = new Locale("vi", "VN"); // Đặt ngôn ngữ là Tiếng Việt và quốc gia là Việt Nam
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String giaFormatted = numberFormat.format(list.get(position).getThanhTien());
        holder.binding.txtDonGia.setText("Giá: " + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtmaChiTietDon.setText("Mã chi tiết đơn: " + String.valueOf(list.get(position).getMaChiTietDonHang()));
        holder.binding.txtMaDonHang.setText("Mã đơn hàng: " + String.valueOf(list.get(position).getMaDonHang()));
        holder.binding.txtMaSanPham.setText("Mã sản phẩm: " + String.valueOf(list.get(position).getMaSanPham()));
        holder.binding.txtThanhTien.setText("Thành tiền: " + String.valueOf(giaFormatted));
        holder.binding.txtSoLuong.setText("Số lượng: " + String.valueOf(list.get(position).getSoLuong()));
        holder.binding.txttensanpham.setText("Tên sản phẩm: " + list.get(position).getTenSanPham());
        Picasso.get().load(list.get(position).getAnhsanpham()).into(holder.binding.imgAnhsp);
        Hoadonchitiet ct = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       ItemChitiethoadonBinding binding;

        public ViewHolder(ItemChitiethoadonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
