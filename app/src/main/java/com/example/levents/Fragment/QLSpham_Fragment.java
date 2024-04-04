package com.example.levents.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.levents.Adapter.QLSP_Adapter;
import com.example.levents.Adapter.Sanpham_Adapter;
import com.example.levents.DAO.QLSanPham_DAO;
import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Model.Khachhang;
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
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_addsanpham, null);
        final EditText edttensanpham = view.findViewById(R.id.edt_addsanpham);
        final EditText edtgiasanpham = view.findViewById(R.id.edt_addgiasanpham);
        final EditText edtmaloai = view.findViewById(R.id.edt_addmaloai);
        final EditText edtmotasanpham = view.findViewById(R.id.edt_addmota);
        final EditText edtanhsanpham = view.findViewById(R.id.edt_addanhsanpham);
        final EditText edtsoluongsanpham = view.findViewById(R.id.edt_addsoluong);
        Button btnAddSp = view.findViewById(R.id.bt_addSp);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btnAddSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensanpham = edttensanpham.getText().toString();
                String giasanpham = edtgiasanpham.getText().toString();
                String mota = edtmotasanpham.getText().toString();
                String maloaisanpham = edtmaloai.getText().toString();
                String anhsanpham = edtanhsanpham.getText().toString();
                String soluong = edtsoluongsanpham.getText().toString();
                Sanpham sanpham = new Sanpham(tensanpham,giasanpham,maloaisanpham,mota,anhsanpham,10);
                dialog.dismiss();
            }
        });


    } 

}