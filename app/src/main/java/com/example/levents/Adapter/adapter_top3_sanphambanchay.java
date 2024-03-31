package com.example.levents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.ThongKe_DAO;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.databinding.ItemTop3SanphamBanchayBinding;

import java.util.ArrayList;

public class adapter_top3_sanphambanchay extends RecyclerView.Adapter<adapter_top3_sanphambanchay.Viewh> {

    private ArrayList<Hoadonchitiet> list;
    private Context context;
    private ThongKe_DAO dao;

    public adapter_top3_sanphambanchay(ArrayList<Hoadonchitiet> list, Context context) {
        this.list = list;
        this.context = context;
        dao=new ThongKe_DAO(context);
    }

    @NonNull
    @Override
    public Viewh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTop3SanphamBanchayBinding binding=ItemTop3SanphamBanchayBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Viewh(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewh holder, int position) {
        holder.biding.txtmaSanPham.setText("MÃ sản phẩm: " + String.valueOf(list.get(position).getMaSanPham()));
        holder.biding.txttenLoaiSanPham.setText("Tên sản phẩm: " + list.get(position).getTenSanPham());
        holder.biding.txtsoluongban.setText("Số lượng: "+String.valueOf(list.get(position).getSoLuong()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewh extends RecyclerView.ViewHolder {
        ItemTop3SanphamBanchayBinding biding;
        public Viewh(@NonNull ItemTop3SanphamBanchayBinding biding) {
            super(biding.getRoot());
            this.biding=biding;
        }
    }
}
