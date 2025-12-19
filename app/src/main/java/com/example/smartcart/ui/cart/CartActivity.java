package com.example.smartcart.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.smartcart.R;
import com.example.smartcart.data.model.Product;
import com.example.smartcart.ui.checkout.CheckoutActivity;
import com.example.smartcart.viewmodel.CartViewModel;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // 🔹 Empty State Views
        LinearLayout emptyStateLayout = findViewById(R.id.emptyStateLayout);
        Button browseProductsBtn = findViewById(R.id.browseProductsBtn);
        View summaryCard = findViewById(R.id.summaryCard);

        // 🔹 Recycler
        RecyclerView recycler = findViewById(R.id.cartRecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // 🔹 Summary Views
        TextView subtotal = findViewById(R.id.subtotal);
        TextView tax = findViewById(R.id.tax);
        TextView coupon = findViewById(R.id.coupon);
        TextView finalAmount = findViewById(R.id.finalAmount);
        TextView couponMessage = findViewById(R.id.couponMessage);
        Button applyCouponBtn = findViewById(R.id.applyCouponBtn);

        LottieAnimationView emptyAnim =
                findViewById(R.id.emptyCartAnimation);

        CartViewModel vm = new ViewModelProvider(this).get(CartViewModel.class);

        CartAdapter adapter = new CartAdapter(new CartAdapter.Listener() {
            @Override
            public void onPlus(Product product) {
                vm.increaseQty(product);
            }

            @Override
            public void onMinus(Product product) {
                vm.decreaseQty(product);
            }
        });

        recycler.setAdapter(adapter);

        // 🔹 Empty cart handling
        vm.getCartItems().observe(this, items -> {
            adapter.setItems(items);

            boolean isEmpty = items == null || items.isEmpty();

            emptyStateLayout.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
            recycler.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
            summaryCard.setVisibility(isEmpty ? View.GONE : View.VISIBLE);

            if (isEmpty) {
                emptyAnim.playAnimation();
            } else {
                emptyAnim.pauseAnimation();
            }
        });



        // 🔹 Summary
        vm.getSummary().observe(this, s -> {
            subtotal.setText("Subtotal      ₹" + (int) s.subtotal);
            tax.setText("Tax                  ₹" + (int) s.tax);

            if (s.couponDiscount > 0) {
                coupon.setText("Coupon Discount   -₹" + (int) s.couponDiscount);
            } else {
                coupon.setText("Coupon Discount   ₹0");
            }

            finalAmount.setText("Total        ₹" + (int) s.finalAmount);
        });

        // 🔹 Coupon eligibility UX
        vm.isCouponEligible().observe(this, eligible -> {
            applyCouponBtn.setEnabled(eligible);
            applyCouponBtn.setAlpha(eligible ? 1f : 0.5f);

            if (!eligible) {
                couponMessage.setText(
                        "Add ₹" + (int) (1000 - vm.getEligibleCouponAmount()) +
                                " more to apply coupon"

                );
            } else {
                couponMessage.setText("Coupon available 🎉");
            }
        });

        applyCouponBtn.setOnClickListener(v -> {
            if (!Boolean.TRUE.equals(vm.isCouponEligible().getValue())) return;

            if (Boolean.TRUE.equals(vm.isCouponApplied().getValue())) {
                vm.removeCoupon();
            } else {
                vm.applyCoupon();
            }
        });

        vm.getCouponMessage().observe(this, couponMessage::setText);

        vm.isCouponApplied().observe(this, applied -> {
            applyCouponBtn.setText(applied ? "Remove Coupon" : "Apply Coupon");
            applyCouponBtn.setBackgroundTintList(
                    getColorStateList(applied ? R.color.error : R.color.coupon_orange_dark)
            );
        });

        findViewById(R.id.checkoutBtn).setOnClickListener(v ->
                startActivity(new Intent(this, CheckoutActivity.class))
        );

        browseProductsBtn.setOnClickListener(v -> finish());
    }

}
