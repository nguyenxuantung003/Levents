package com.example.levents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Interface.Itemclick;
import com.example.levents.Model.Sanpham;
import com.example.levents.databinding.ItemSanphamBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Sanpham_Adapter extends RecyclerView.Adapter<Sanpham_Adapter.ViewHolder> {
    protected ArrayList<Sanpham> list;
    protected Sanpham_DAO sanphamDao;
    private Context context;

    public Sanpham_Adapter(ArrayList<Sanpham> list, Sanpham_DAO sanphamDao, Context context) {
        this.list = list;
        this.sanphamDao = sanphamDao;
        this.context = context;
    }
    private Itemclick mListener;

    public Sanpham_Adapter(List<Sanpham> sanphams) {
    }

    public void setOnItemClick(Itemclick listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Sanpham_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSanphamBinding binding = ItemSanphamBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Sanpham_Adapter.ViewHolder holder, int position) {
        Sanpham sanpham = list.get(position);
        Picasso.get().load(list.get(position).getAnhsanpham()).into(holder.binding.imgItemAnhSanPham);
        holder.binding.txtTenSanPham.setText("Ten sp: " + String.valueOf(list.get(position).getTensanpham()));
        holder.binding.txtGiaSanPham.setText("Gia: "+ String.valueOf(list.get(position).getGia()));
        holder.binding.txtSoluong.setText("So luong: "+String.valueOf(list.get(position).getSoluong()));
        holder.binding.txtMoTa.setText("Mo ta: "+list.get(position).getMota());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.ItemClick(holder.getAdapterPosition());

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemSanphamBinding binding;
        public ViewHolder(ItemSanphamBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
