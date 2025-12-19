package com.example.smartcart.data.repository;

import com.example.smartcart.data.model.CartItem;
import com.example.smartcart.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepository {

    // SINGLE SOURCE OF TRUTH
    private static CartRepository instance;
    private final List<CartItem> cartItems = new ArrayList<>();

    private CartRepository() {}

    public static CartRepository getInstance() {
        if (instance == null) {
            instance = new CartRepository();
        }
        return instance;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addProduct(Product product) {
        for (CartItem item : cartItems) {
            if (item.product.id.equals(product.id)) {
                item.quantity++;
                return;
            }
        }
        cartItems.add(new CartItem(product));
    }

    public void removeProduct(Product product) {
        cartItems.removeIf(
                item -> item.product.id.equals(product.id)
        );
    }

    public void increaseQty(Product product) {
        for (CartItem item : cartItems) {
            if (item.product.id.equals(product.id)) {
                item.quantity++;
                return;
            }
        }
    }

    public void decreaseQty(Product product) {
        for (CartItem item : cartItems) {
            if (item.product.id.equals(product.id)) {
                item.quantity--;
                if (item.quantity <= 0) {
                    cartItems.remove(item);
                }
                return;
            }
        }
    }

    public void clearCart() {
        cartItems.clear();
    }
}
