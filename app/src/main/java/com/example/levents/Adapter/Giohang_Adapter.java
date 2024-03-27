package com.example.levents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Giohang_DAO;
import com.example.levents.Model.Giohang;
import com.example.levents.databinding.ItemSanphamIngiohangBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Giohang_Adapter extends RecyclerView.Adapter<Giohang_Adapter.ViewHolder> {
    private ArrayList<Giohang> list;
    Context context;
    Giohang_DAO giohangDao;
    private TotalPriceListener listener;
    public Giohang_Adapter(Context context,  ArrayList<Giohang> list) {
        this.context = context;
        this.list = list;
        giohangDao = new Giohang_DAO(context);

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSanphamIngiohangBinding binding = ItemSanphamIngiohangBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Giohang giohang = list.get(position);
        // Hiển thị thông tin sản phẩm
        holder.binding.txtgia.setText(String.valueOf(giohang.getSoLuongMua() * giohang.getGiaSanPham()));
        holder.binding.txtsoluong.setText(String.valueOf(giohang.getSoLuongMua()));
        holder.binding.txttensp.setText(giohang.getTenSanPham());
        Picasso.get().load(list.get(position).getAnhSanPham()).into(holder.binding.imganhsp);
        holder.binding.chkChonSanPham.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                giohang.setSelected(b);
                holder.binding.chkChonSanPham.setChecked(b);
                notifyDataSetChanged();
                updateTotalPrice();

            }
        });
        holder.binding.btncong.setOnClickListener(view -> {
            if (giohang.getSoLuongMua() < giohang.getSoLuong()) {
                giohang.setSoLuongMua(giohang.getSoLuongMua() + 1);
                giohangDao.updateGioHang(giohang);
                notifyDataSetChanged();
                updateTotalPrice();
            } else {
                Toast.makeText(context, "Không thể mua thêm, số lượng trong kho đã đạt tối đa", Toast.LENGTH_SHORT).show();
            }
        });
        holder.binding.btntru.setOnClickListener(view -> {
            if (giohang.getSoLuongMua() > 1) {
                giohang.setSoLuongMua(giohang.getSoLuongMua() - 1);

                giohangDao.updateGioHang(giohang);
                notifyDataSetChanged();
                updateTotalPrice();
            } else {

                removeItem(giohang);

            }
        });

    }

    public void updateCartList(ArrayList<Giohang> updatedList) {
        list.clear();
        list.addAll(updatedList);
//        this.list = updatedList;
        notifyDataSetChanged();

    }

    public Context getContext() {
        return  context;
    }

    public void setTotalPriceListener(TotalPriceListener listener) {
        this.listener = listener;
    }

    public interface TotalPriceListener {
        void onTotalPriceUpdated(int totalAmount);
    }
    private void removeItem(Giohang gioHang) {
        if (giohangDao.deleteGioHang(gioHang)) {
            list.remove(gioHang);

            notifyDataSetChanged();
            updateTotalPrice();
        } else {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    public void removeItem2(int pos) {
        Giohang gioHang1=list.get(pos);
        if (giohangDao.deleteGioHang(gioHang1)) {
            list.remove(gioHang1);

            notifyDataSetChanged();
            updateTotalPrice();
        } else {
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateTotalPrice() {
        if (listener != null) {
            int totalAmount = 0;

            for (Giohang gioHang : list) {
                if (gioHang.isSelected()) {
                    totalAmount += gioHang.getSoLuongMua() * gioHang.getGiaSanPham();
                }
            }
            listener.onTotalPriceUpdated(totalAmount);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      ItemSanphamIngiohangBinding binding;

        public ViewHolder(ItemSanphamIngiohangBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}
