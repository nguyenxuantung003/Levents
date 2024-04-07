package com.example.levents.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.Adapter.QLSP_Adapter;
import com.example.levents.Adapter.Sanpham_Adapter;
import com.example.levents.DAO.QLSanPham_DAO;
import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Model.Sanpham;
import com.example.levents.R;
import com.example.levents.databinding.FragmentQLSphamBinding;

import java.util.ArrayList;


public class QLSpham_Fragment extends Fragment {
    View view;
    RecyclerView recyclerView;

    EditText edtTenSP, edtGia, edtMoTa, edtAnh, edtSoLuong;

    QLSanPham_DAO dao;
    ArrayList<Sanpham> sanphamlist;
    QLSP_Adapter adapter;
    Sanpham_DAO sanphamDao;
    ImageView addSP;
    Sanpham_Adapter sanphamAdapter;
    QLSP_Adapter qlspAdapter;

    public QLSpham_Fragment() {
        // Required empty public constructor
    }
    public void OnCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    FragmentQLSphamBinding binding ;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_l_spham_,container,false);
        addSP = view.findViewById(R.id.addSP);
        recyclerView = view.findViewById(R.id.rcv_qlsanpham);
        addSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAddDialog();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sanphamDao = new Sanpham_DAO(getContext().getApplicationContext());
        LoaddataSanpham();
        return view;
        
    }

    private void LoaddataSanpham() {
        sanphamDao = new Sanpham_DAO(getContext().getApplicationContext());
        sanphamlist = sanphamDao.getsanphamall();
        qlspAdapter = new QLSP_Adapter(sanphamlist, getContext());
        recyclerView.setAdapter(qlspAdapter);
    }

    private void ShowAddDialog() {
        AlertDialog.Builder dialogThemSP = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view1 = layoutInflater.inflate(R.layout.alertdialog_them_sp_admin, null);
        dialogThemSP.setView(view1);

        AlertDialog alertDialog = dialogThemSP.create();

        // ánh xạ
         edtTenSP = view1.findViewById(R.id.edtTenSP);
         edtGia = view1.findViewById(R.id.edtGia);
         edtMoTa = view1.findViewById(R.id.edtMoTa);
         edtAnh = view1.findViewById(R.id.edtAnhSP);
         edtSoLuong = view1.findViewById(R.id.edtSoLuong);
        Button btnThem = view1.findViewById(R.id.btnThem);
        Button btnThoat = view1.findViewById(R.id.btnThoat);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSP = edtTenSP.getText().toString();
                String giaBan = edtGia.getText().toString();
                String moTa = edtMoTa.getText().toString();
                String anh = edtAnh.getText().toString();
                String soLuong = edtSoLuong.getText().toString();
               // Sanpham sp = new Sanpham(tenSP, Integer.valueOf(giaBan), moTa, anh, Integer.valueOf(soLuong), 0);
                if (isCheck()) {
                    if (sanphamDao.insert2(tenSP, Integer.valueOf(giaBan), moTa, anh, Integer.valueOf(soLuong))) {
                        sanphamlist.clear();
                        sanphamlist.addAll(sanphamDao.getAllSanpham());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Thêm sản phẩm thành công !", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm sản phẩm thất bại !", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

        public boolean isCheck(){
            if (edtTenSP.length()==0){
                edtTenSP.setError("Không để trống tên");
                return false;
            } else if (edtGia.length()==0) {
                edtGia.setError("Không để trống giá");
                return false;
            }
            edtTenSP.setError(null);
            return true;
        }

}