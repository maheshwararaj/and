package com.example.practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductListActivity extends AppCompatActivity {
    Button viewCart;
    ListView listView;
    ArrayList<String> products;
    ArrayAdapter<String> adapter;
    public static HashMap<String, Integer> cart = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        listView = findViewById(R.id.listView);

        // Create a list of random products
        products = new ArrayList<>();
        products.add("Product 1");
        products.add("Product 2");
        products.add("Product 3");
        products.add("Product 4");
        products.add("Product 5");
        viewCart = findViewById(R.id.btnViewCart);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String product = products.get(position);
                addToCart(product);
            }
        });
        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CartActivity.class));
            }
        });
    }

    private void addToCart(String product) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + 1);
        } else {
            cart.put(product, 1);
        }
        Toast.makeText(this, product + " added to cart", Toast.LENGTH_SHORT).show();
    }
}
