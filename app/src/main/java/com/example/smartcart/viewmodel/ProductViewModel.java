package com.example.smartcart.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcart.data.model.Product;
import com.example.smartcart.data.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {

    private final MutableLiveData<List<Product>> products =
            new MutableLiveData<>();

    private final ProductRepository repository =
            new ProductRepository();

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public void loadProducts() {
        repository.getProducts(new ProductRepository.Callback() {
            @Override
            public void onSuccess(List<Product> result) {
                products.setValue(result);
            }
        });
    }
}
