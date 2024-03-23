package com.example.levents.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.Adapter.Sanpham_Adapter;
import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Model.Sanpham;
import com.example.levents.R;

import java.util.ArrayList;

public class Sanpham_Fragment extends Fragment {
    ArrayList<Sanpham> sanphams;
    Sanpham_DAO sanphamDao;
    Sanpham_Adapter sanphamAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham_, container, false);
        recyclerView = view.findViewById(R.id.rcv_sanpham);
        return view;
    }

}