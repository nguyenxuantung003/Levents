package com.example.levents.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.Adapter.NguoiDung_Adapter;
import com.example.levents.DAO.Khachang_DAO;
import com.example.levents.Model.Khachhang;
import com.example.levents.R;

import java.util.ArrayList;


public class QL_nguoidung_Fragment extends Fragment {
    RecyclerView recyclerViewND;
    NguoiDung_Adapter nguoiDungAdapter;
    ArrayList<Khachhang> khachhangArrayList;
    Khachang_DAO khachangDao;
    ImageView addND;
    ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q_l_nguoidung_, container, false);
        recyclerViewND = view.findViewById(R.id.rcv_nguoidung);
        addND = view.findViewById(R.id.image_addND);
        addND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
        recyclerViewND.setLayoutManager(new LinearLayoutManager(getContext()));
        khachangDao = new Khachang_DAO(getContext().getApplicationContext());
        loadNguoiDungData();
        return view;
    }

    private void loadNguoiDungData() {
        khachangDao = new Khachang_DAO(getContext().getApplicationContext());
        khachhangArrayList = khachangDao.getAllNguoiDung();
        nguoiDungAdapter = new NguoiDung_Adapter(getContext(), khachangDao, khachhangArrayList);
        recyclerViewND.setAdapter(nguoiDungAdapter);
    }

    private void showAddDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_addnguoidung, null);

        final EditText edtTenDangNhap = dialogView.findViewById(R.id.edt_addtendangnhap);
        final EditText edtMatKhau = dialogView.findViewById(R.id.edt_addmkdangnhap);
        final EditText edtHoTen = dialogView.findViewById(R.id.edt_addhovatendangnhap);
        final EditText edtEmail = dialogView.findViewById(R.id.edt_addemaildangnhap);
        final EditText edtSDT = dialogView.findViewById(R.id.edt_addsdtdangnhap);
        final EditText edtDiaChi = dialogView.findViewById(R.id.edt_adddiachidangnhap);
        final EditText edtAnh = dialogView.findViewById(R.id.edt_addanhdangnhap);
        Button btnAddKH = dialogView.findViewById(R.id.bt_addKH);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        btnAddKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenDangNhap = edtTenDangNhap.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                String hoTen = edtHoTen.getText().toString();
                String email = edtEmail.getText().toString();
                String sdt = edtSDT.getText().toString();
                String diaChi = edtDiaChi.getText().toString();
                String anh = edtAnh.getText().toString();
                Khachhang nguoiDung = new Khachhang(tenDangNhap, matKhau, hoTen, email, sdt, diaChi, anh,"khachhang");
                boolean result = khachangDao.checkDangKy(nguoiDung);
                if (result) {

                    Toast.makeText(getContext(), "Thêm người dùng thành công", Toast.LENGTH_SHORT).show();

                    loadNguoiDungData();
                } else {

                    Toast.makeText(getContext(), "Thêm người dùng thất bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }

}