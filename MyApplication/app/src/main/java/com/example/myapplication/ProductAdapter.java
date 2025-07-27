package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> products;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = inflater.inflate(R.layout.item_product, parent, false);
        }

        // Ánh xạ view
        ImageView img = itemView.findViewById(R.id.imageProduct);
        TextView name = itemView.findViewById(R.id.textProductName);
        TextView price = itemView.findViewById(R.id.textProductPrice);

        Product p = products.get(position);
        name.setText(p.title);
        price.setText(p.price);

        // Load ảnh từ URL bằng Picasso
        Picasso.get().load(p.image).into(img);

        return itemView;
    }
}
