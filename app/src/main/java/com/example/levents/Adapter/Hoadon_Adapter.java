package com.example.levents.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Donhang_DAO;
import com.example.levents.Interface.OnItemClick;
import com.example.levents.Model.Hoadon;
import com.example.levents.R;
import com.example.levents.databinding.DialogTrangthaihoadonBinding;
import com.example.levents.databinding.DialogXoahoadonBinding;
import com.example.levents.databinding.ItemQlHoadonBinding;

import java.util.ArrayList;

public class Hoadon_Adapter extends RecyclerView.Adapter<Hoadon_Adapter.ViewHolder> {
    protected ArrayList<Hoadon> list;
    protected Donhang_DAO donhangDao;
    private Context context;
    public Hoadon_Adapter(ArrayList<Hoadon> list, Context context) {
        this.list = list;
        this.context = context;
        donhangDao = new Donhang_DAO(context);
    }
    private OnItemClick onItemClick;
    public void setOnItemClick(OnItemClick listener) {
        onItemClick = listener;
    }


    @NonNull
    @Override
    public Hoadon_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQlHoadonBinding binding = ItemQlHoadonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Hoadon_Adapter.ViewHolder holder, int position) {
        Hoadon hoadon = list.get(position);
        holder.binding.txtMdonhang.setText("Mã đơn hàng: " + String.valueOf(hoadon.getMaDonHang()));
        holder.binding.txtMnguoidung.setText("Mã người dung: " + String.valueOf(hoadon.getMaTaiKhoan()));
        holder.binding.txtDHTennguoidung.setText("Tên người dùng: " + hoadon.getTenTaiKhoan());
        holder.binding.txtNgayDat.setText("Ngày đặt hàng: " + hoadon.getNgayDatHang());
        holder.binding.txtTrangThai.setText("Trạng thái: " + hoadon.getTrangthai());
        holder.binding.txtTongTien.setText("Tổng tiền: " + String.valueOf(hoadon.getTongTien()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(holder.getAdapterPosition());

                }
            }
        });
        holder.binding.btnchinhsuaTrangThai.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            DialogTrangthaihoadonBinding dialogUpdateTrangThaiDonhangBinding = DialogTrangthaihoadonBinding.inflate(inflater);
            builder.setView(dialogUpdateTrangThaiDonhangBinding.getRoot());
            Dialog dialog = builder.create();
            dialog.show();
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.backgroud_dialog);
            dialogUpdateTrangThaiDonhangBinding.txtTrangThai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(context);
                    builder1.setTitle("Lựa chọn trạng thái");
                    String[] loai = {"Chờ phê duyệt","Đã phê duyệt","Đang giao hàng","Đã giao hàng"};

                    builder1.setItems(loai, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialogUpdateTrangThaiDonhangBinding.txtTrangThai.setText(loai[which]);

                        }
                    });
                    android.app.AlertDialog dialog1 = builder1.create();
                    dialog1.getWindow().setBackgroundDrawableResource(R.drawable.backgroud_dialog);
                    dialog1.show();
                }
            });
            dialogUpdateTrangThaiDonhangBinding.btnxacnhanTrangthai.setOnClickListener(view12 -> {
                String trangthai = dialogUpdateTrangThaiDonhangBinding.txtTrangThai.getText().toString();

                if (trangthai.equals("")) {
                    dialogUpdateTrangThaiDonhangBinding.txtTrangThai.setError("Vui lòng không để trống trạng thái");

                }
                list = donhangDao.getDonHangByMaTaiKhoan(hoadon.getMaTaiKhoan());
                hoadon.setMaDonHang(hoadon.getMaDonHang());
                hoadon.setTrangthai(trangthai);
                boolean check = donhangDao.updateDonHang(hoadon);
                if (check) {
                    list.clear();
                    list.addAll(donhangDao.getDsDonHang());

                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Thay đổi trang thái thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thay đổi trạng thái thất bại", Toast.LENGTH_SHORT).show();
                }
            });

            dialogUpdateTrangThaiDonhangBinding.btnhuyTrangthai.setOnClickListener(view1 -> dialog.dismiss());
        });

        holder.binding.btnXoaDonHang.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            DialogXoahoadonBinding dialogXoaDonHangBinding = DialogXoahoadonBinding.inflate(inflater);
            builder.setView(dialogXoaDonHangBinding.getRoot());

            Dialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.backgroud_dialog);
            dialog.show();
            dialogXoaDonHangBinding.btnOutXoaDonHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();
                }
            });
            dialogXoaDonHangBinding.btnConfilmXoaDonHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int check = donhangDao.xoaDonHang(list.get(holder.getAdapterPosition()).getMaDonHang());
                    switch (check) {
                        case 1:
                            list.clear();
                            list.addAll(donhangDao.getDsDonHang());
                            notifyDataSetChanged();
                            dialog.dismiss();
                            Toast.makeText(context, "Xóa thành công Đơn hàng", Toast.LENGTH_SHORT).show();
                            break;
                        case 0:
                            Toast.makeText(context, "Xóa không thành công Đơn hàng", Toast.LENGTH_SHORT).show();
                            break;
                        case -1:
                            Toast.makeText(context, "Không xóa được Đơn hàng này vì đang còn tồn tại trong chi tiết hóa đơn", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            });
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       ItemQlHoadonBinding binding;

        public ViewHolder(ItemQlHoadonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
