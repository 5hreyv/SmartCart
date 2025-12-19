package com.example.smartcart.data.remote;

import android.os.Handler;
import android.os.Looper;

import com.example.smartcart.data.local.InMemoryProductSource;
import com.example.smartcart.data.model.Product;

import java.util.List;

public class ProductApiService {

    public interface Callback {
        void onSuccess(List<Product> products);
    }

    public void fetchProducts(Callback callback) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            callback.onSuccess(InMemoryProductSource.getProducts());
        }, 1000); // simulate network delay
    }
}
