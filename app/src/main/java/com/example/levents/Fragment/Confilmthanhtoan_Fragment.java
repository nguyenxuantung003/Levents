package com.example.levents.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.levents.Adapter.Thanhtoan_Adapter;
import com.example.levents.DAO.Chitiethoadon_DAO;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.R;
import com.example.levents.databinding.FragmentConfilmthanhtoanBinding;

import java.util.ArrayList;

public class Confilmthanhtoan_Fragment extends Fragment {
    public Confilmthanhtoan_Fragment() {
    }
    private FragmentConfilmthanhtoanBinding binding;
    private ArrayList<Hoadonchitiet> list = new ArrayList<>();
    private Chitiethoadon_DAO chitiethoadonDao;
    private Thanhtoan_Adapter thanhtoanAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       binding = FragmentConfilmthanhtoanBinding.inflate(inflater, container, false);
       chitiethoadonDao = new Chitiethoadon_DAO(getContext());
        binding.rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        Bundle bundle = getArguments();
        if (bundle != null) {
            int maDonHang = bundle.getInt("maDonHang", 0);
            if (maDonHang != 0) {
                list = chitiethoadonDao.getChiTietDonHangByMaDonHang(maDonHang);
                thanhtoanAdapter = new Thanhtoan_Adapter(list, getContext());
                binding.rcv.setAdapter(thanhtoanAdapter);

            }
        }
        binding.btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Giohang_Fragment frgGioHang=new Giohang_Fragment();
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.framelayout_trangchu,frgGioHang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return binding.getRoot();
    }
}