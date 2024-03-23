package com.example.levents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Interface.OnAddToCart;
import com.example.levents.Interface.OnItemClick;
import com.example.levents.Model.Sanpham;
import com.example.levents.databinding.ItemSanphamBinding;
import com.example.levents.databinding.ItemSanphamTrangchuBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Fragment_Trangchu_Adapter extends RecyclerView.Adapter<Fragment_Trangchu_Adapter.ViewHolder> {
    private ArrayList<Sanpham> list;
    private Context context;
    Sanpham_DAO dao;
    private OnAddToCart onAddToCart;
    private OnItemClick onItemClick;
    public void setOnItemClick(OnItemClick listener){
        onItemClick = listener;
    }
    public Sanpham getViTriSp(int position) {
        if (position >= 0 && position < list.size()) {
            return list.get(position);
        }
        return null;
    }
    public void setOnAddToCartClickListenerTrangChu(OnAddToCart listener) {
        onAddToCart = listener;
    }


    public Fragment_Trangchu_Adapter(ArrayList<Sanpham> list, Context context, Sanpham_DAO dao) {
        this.list = list;
        this.context = context;
        this.dao = dao;
    }

    @NonNull
    @Override
    public Fragment_Trangchu_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSanphamBinding biding = ItemSanphamBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Fragment_Trangchu_Adapter.ViewHolder holder, int position) {
        holder.biding.txtTenSanPham.setText(list.get(position).getTensanpham());
        holder.biding.txtGiaSanPham.setText(list.get(position).getGia());
        Picasso.get().load(list.get(position).getAnhsanpham()).into(holder.biding.imgItemAnhSanPham);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(holder.getAdapterPosition());
                }
            }
        });
        if (list.get(position).getSoluong() == 0) {
            holder.biding.btnThemvaogio.setVisibility(View.GONE);
        } else {
            holder.biding.btnThemvaogio.setVisibility(View.VISIBLE);
        }
        holder.biding.btnThemvaogio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onAddToCart != null) {
                    onAddToCart.onAddToCartClick(list.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
       ItemSanphamTrangchuBinding biding;

        public ViewHolder(@NonNull ItemSanphamTrangchuBinding biding) {
            super(biding.getRoot());
            this.biding = biding;

        }
    }

}
