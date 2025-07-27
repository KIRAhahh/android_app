package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailActivity extends AppCompatActivity {

    TextView txtTenHang, txtMoTa, txtGia;
    ImageView imgSanPham, btnFavorite;
    Button btnThemVaoGio;
    RatingBar ratingBar;
    boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_product_detail);

        // Ánh xạ view
        txtTenHang = findViewById(R.id.txtTenHang);
        txtMoTa = findViewById(R.id.txtMoTa);
        imgSanPham = findViewById(R.id.imgSanPham);
        btnThemVaoGio = findViewById(R.id.btnThemVaoGio);
        txtGia = findViewById(R.id.txtGia);
        ratingBar = findViewById(R.id.ratingBar);
        btnFavorite = findViewById(R.id.btnFavorite);

        // Nhận dữ liệu từ Home
        String tenHang = getIntent().getStringExtra("TEN_HANG");
        txtTenHang.setText(tenHang);

        // Gọi API để lấy thông tin sản phẩm
        loadProductDetailFromAPI(tenHang);

        // Thêm vào giỏ
        btnThemVaoGio.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("GIO_HANG", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            String gio = pref.getString("danhsach", "");
            gio += tenHang + ";";
            editor.putString("danhsach", gio);
            editor.apply();
            Toast.makeText(this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });

        // Thêm vào yêu thích
        btnFavorite.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            btnFavorite.setImageResource(isFavorite ? R.drawable.ic_heart_filled : R.drawable.ic_heart_border);
            Toast.makeText(this, isFavorite ? "Đã thêm vào yêu thích" : "Đã bỏ yêu thích", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadProductDetailFromAPI(String tenHang) {
        String url = "https://fakestoreapi.com/products";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            String title = obj.getString("title");

                            if (title.equalsIgnoreCase(tenHang)) {
                                // Gán thông tin chi tiết
                                txtMoTa.setText(obj.getString("description"));
                                txtGia.setText("Giá: " + obj.getString("price") + "đ");
                                Glide.with(this).load(obj.getString("image")).into(imgSanPham);

                                JSONObject rating = obj.getJSONObject("rating");
                                float rate = (float) rating.getDouble("rate");
                                ratingBar.setRating(rate);

                                break;
                            }
                        }
                    } catch (JSONException e) {
                        Toast.makeText(this, "Lỗi xử lý JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show());

        queue.add(request);
    }
}
