package com.example.practice;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    ListView listView;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        listView = findViewById(R.id.listView);

        List<Map<String, String>> data = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : ProductListActivity.cart.entrySet()) {
            Map<String, String> datum = new HashMap<>();
            datum.put("product", entry.getKey());
            datum.put("quantity", String.valueOf(entry.getValue()));
            data.add(datum);
        }

        String[] from = {"product", "quantity"};
        int[] to = {android.R.id.text1, android.R.id.text2};

        adapter = new SimpleAdapter(this, data, android.R.layout.simple_list_item_2, from, to);
        listView.setAdapter(adapter);
    }
}
