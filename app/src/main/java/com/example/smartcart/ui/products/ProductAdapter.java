package com.example.smartcart.ui.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcart.R;
import com.example.smartcart.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {

    public interface Listener {
        void onAdd(Product product);
    }

    private final Listener listener;
    private List<Product> products = new ArrayList<>();

    public ProductAdapter(Listener listener) {
        this.listener = listener;
    }

    public void setProducts(List<Product> list) {
        products = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Product p = products.get(position);

        // Image
        h.productImage.setImageResource(p.imageResId);

        // Name
        h.name.setText(p.name);

        // Final price (always shown)
        h.finalPrice.setText("₹" + (int) p.finalPrice);

        // Tax chip
        h.taxChip.setText("GST " + p.taxPercent + "%");

        // Discount handling
        if (p.isDiscounted) {
            h.originalPrice.setVisibility(View.VISIBLE);
            h.originalPrice.setText("₹" + (int) p.originalPrice);
            h.discountChip.setVisibility(View.VISIBLE);
        } else {
            h.originalPrice.setVisibility(View.GONE);
            h.discountChip.setVisibility(View.GONE);
        }

        // Add to cart
        h.addBtn.setOnClickListener(v -> listener.onAdd(p));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class VH extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView name, finalPrice, originalPrice, taxChip, discountChip;
        Button addBtn;

        VH(View v) {
            super(v);
            productImage = v.findViewById(R.id.productImage);
            name = v.findViewById(R.id.name);
            finalPrice = v.findViewById(R.id.finalPrice);
            originalPrice = v.findViewById(R.id.originalPrice);
            taxChip = v.findViewById(R.id.taxChip);
            discountChip = v.findViewById(R.id.discountChip);
            addBtn = v.findViewById(R.id.addBtn);
        }
    }
}
