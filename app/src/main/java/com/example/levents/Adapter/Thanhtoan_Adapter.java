package com.example.levents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Chitiethoadon_DAO;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.databinding.ItemConfilmthanhtoanBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Thanhtoan_Adapter extends RecyclerView.Adapter<Thanhtoan_Adapter.ViewHolder> {
    private ArrayList<Hoadonchitiet> list = new ArrayList<>();
    private Context context;
    private Chitiethoadon_DAO chitiethoadonDao;


    public Thanhtoan_Adapter(ArrayList<Hoadonchitiet> list, Context context) {
        this.list = list;
        this.context = context;
        chitiethoadonDao = new Chitiethoadon_DAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemConfilmthanhtoanBinding binding = ItemConfilmthanhtoanBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.txtTenSanPham.setText("Tên sản phẩm: " + list.get(position).getTenSanPham());
        holder.binding.txtMaSanPham.setText("Mã sản phẩm: " + String.valueOf(list.get(position).getMaSanPham()));
        holder.binding.txtMaDonHang.setText("Mã đơn hàng: " + String.valueOf(list.get(position).getMaDonHang()));
        holder.binding.txtSoLuong.setText("Số lượng: " + String.valueOf(list.get(position).getSoLuong()));
        holder.binding.txtDonGia.setText("Giá: " + String.valueOf(list.get(position).getDonGia()));
        holder.binding.txtThanhTien.setText("Thành tiền: " + String.valueOf(list.get(position).getThanhTien()));
        Picasso.get().load(list.get(position).getAnhsanpham()).into(holder.binding.imgAnhSp);
       Hoadonchitiet ct=list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemConfilmthanhtoanBinding binding;

        public ViewHolder(ItemConfilmthanhtoanBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
