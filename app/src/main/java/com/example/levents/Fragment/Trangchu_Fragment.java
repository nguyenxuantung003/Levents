package com.example.levents.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.levents.Adapter.Sanpham_Adapter;
import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Model.Sanpham;
import com.example.levents.R;
import java.util.List;
public class Trangchu_Fragment extends Fragment {
    View view;
    List<Sanpham> sanphams;
    Sanpham_Adapter sanphamAdapter;
    Sanpham_DAO sanphamDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trangchu_, container, false);

    }

}