package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class homeActivity2 extends AppCompatActivity {

    ListView listNganh;

    // Danh sách tên thương hiệu
    String[] brandNames = {
            "YAMAHA",
            "SQUIER",
            "FENDER",
            "EVH"
    };

    // Danh sách giá tương ứng
    String[] brandPrices = {
            "10.000.000đ",
            "7.500.000đ",
            "12.000.000đ",
            "15.000.000đ"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home2);

        // Gắn list view
        listNganh = findViewById(R.id.list);

        // Sử dụng adapter tùy chỉnh để gắn hình + tên + giá
        BrandAdapter adapter = new BrandAdapter(this, brandNames, brandPrices);
        listNganh.setAdapter(adapter);

        // Bắt sự kiện click vào từng item
        listNganh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tenHang = brandNames[position];

                Intent intent = new Intent(homeActivity2.this, ProductDetailActivity.class);
                intent.putExtra("TEN_HANG", tenHang);
                startActivity(intent);
            }
        });

        // Nút giỏ hàng
        Button btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(homeActivity2.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
