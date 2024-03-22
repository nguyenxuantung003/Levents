package com.example.levents.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.Adapter.Sanpham_Adapter;
import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Model.Sanpham;
import com.example.levents.R;

import java.util.ArrayList;
import java.util.List;

public class Sanpham_Fragment extends Fragment {
    ArrayList<Sanpham> sanphams;
    Sanpham_DAO sanphamDao;
    Sanpham_Adapter sanphamAdapter;
    RecyclerView recyclerView;

    public Sanpham_Fragment() {
        this.sanphams = sanphams;
        this.sanphamDao = sanphamDao;
        this.sanphamAdapter = sanphamAdapter;
        this.recyclerView = recyclerView;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sanpham_, container, false);
        recyclerView = view.findViewById(R.id.rcv_sanpham);
        new LoadSanphamTask().execute();
        return view;
    }
    private class LoadSanphamTask extends AsyncTask<Void, Void, List<Sanpham>> {

        protected List<Sanpham> doInBackground(Void... voids) {
            return sanphamDao.getAllSanpham(); // Lấy danh sách sản phẩm từ cơ sở dữ liệu
        }
        @Override
        protected void onPostExecute(List<Sanpham> sanphams) {
            super.onPostExecute(sanphams);
            if (sanphams != null && !sanphams.isEmpty()) {
                // Hiển thị danh sách sản phẩm trong RecyclerView nếu có dữ liệu
                sanphamAdapter = new Sanpham_Adapter(sanphams);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(sanphamAdapter);
            }
        }
    }
}