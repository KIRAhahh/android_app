package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> items;

    public CartAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override public int getCount() { return items.size(); }

    @Override public Object getItem(int position) { return items.get(position); }

    @Override public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);

        ImageView img = convertView.findViewById(R.id.imgProduct);
        TextView txtTen = convertView.findViewById(R.id.txtTenSP);
        TextView txtGia = convertView.findViewById(R.id.txtGiaSP);

        String name = items.get(position);
        String gia;

        switch (name) {
            case "YAMAHA": gia = "10.000.000đ"; break;
            case "SQUIER": gia = "7.500.000đ"; break;
            case "FENDER": gia = "12.000.000đ"; break;
            case "EVH": gia = "15.000.000đ"; break;
            default: gia = "0đ";
        }

        txtTen.setText(name);
        txtGia.setText("Giá: " + gia);
        img.setImageResource(R.drawable.preview);

        return convertView;
    }
}
