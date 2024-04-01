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
import com.example.levents.databinding.ItemSanphamBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Fragment_sanpham_Adapter extends RecyclerView.Adapter<Fragment_sanpham_Adapter.ViewHolder> {

    private ArrayList<Sanpham> sanphamArrayList;
    private Sanpham_DAO sanphamDao;
    private Context context;

    public Fragment_sanpham_Adapter(ArrayList<Sanpham> sanphamArrayList, Context context) {
        this.sanphamArrayList = sanphamArrayList;
        this.context = context;
        sanphamDao = new Sanpham_DAO(context);
    }
    private OnItemClick onItemClick;
    private OnAddToCart onAddToCart;

    public void setOnItemClick(OnItemClick listener){
        onItemClick = listener;
    }
    public Sanpham getViTriSp(int position) {
        if (position >= 0 && position < sanphamArrayList.size()) {
            return sanphamArrayList.get(position);
        }
        return null;

    }
    public void setOnAddToCartClickListenerSanpham(OnAddToCart listener) {
        onAddToCart = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSanphamBinding binding = ItemSanphamBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sanpham sanpham = sanphamArrayList.get(position);
        Picasso.get().load(sanpham.getAnhsanpham()).into(holder.binding.imgItemAnhSanPham);
        holder.binding.txtTenSanPham.setText(sanpham.getTensanpham());
        holder.binding.txtGiaSanPham.setText("Giá bán: " + sanpham.getGia());
        holder.binding.txtSoluong.setText("Số lượng: "+ sanpham.getSoluong());
        holder.binding.txtMoTa.setText(sanpham.getMota());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(holder.getAdapterPosition());
                    if (sanphamArrayList.get(position).getSoluong() == 0) {
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
                    onAddToCart.onAddToCartClick(sanphamArrayList.get(holder.getAdapterPosition()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanphamArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemSanphamBinding binding;
        public ViewHolder(ItemSanphamBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
