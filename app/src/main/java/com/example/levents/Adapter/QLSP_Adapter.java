package com.example.levents.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.QLSanPham_DAO;
import com.example.levents.Model.QLsanpham;
import com.example.levents.databinding.ItemQlspBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class QLSP_Adapter extends RecyclerView.Adapter<QLSP_Adapter.ViewHolder> {
    private ArrayList<QLsanpham> list;
    private Context context;
    private ArrayList<HashMap<String, Object>> listHM;
    QLSanPham_DAO dao;

    public QLSP_Adapter(ArrayList<QLsanpham> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new QLSanPham_DAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ItemQlspBinding binding = ItemQlspBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }
//" masanpham integer primary key autoincrement," +
//                " tensanpham text not null," +
//                " gia integer not null," +
//                " maloaisanpham integer REFERENCES LOAISANPHAM(maloaisanpham)," +
//                " mota text not null," +
//                " anhsanpham text not null," +
//                " soluong integer not null," +
//                " soluongbanra integer not null)";
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.malsp.setText("MÃ sản phẩm: " + String.valueOf(list.get(position).getMasp()));
        holder.binding.txtTenSanPham.setText("Tên sản phẩm: " + list.get(position).getTensp());
        holder.binding.giasp.setText("Giá sản phẩm: " + String.valueOf(list.get(position).getGiasp()));
        holder.binding.malsp.setText("MÃ loại sản phẩm: " + String.valueOf(list.get(position).getMalsp()));
        holder.binding.mota.setText("Mô tả sản phẩm: " + list.get(position).getMoto());
        holder.binding.tenlsp.setText("Tên loại sản phẩm: "+list.get(position).getTenloaisp());
        holder.binding.soluong.setText("Số lượng: "+String.valueOf(list.get(position).getSoluong()));
        holder.binding.soluongbanra.setText("Số lượng đã bán: " + list.get(position).getSoluongbanra());
//        if (list.get(position).getSoluong() == 0){
//            holder.binding.txttrangThaiSanPham.setVisibility(View.GONE);
//            holder.binding.txttrangThaiSanPham1.setVisibility(View.VISIBLE);
//
//        }else {
//            holder.txt.txttrangThaiSanPham.setVisibility(View.VISIBLE);
//            holder.txt.txttrangThaiSanPham1.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemQlspBinding binding;

        public ViewHolder(ItemQlspBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
