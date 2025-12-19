package com.example.smartcart.ui.checkout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.example.smartcart.R;
import com.example.smartcart.ui.products.ProductListActivity;
import com.example.smartcart.viewmodel.CartViewModel;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        CartViewModel cartViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        LottieAnimationView confetti = findViewById(R.id.confettiAnim);
        Button backBtn = findViewById(R.id.backBtn);

        // Play checkout animation once
        confetti.setAnimation(R.raw.confetti);
        confetti.playAnimation();

        // Clear cart ONCE after animation
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            cartViewModel.clearCart();

            Intent intent = new Intent(this, ProductListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }, 2500);

        // Optional: manual back
        backBtn.setOnClickListener(v -> {
            cartViewModel.clearCart();
            Intent intent = new Intent(this, ProductListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
