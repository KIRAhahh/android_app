package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    TextView txtTenHang, txtMoTa,txtGia;
    ImageView imgSanPham;
    Button btnThemVaoGio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_product_detail);

        txtTenHang = findViewById(R.id.txtTenHang);
        txtMoTa = findViewById(R.id.txtMoTa);
        imgSanPham = findViewById(R.id.imgSanPham);
        btnThemVaoGio = findViewById(R.id.btnThemVaoGio);
        txtGia = findViewById(R.id.txtGia);

        String tenHang = getIntent().getStringExtra("TEN_HANG");
        txtTenHang.setText(tenHang);


        switch (tenHang) {
            case "YAMAHA":

                txtMoTa.setText("Yamaha nổi tiếng với chất lượng và độ bền cao.");
                txtGia.setText("Giá: 5.000.000đ");
                break;
            case "FENDER":
                txtMoTa.setText("Fender – biểu tượng của guitar điện, âm thanh mạnh mẽ.");
                txtGia.setText("Giá: 9.000.000đ");

                break;
            default:
                txtMoTa.setText("Thông tin sản phẩm đang được cập nhật.");
                txtGia.setText("Giá: Đang cập nhật");
        }


        btnThemVaoGio.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("GIO_HANG", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();


            String gio = pref.getString("danhsach", "");
            gio += tenHang + ";";

            editor.putString("danhsach", gio);
            editor.apply();

            Toast.makeText(this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });
        ImageView btnFavorite = findViewById(R.id.btnFavorite);
        final boolean[] isFavorite = {false};

        btnFavorite.setOnClickListener(v -> {
            isFavorite[0] = !isFavorite[0];
            btnFavorite.setImageResource(isFavorite[0] ? R.drawable.ic_heart_filled : R.drawable.ic_heart_border);
            Toast.makeText(this, isFavorite[0] ? "Đã thêm vào yêu thích" : "Đã bỏ yêu thích", Toast.LENGTH_SHORT).show();
        });

    }
}
