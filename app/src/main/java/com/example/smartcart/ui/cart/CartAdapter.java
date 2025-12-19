package com.example.smartcart.ui.cart;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcart.R;
import com.example.smartcart.data.model.CartItem;
import com.example.smartcart.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.VH> {

    public interface Listener {
        void onPlus(Product product);
        void onMinus(Product product);
    }

    private final Listener listener;
    private List<CartItem> items = new ArrayList<>();

    public CartAdapter(Listener listener) {
        this.listener = listener;
    }

    public void setItems(List<CartItem> list) {
        items = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new VH(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        CartItem item = items.get(position);

        h.image.setImageResource(item.product.imageResId);
        h.name.setText(item.product.name);
        h.price.setText("₹" + (int) item.totalPrice());
        h.qty.setText(String.valueOf(item.quantity));

        h.plus.setOnClickListener(v -> listener.onPlus(item.product));
        h.minus.setOnClickListener(v -> listener.onMinus(item.product));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, price, qty;
        ImageButton plus, minus;

        VH(View v) {
            super(v);
            image = v.findViewById(R.id.productImage);
            name = v.findViewById(R.id.name);
            price = v.findViewById(R.id.price);
            qty = v.findViewById(R.id.qty);
            plus = v.findViewById(R.id.plus);
            minus = v.findViewById(R.id.minus);
        }
    }
}
