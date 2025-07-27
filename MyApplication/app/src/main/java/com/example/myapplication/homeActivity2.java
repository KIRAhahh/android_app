package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class homeActivity2 extends AppCompatActivity {

    ListView listNganh;
    ArrayList<Product> productList;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home2);

        // Ánh xạ view
        listNganh = findViewById(R.id.list);
        Button btnCart = findViewById(R.id.btnCart);

        // Khởi tạo danh sách + adapter
        productList = new ArrayList<>();
        adapter = new ProductAdapter(this, productList);
        listNganh.setAdapter(adapter);

        // Gọi API để lấy dữ liệu sản phẩm
        fetchProductsFromAPI();

        // Sự kiện khi click vào item
        listNganh.setOnItemClickListener((parent, view, position, id) -> {
            Product product = productList.get(position);
            Intent intent = new Intent(homeActivity2.this, ProductDetailActivity.class);
            intent.putExtra("TEN_HANG", product.title);
            startActivity(intent);
        });

        // Nút giỏ hàng
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(homeActivity2.this, CartActivity.class);
            startActivity(intent);
        });
    }

    private void fetchProductsFromAPI() {
        String url = "https://fakestoreapi.com/products";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject object = response.getJSONObject(i);

                            Product product = new Product();
                            product.id = object.getInt("id");
                            product.title = object.getString("title");
                            product.price = object.getString("price") + "đ";
                            product.image = object.getString("image");

                            productList.add(product);
                        }
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Lỗi xử lý JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show();
                });

        queue.add(jsonArrayRequest);
    }
}
