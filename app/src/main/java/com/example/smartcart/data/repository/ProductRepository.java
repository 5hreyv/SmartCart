package com.example.smartcart.data.repository;

import com.example.smartcart.data.model.Product;
import com.example.smartcart.data.local.InMemoryProductSource;

import java.util.List;

public class ProductRepository {

    public interface Callback {
        void onSuccess(List<Product> products);
    }

    public void getProducts(Callback callback) {
        // In-memory source (no network delay)
        callback.onSuccess(InMemoryProductSource.getProducts());
    }
}
