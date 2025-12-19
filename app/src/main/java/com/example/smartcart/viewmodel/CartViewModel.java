package com.example.smartcart.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcart.data.model.CartItem;
import com.example.smartcart.data.model.CartSummary;
import com.example.smartcart.data.model.Product;
import com.example.smartcart.data.repository.CartRepository;
import com.example.smartcart.utils.CouponEngine;
import com.example.smartcart.utils.TaxCalculator;

import java.util.List;

public class CartViewModel extends ViewModel {

    private final CartRepository repository = CartRepository.getInstance();

    private final MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>();
    private final MutableLiveData<CartSummary> summary = new MutableLiveData<>();

    private final MutableLiveData<Boolean> couponApplied = new MutableLiveData<>(false);
    private final MutableLiveData<Boolean> couponEligible = new MutableLiveData<>(false);
    private final MutableLiveData<String> couponMessage = new MutableLiveData<>("");

    public CartViewModel() {
        refresh();
    }

    // ─────────────────── LiveData getters ───────────────────

    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }

    public LiveData<CartSummary> getSummary() {
        return summary;
    }

    public LiveData<Boolean> isCouponApplied() {
        return couponApplied;
    }

    public LiveData<Boolean> isCouponEligible() {
        return couponEligible;
    }

    public LiveData<String> getCouponMessage() {
        return couponMessage;
    }

    // ─────────────────── Cart actions ───────────────────

    public void addProduct(Product product) {
        repository.addProduct(product);
        refresh();
    }

    public void increaseQty(Product product) {
        repository.increaseQty(product);
        refresh();
    }

    public void decreaseQty(Product product) {
        repository.decreaseQty(product);
        refresh();
    }

    // ─────────────────── Coupon actions ───────────────────

    public void applyCoupon() {
        if (!Boolean.TRUE.equals(couponEligible.getValue())) return;
        couponApplied.setValue(true);
        refresh();
    }

    public void removeCoupon() {
        couponApplied.setValue(false);
        refresh();
    }

    // ─────────────────── Core refresh logic ───────────────────

    private void refresh() {
        List<CartItem> items = repository.getCartItems();
        cartItems.setValue(items);

        double subtotal = 0;
        double eligibleAmount = 0;

        for (CartItem item : items) {
            subtotal += item.totalPrice();

            if (!item.product.isDiscounted) {
                eligibleAmount += item.totalPrice();
            }
        }

        double tax = TaxCalculator.calculateTax(items);

        boolean eligible = eligibleAmount >= 1000;
        couponEligible.setValue(eligible);

        double coupon = 0;

        if (Boolean.TRUE.equals(couponApplied.getValue()) && eligible) {
            coupon = CouponEngine.calculateDiscount(items);
            couponMessage.setValue("Coupon applied 🎉");
        } else if (!eligible) {
            couponApplied.setValue(false);
            couponMessage.setValue(
                    "Add ₹" + (int) (1000 - eligibleAmount) + " more to apply coupon"
            );
        } else {
            couponMessage.setValue("Coupon available");
        }

        double finalAmount = subtotal + tax - coupon;
        summary.setValue(new CartSummary(subtotal, tax, coupon, finalAmount));
    }

    // ─────────────────── Helpers ───────────────────

    public double getEligibleCouponAmount() {
        double eligible = 0;
        for (CartItem item : repository.getCartItems()) {
            if (!item.product.isDiscounted) {
                eligible += item.totalPrice();
            }
        }
        return eligible;
    }

    public void clearCart() {
        repository.clearCart();
        couponApplied.setValue(false);
        refresh();
    }
}
