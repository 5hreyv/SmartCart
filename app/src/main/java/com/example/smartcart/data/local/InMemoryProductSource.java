package com.example.smartcart.data.local;

import com.example.smartcart.R;
import com.example.smartcart.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductSource {

    public static List<Product> getProducts() {

        List<Product> list = new ArrayList<>();

        list.add(new Product(
                "1",
                "Wireless Headphones",
                2999,
                2199,
                true,
                18,
                R.drawable.ic_headphones
        ));

        list.add(new Product(
                "2",
                "Smart Watch",
                3499,
                3499,
                false,
                18,
                R.drawable.ic_watch
        ));

        list.add(new Product(
                "3",
                "Bluetooth Speaker",
                1999,
                1599,
                true,
                5,
                R.drawable.ic_speaker
        ));

        list.add(new Product(
                "4",
                "Laptop Backpack",
                1299,
                1299,
                false,
                5,
                R.drawable.ic_bag
        ));

        list.add(new Product(
                "5",
                "USB-C Charger",
                999,
                799,
                true,
                18,
                R.drawable.ic_charger
        ));

        list.add(new Product(
                "6",
                "Wireless Mouse",
                899,
                899,
                false,
                5,
                R.drawable.ic_mouse
        ));

        list.add(new Product(
                "7",
                "Mechanical Keyboard",
                5499,
                4999,
                true,
                18,
                R.drawable.ic_keyboard
        ));

        list.add(new Product(
                "8",
                "Laptop Stand",
                1999,
                1999,
                false,
                5,
                R.drawable.ic_stand
        ));

        return list;
    }
}
