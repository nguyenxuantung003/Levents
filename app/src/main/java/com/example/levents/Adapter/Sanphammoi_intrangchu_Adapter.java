package com.example.levents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Interface.OnItemClick;
import com.example.levents.Model.Sanpham;
import com.example.levents.databinding.ItemSanphammoiBinding;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Sanphammoi_intrangchu_Adapter extends RecyclerView.Adapter<Sanphammoi_intrangchu_Adapter.ViewHolder> {
    private ArrayList<Sanpham> sanphams;
    private Context context;
    Sanpham_DAO sanphamDao;
    private OnItemClick onItemClick;
    public void setOnItemClick(OnItemClick listener) {
        onItemClick = listener;
    }
    public Sanpham getViTriSp(int position) {
        if (position >= 0 && position < sanphams.size()) {
            return sanphams.get(position);
        }
        return null;
    }

    public Sanphammoi_intrangchu_Adapter(ArrayList<Sanpham> sanphams, Context context) {
        this.sanphams = sanphams;
        this.context = context;
        sanphamDao = new Sanpham_DAO(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSanphammoiBinding binding= ItemSanphammoiBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Locale locale = new Locale("vi", "VN"); // Đặt ngôn ngữ là Tiếng Việt và quốc gia là Việt Nam
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String giaFormatted = numberFormat.format(sanphams.get(position).getGia());
        Picasso.get().load(sanphams.get(position).getAnhsanpham()).into(holder.binding.imgAnhListSp);
        holder.binding.txtgia.setText(String.valueOf(giaFormatted));
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
        return sanphams.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        ItemSanphammoiBinding binding;
        public ViewHolder(@NonNull ItemSanphammoiBinding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }
    }
}
