package com.example.smartcart.utils;

import com.example.smartcart.data.model.CartItem;

import java.util.List;

public class CouponEngine {

    private static final double MIN_CART_VALUE = 1000;
    private static final double DISCOUNT_PERCENT = 0.20;
    private static final double MAX_DISCOUNT = 300;

    /**
     * Calculates coupon discount based on assignment rules:
     * - Coupon applies ONLY on non-discounted items
     * - Minimum eligible cart value: ₹1000
     * - Discount: 20%
     * - Maximum discount: ₹300
     */
    public static double calculateDiscount(List<CartItem> items) {

        double eligibleSubtotal = 0;

        for (CartItem item : items) {
            // Exclude discounted items from coupon eligibility
            if (!item.product.isDiscounted) {
                eligibleSubtotal += item.totalPrice();
            }
        }

        // Minimum cart value check on eligible items only
        if (eligibleSubtotal < MIN_CART_VALUE) {
            return 0;
        }

        double discount = eligibleSubtotal * DISCOUNT_PERCENT;

        // Cap discount at ₹300
        return Math.min(discount, MAX_DISCOUNT);
    }
}
