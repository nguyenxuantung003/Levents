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

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Sanpham_Adapter extends RecyclerView.Adapter<Sanpham_Adapter.ViewHolder> {
    protected ArrayList<Sanpham> list;
    protected Sanpham_DAO sanphamDao;
    private Context context;

    public Sanpham_Adapter(ArrayList<Sanpham> list, Context context, Sanpham_DAO sanphamDao) {
        this.list = list;
        this.context = context;
        this.sanphamDao = sanphamDao;
        notifyDataSetChanged();
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
        Locale locale = new Locale("vi", "VN"); // Đặt ngôn ngữ là Tiếng Việt và quốc gia là Việt Nam
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String giaFormatted = numberFormat.format(list.get(position).getGia());
        Picasso.get().load(list.get(position).getAnhsanpham()).into(holder.binding.imgItemAnhSanPham);
        holder.binding.txtTenSanPham.setText(String.valueOf(list.get(position).getTensanpham()));
        holder.binding.txtGiaSanPham.setText(String.valueOf(giaFormatted));
        holder.binding.txtSoluong.setText("Số lượng: "+String.valueOf(list.get(position).getSoluong()));
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
