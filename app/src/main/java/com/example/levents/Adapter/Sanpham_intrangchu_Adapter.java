package com.example.levents.Adapter;

import android.annotation.SuppressLint;
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
import com.example.levents.databinding.ItemSanphamTrangchuBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Sanpham_intrangchu_Adapter extends RecyclerView.Adapter<Sanpham_intrangchu_Adapter.ViewHolder> {
    private ArrayList<Sanpham> list;
    private Context context;
    Sanpham_DAO sanphamDao;

    public Sanpham_intrangchu_Adapter(ArrayList<Sanpham> list, Context context) {
        this.list = list;
        this.context = context;

        sanphamDao = new Sanpham_DAO(context);
    }
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

    @NonNull
    @Override
    public Sanpham_intrangchu_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSanphamTrangchuBinding biding = ItemSanphamTrangchuBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(biding);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull Sanpham_intrangchu_Adapter.ViewHolder holder,int position) {
        Sanpham sanpham = list.get(position);
        holder.binding.txtTenSanPham.setText(sanpham.getTensanpham());
        holder.binding.txtGiaSanPham.setText(String.valueOf(sanpham.getGia()));
        Picasso.get().load(sanpham.getAnhsanpham()).into(holder.binding.imgItemAnhSanPham);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(holder.getAdapterPosition());
                    if (list.get(position).getSoluong() == 0) {
                        holder.binding.btnThemvaogio.setVisibility(View.GONE);
                    } else {
                        holder.binding.btnThemvaogio.setVisibility(View.VISIBLE);
                    }

                }

            }
        });
        holder.binding.btnThemvaogio.setOnClickListener(new View.OnClickListener() {
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

    public  class ViewHolder extends RecyclerView.ViewHolder {
       ItemSanphamTrangchuBinding binding;
        public ViewHolder(@NonNull ItemSanphamTrangchuBinding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }
    }
}
