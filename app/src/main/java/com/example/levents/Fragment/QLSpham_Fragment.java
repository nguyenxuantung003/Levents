package com.example.levents.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.levents.Adapter.QLSP_Adapter;
import com.example.levents.Adapter.Sanpham_Adapter;
import com.example.levents.DAO.QLSanPham_DAO;
import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Model.Loaisanpham;
import com.example.levents.Model.QLsanpham;
import com.example.levents.Model.Sanpham;
import com.example.levents.R;
import com.example.levents.databinding.FragmentQLSphamBinding;

import java.util.ArrayList;
import java.util.HashMap;


public class QLSpham_Fragment extends Fragment {

    View view;
    RecyclerView recyclerView;

    QLSanPham_DAO dao;
    ArrayList<Sanpham> sanphamlist;
    QLSP_Adapter adapter;
    Sanpham_DAO sanphamDao;
    ImageView addSP;
    Sanpham_Adapter sanphamAdapter;

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
        sanphamAdapter = new Sanpham_Adapter(sanphamlist, getContext(),sanphamDao);
        recyclerView.setAdapter(sanphamAdapter);
    }

    private void ShowAddDialog() {
    }

}