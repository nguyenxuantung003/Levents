package com.example.levents.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.levents.Adapter.QLSP_Adapter;
import com.example.levents.DAO.QLSanPham_DAO;
import com.example.levents.Model.Loaisanpham;
import com.example.levents.Model.QLsanpham;
import com.example.levents.R;
import com.example.levents.databinding.FragmentQLSphamBinding;

import java.util.ArrayList;
import java.util.HashMap;


public class QLSpham_Fragment extends Fragment {

    View vieww;

    QLSanPham_DAO dao;
    ArrayList<QLsanpham> list;
    QLSP_Adapter adapter;

    public QLSpham_Fragment() {
        // Required empty public constructor
    }

    FragmentQLSphamBinding binding ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQLSphamBinding.inflate(inflater, container, false);
        vieww = binding.getRoot();
        dao = new QLSanPham_DAO(getContext());
        list = dao.getsanphamall();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rcvQlsanpham.setLayoutManager(layoutManager);
        adapter = new QLSP_Adapter(list,getContext());
        binding.rcvQlsanpham.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // dialogthem();
            }
        });
        return vieww;
    }
   /* private ArrayList<HashMap<String, Object>> getDsLoaiSanPham() {
       QLSanPham_DAO loaiDao = new QLSanPham_DAO(getContext());
        ArrayList<Loaisanpham> list1 = loaiDao.getsanphamall();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiSanPham ls : list1) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloaisanpham", ls.getMaloaisp());
            hs.put("tenloaisanpham", ls.getTenloaisp());
            listHM.add(hs);
        }
        return listHM;
    }
    */
}