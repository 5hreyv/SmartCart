package com.example.smartcart.ui.products;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartcart.R;
import com.example.smartcart.viewmodel.ProductViewModel;
import android.content.Intent;
import com.example.smartcart.viewmodel.CartViewModel;
import com.example.smartcart.ui.cart.CartActivity;

public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        findViewById(R.id.viewCartBtn).setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class))
        );

        RecyclerView recycler = findViewById(R.id.productRecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        ProductAdapter adapter = new ProductAdapter(product -> {
            CartViewModel cartViewModel =
                    new ViewModelProvider(this).get(CartViewModel.class);

            cartViewModel.addProduct(product);

            Toast.makeText(this,
                    "Added to cart",
                    Toast.LENGTH_SHORT).show();
        });


        recycler.setAdapter(adapter);

        ProductViewModel viewModel =
                new ViewModelProvider(this).get(ProductViewModel.class);

        viewModel.getProducts().observe(this, adapter::setProducts);
        viewModel.loadProducts();
    }
}
