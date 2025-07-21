package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BrandAdapter extends BaseAdapter {

    private Context context;
    private String[] brandNames;
    private String[] brandPrices;

    public BrandAdapter(Context context, String[] brandNames, String[] brandPrices) {
        this.context = context;
        this.brandNames = brandNames;
        this.brandPrices = brandPrices;
    }

    @Override
    public int getCount() {
        return brandNames.length;
    }

    @Override
    public Object getItem(int position) {
        return brandNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Tạo View cho mỗi item trong ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        ImageView imgLogo = convertView.findViewById(R.id.imgLogo);
        TextView txtTen = convertView.findViewById(R.id.txtTen);
        TextView txtGia = convertView.findViewById(R.id.txtGia);

        txtTen.setText(brandNames[position]);
        txtGia.setText("Giá: " + brandPrices[position]);
        imgLogo.setImageResource(R.drawable.preview); // Gắn cùng 1 ảnh

        return convertView;
    }
}
