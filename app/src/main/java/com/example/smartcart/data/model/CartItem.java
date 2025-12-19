// CartItem.java
package com.example.smartcart.data.model;

public class CartItem {

    public Product product;
    public int quantity;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public double unitPrice() {
        return product.finalPrice;
    }

    public double totalPrice() {
        return product.finalPrice * quantity;
    }

    public double taxAmount() {
        return totalPrice() * product.taxPercent / 100;
    }
}
