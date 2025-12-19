// Product.java
package com.example.smartcart.data.model;

public class Product {

    public String id;
    public String name;

    // Prices
    public double originalPrice;   // MRP
    public double finalPrice;      // Price user pays

    public boolean isDiscounted;
    public int taxPercent;
    public int imageResId;

    public Product(
            String id,
            String name,
            double originalPrice,
            double finalPrice,
            boolean isDiscounted,
            int taxPercent,
            int imageResId
    ) {
        this.id = id;
        this.name = name;
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.isDiscounted = isDiscounted;
        this.taxPercent = taxPercent;
        this.imageResId = imageResId;
    }
}
