package com.example.levents.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.levents.DAO.Sanpham_DAO;
import com.example.levents.Model.Sanpham;
import com.example.levents.R;
import com.example.levents.databinding.ItemsQlsanphamBinding;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class QLSP_Adapter extends RecyclerView.Adapter<QLSP_Adapter.ViewHolder> {
    private ArrayList<Sanpham> list;
    private Context context;
    private ArrayList<HashMap<String, Object>> listHM;
    Sanpham_DAO sanphamDao;

    public QLSP_Adapter(ArrayList<Sanpham> list, Context context) {
        this.list = list;
        this.context = context;
        sanphamDao = new Sanpham_DAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ItemsQlsanphamBinding binding = ItemsQlsanphamBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }
//" masanpham integer primary key autoincrement," +
//                " tensanpham text not null," +
//                " gia integer not null," +
//                " maloaisanpham integer REFERENCES LOAISANPHAM(maloaisanpham)," +
//                " mota text not null," +
//                " anhsanpham text not null," +
//                " soluong integer not null," +
//                " soluongbanra integer not null)";
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Locale locale = new Locale("vi", "VN"); // Đặt ngôn ngữ là Tiếng Việt và quốc gia là Việt Nam
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String giaFormatted = numberFormat.format(list.get(position).getGia());
        holder.binding.txtmaSanPham.setText("MÃ sản phẩm: " + String.valueOf(list.get(position).getMasanpham()));
        holder.binding.txtTenSanPham.setText("Tên sản phẩm: " + list.get(position).getTensanpham());
        holder.binding.txtgiaSanPham.setText("Giá sản phẩm: " + String.valueOf(giaFormatted));
        holder.binding.txtmoTa.setText("Mô tả: "+list.get(position).getMota());
        holder.binding.txtsoluong.setText("Số lượng: "+String.valueOf(list.get(position).getSoluong()));
        holder.binding.txtSoLuongBanRa.setText("Số lượng đã bán: " + list.get(position).getSoluotbanra());
        if (list.get(position).getSoluong() == 0){
            holder.binding.txttrangThaiSanPham.setVisibility(View.GONE);
            holder.binding.txttrangThaiSanPham1.setVisibility(View.VISIBLE);

        }else {
            holder.binding.txttrangThaiSanPham.setVisibility(View.VISIBLE);
            holder.binding.txttrangThaiSanPham1.setVisibility(View.GONE);
        }
        Picasso.get().load(list.get(position).getAnhsanpham()).into(holder.binding.imgItemAnhSanPham);
        Sanpham sp = list.get(position);
        holder.binding.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa sản phẩm có " + holder.binding.txtTenSanPham.getText() + " không?");

                // Tạo RelativeLayout để chứa nút "Không" và "Đồng ý"
                RelativeLayout layout = new RelativeLayout(context);

                // Tạo nút "Không"
                Button btnCancel = new Button(context);
                RelativeLayout.LayoutParams paramsCancel = new RelativeLayout.LayoutParams(
                        190, // Độ dài
                        75  // Chiều cao
                );
                paramsCancel.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE);
                paramsCancel.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                paramsCancel.setMargins(20, 10, 0, 10);
                btnCancel.setLayoutParams(paramsCancel);
                btnCancel.setText("Không");
                btnCancel.setBackgroundResource(R.drawable.cuttoms_btn);
                btnCancel.setTextColor(ContextCompat.getColor(context, android.R.color.black));
                btnCancel.setAllCaps(false);

                // Tạo nút "Đồng ý"
                Button btnAdd = new Button(context);
                RelativeLayout.LayoutParams paramsAdd = new RelativeLayout.LayoutParams(
                        190, // Độ dài
                        75  // Chiều cao
                );
                paramsAdd.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
                paramsAdd.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                paramsAdd.setMargins(0, 10, 20, 10);
                btnAdd.setLayoutParams(paramsAdd);
                btnAdd.setText("Đồng ý");
                btnAdd.setBackgroundResource(R.drawable.cuttoms_btn);
                btnAdd.setTextColor(ContextCompat.getColor(context, R.color.green));
                btnAdd.setAllCaps(false);

                // Thêm nút "Không" và "Đồng ý" vào RelativeLayout
                layout.addView(btnCancel);
                layout.addView(btnAdd);

                // Thiết lập RelativeLayout làm nội dung cho AlertDialog
                builder.setView(layout);

                // Thiết lập background cho AlertDialog
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.grey_background);

                // Hiển thị AlertDialog
                dialog.show();

                // Sử dụng biến dialog trong phương thức onClick của nút "Không"
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Đóng AlertDialog khi nút "Không" được nhấn
                        dialog.dismiss();
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Xử lý khi nút "Đồng ý" được nhấn
                        int check = sanphamDao.delete(list.get(holder.getAdapterPosition()).getMasanpham());
                        switch (check) {
                            case 1:
                                list.clear();
                                list.addAll(sanphamDao.getsanphamall());
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa thành công sản phẩm", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa không thành công sản phẩm", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không xóa được sản phẩm này vì đang còn tồn tại trong chi tiết hóa đơn", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        // Đóng AlertDialog sau khi xử lý
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemsQlsanphamBinding binding;

        public ViewHolder(ItemsQlsanphamBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
