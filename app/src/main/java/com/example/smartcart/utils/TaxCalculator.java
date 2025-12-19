package com.example.smartcart.utils;

import com.example.smartcart.data.model.CartItem;
import java.util.List;

public class TaxCalculator {

    public static double calculateTax(List<CartItem> items) {
        double tax = 0;
        for (CartItem item : items) {
            tax += item.taxAmount();
        }
        return tax;
    }
}
