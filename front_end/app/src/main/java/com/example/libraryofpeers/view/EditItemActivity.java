package com.example.libraryofpeers.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.libraryofpeers.R;

import Entities.Item;

public class EditItemActivity extends AppCompatActivity {

    TextView itemTitle;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        item = (Item) getIntent().getSerializableExtra("item");

        itemTitle = findViewById(R.id.textView3);
        itemTitle.setText(item.getTitle());
    }
}