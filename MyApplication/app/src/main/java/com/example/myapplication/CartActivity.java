package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class CartActivity extends AppCompatActivity {

    ListView listCart;
    ArrayList<String> sanphamList;
    CartAdapter adapter;
    Button btnThanhToan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart);

        listCart = findViewById(R.id.listCart);
        btnThanhToan = findViewById(R.id.btnThanhToan);

        SharedPreferences pref = getSharedPreferences("GIO_HANG", MODE_PRIVATE);
        String gio = pref.getString("danhsach", "");

        if (gio.isEmpty()) {
            sanphamList = new ArrayList<>();
            sanphamList.add("Giỏ hàng trống.");
        } else {
            sanphamList = new ArrayList<>(Arrays.asList(gio.split(";")));
        }

        adapter = new CartAdapter(this, sanphamList);
        listCart.setAdapter(adapter);

        listCart.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = sanphamList.get(position);

            if (selectedItem.equals("Giỏ hàng trống.")) return;

            new AlertDialog.Builder(CartActivity.this)
                    .setTitle("Xóa sản phẩm")
                    .setMessage("Bạn có chắc muốn xóa \"" + selectedItem + "\" khỏi giỏ hàng?")
                    .setPositiveButton("Xóa", (dialog, which) -> {
                        sanphamList.remove(position);
                        updateSharedPreferences();
                        updateListView();
                    })
                    .setNegativeButton("Hủy", null)
                    .show();
        });

        btnThanhToan.setOnClickListener(v -> {
            if (sanphamList.isEmpty() || sanphamList.get(0).equals("Giỏ hàng trống.")) {
                Toast.makeText(this, "Giỏ hàng đang trống!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_LONG).show();
                sanphamList.clear();
                updateSharedPreferences();
                updateListView();
            }
        });
    }

    void updateSharedPreferences() {
        SharedPreferences pref = getSharedPreferences("GIO_HANG", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        StringBuilder newList = new StringBuilder();
        for (String item : sanphamList) {
            newList.append(item).append(";");
        }

        editor.putString("danhsach", newList.toString());
        editor.apply();
    }

    void updateListView() {
        if (sanphamList.isEmpty()) {
            sanphamList.add("Giỏ hàng trống.");
        }
        adapter.notifyDataSetChanged();
    }
}
