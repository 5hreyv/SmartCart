package com.example.smartcart.data.model;

public class CartSummary {
    public double subtotal;
    public double tax;
    public double couponDiscount;
    public double finalAmount;

    public CartSummary(double subtotal, double tax,
                       double couponDiscount, double finalAmount) {
        this.subtotal = subtotal;
        this.tax = tax;
        this.couponDiscount = couponDiscount;
        this.finalAmount = finalAmount;
    }
}
