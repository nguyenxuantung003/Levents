package com.example.levents.Fragment;

import android.annotation.SuppressLint;
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
    @SuppressLint("MissingInflatedId")
    private void ShowAddDialog() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_addsanpham, null);

        final EditText edttensanpham = dialogView.findViewById(R.id.edt_addtensanpham);
        final EditText edtgiasanpham = dialogView.findViewById(R.id.edt_addgiasanpham);
        final EditText edtsoluongsanpham = dialogView.findViewById(R.id.edt_addsoluongsanpham);
        final EditText edtanhsanpham = dialogView.findViewById(R.id.edt_addanhsanpham);
        final EditText edtmotasanpham = dialogView.findViewById(R.id.edt_addmotasanpham);
        Button btnAddSP = dialogView.findViewById(R.id.bt_addSP);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        btnAddSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensanpham = edttensanpham.getText().toString();
                int giasanpham = Integer.parseInt(edtgiasanpham.getText().toString());
                int soluongsanpham = Integer.parseInt(edtsoluongsanpham.getText().toString());
                String anhsanpham = edtanhsanpham.getText().toString();
                String motasanpham = edtmotasanpham.getText().toString();
                Sanpham sanpham = new Sanpham(tensanpham, giasanpham, soluongsanpham, motasanpham,anhsanpham,0);
                boolean result = sanphamDao.insertSanpham(sanpham);
                if (result) {
                    Toast.makeText(getContext(), "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    LoaddataSanpham();
                } else {

                    Toast.makeText(getContext(), "Thêm sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }
    }

