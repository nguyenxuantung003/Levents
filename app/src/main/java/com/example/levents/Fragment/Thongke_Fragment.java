package com.example.levents.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.levents.Adapter.Adapter_top3_khachhang;
import com.example.levents.Adapter.adapter_top3_sanphambanchay;
import com.example.levents.DAO.ThongKe_DAO;
import com.example.levents.Model.Hoadonchitiet;
import com.example.levents.Model.Khachhang;
import com.example.levents.databinding.FragmentThongkeBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class Thongke_Fragment extends Fragment {
    FragmentThongkeBinding binding;
    View view;

    public Thongke_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentThongkeBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        ThongKe_DAO thongKeDao = new ThongKe_DAO(getContext());
        ArrayList<Hoadonchitiet> list=thongKeDao.getTop32();
        ArrayList<Khachhang> listkh = thongKeDao.getTop3Khachhang2();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        binding.rvctop3sp.setLayoutManager(layoutManager);
        binding.rvctop3kh.setLayoutManager(layoutManager2);
        adapter_top3_sanphambanchay adapter=new adapter_top3_sanphambanchay(list,getContext());
        Adapter_top3_khachhang adapterTop3Khachhang = new Adapter_top3_khachhang(listkh,getContext());
        binding.rvctop3sp.setAdapter(adapter);
        binding.rvctop3kh.setAdapter(adapterTop3Khachhang);
        Calendar calendar = Calendar.getInstance();
        binding.btnlichBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, month, dayOfMonth);

                                // Kiểm tra nếu ngày chọn là ngày hiện tại hoặc sau ngày hiện tại và không quá ngày hiện tại
                                if (!selectedCalendar.after(Calendar.getInstance())) {
                                    String ngay = (dayOfMonth < 10) ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                                    String thang = ((month + 1) < 10) ? "0" + (month + 1) : String.valueOf(month + 1);
                                    binding.btnlichBatDau.setText(year + "/" + thang + "/" + ngay);
//                                    binding.btnlichBatDau.setText(ngay+"/"+thang+"/"+year);
                                } else {
                                    // Hiển thị thông báo hoặc thực hiện hành động khác nếu người dùng chọn ngày trước hoặc bằng ngày hiện tại.
                                    // Ví dụ: Toast.makeText(getContext(), "Chọn ngày trong khoảng từ ngày hiện tại đến quá khứ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                // Đặt giới hạn cho DatePickerDialog để chỉ cho phép chọn ngày trong khoảng từ ngày hiện tại đến quá khứ
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        binding.btnlichKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                                Calendar selectedCalendar = Calendar.getInstance();
                                selectedCalendar.set(year, month, dayOfMonth);

                                // Kiểm tra nếu ngày chọn là ngày hiện tại hoặc sau ngày hiện tại và không quá ngày hiện tại
                                if (!selectedCalendar.after(Calendar.getInstance())) {
                                    String ngay = (dayOfMonth < 10) ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                                    String thang = ((month + 1) < 10) ? "0" + (month + 1) : String.valueOf(month + 1);

                                    binding.btnlichKetThuc.setText(year + "/" + thang + "/" + ngay);
//                                    binding.btnlichKetThuc.setText(ngay+"/"+thang+"/"+year);
                                } else {
                                    // Hiển thị thông báo hoặc thực hiện hành động khác nếu người dùng chọn ngày trước hoặc bằng ngày hiện tại.
                                    // Ví dụ: Toast.makeText(getContext(), "Chọn ngày trong khoảng từ ngày hiện tại đến quá khứ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );

                // Đặt giới hạn cho DatePickerDialog để chỉ cho phép chọn ngày trong khoảng từ ngày hiện tại đến quá khứ
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        binding.btnthongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThongKe_DAO dao = new ThongKe_DAO(getContext());
//                "Ngày bắt đầu: "+ ngay + "/" + thang + "/" + year
                String ngaybd = binding.btnlichBatDau.getText().toString();
                String ngaykt = binding.btnlichKetThuc.getText().toString();

                int tongtien = dao.tongDoanhThu2(ngaybd, ngaykt);
                binding.txttongTien.setText(" Tổng doanh thu:"+String.valueOf(tongtien) );

                int tongdon = dao.tongDonHang2(ngaybd, ngaykt);
                binding.txtsoLuongDon.setText("Số lượng đơn hàng đã bán ra: "+String.valueOf(tongdon));

            }
        });
        return view;
    }
}