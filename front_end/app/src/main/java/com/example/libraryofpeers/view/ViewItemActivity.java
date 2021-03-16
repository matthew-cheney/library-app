package com.example.libraryofpeers.view;

import Response.EditItemResponse;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.async_tasks.EditItemTask;

public class ViewItemActivity extends AppCompatActivity implements EditItemTask.EditItemObserver {
    ImageView returnHomeArrow;
    TextView titleTextView;
    EditText titleEditText;
    TextView dateAddedTextView;
    TextView descriptionTextView;
    EditText descriptionEditText;
    TextView imageUrlTextView;
    EditText imageUrlEditText;
    TextView authorTextView;
    EditText authorEditText;
    TextView genreTextView;
    EditText genreEditText;
    TextView releaseYearTextView;
    EditText releaseYearEditText;
    TextView itemFormatTextView;
    EditText itemFormatEditText;
    TextView numPlayersTextView;
    EditText numPlayersEditText;
    TextView timeGamePlayTextView;
    EditText timeGamePlayEditText;
    Button saveItemBtn;
    ToggleButton editItemBtn;
    Button deleteItemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        returnHomeArrow = findViewById(R.id.returnHomeArrow);
        titleTextView = (TextView) findViewById(R.id.itemTitleText);
        titleEditText = (EditText) findViewById(R.id.itemTitleEditor);
        dateAddedTextView = (TextView) findViewById(R.id.itemDateText);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditor);
        descriptionTextView = (TextView) findViewById(R.id.descriptionText);
        imageUrlEditText = (EditText) findViewById(R.id.imageUrlEditor);
        imageUrlTextView = (TextView) findViewById(R.id.imageUrlText);
        authorEditText = (EditText) findViewById(R.id.authorEditor);
        authorTextView = (TextView) findViewById(R.id.authorText);
        genreEditText = (EditText) findViewById(R.id.genreEditor);
        genreTextView = (TextView) findViewById(R.id.genreText);
        releaseYearEditText = (EditText) findViewById(R.id.releaseYearEditor);
        releaseYearTextView = (TextView) findViewById(R.id.releaseYearText);
        itemFormatEditText = (EditText) findViewById(R.id.itemFormatEditor);
        itemFormatTextView = (TextView) findViewById(R.id.itemFormatText);
        numPlayersEditText = (EditText) findViewById(R.id.numPlayersEditor);
        numPlayersTextView = (TextView) findViewById(R.id.numPlayersText);
        timeGamePlayEditText = (EditText) findViewById(R.id.timeGamePlayEditor);
        timeGamePlayTextView = (TextView) findViewById(R.id.timeGamePlayText);

        saveItemBtn = (Button) findViewById(R.id.saveItemBtn);
        editItemBtn = (ToggleButton) findViewById(R.id.editItemBtn);
        deleteItemBtn = (Button) findViewById(R.id.deleteItemBtn);

        initializeFields();

        returnHomeArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });

        saveItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItem();
            }
        });

        editItemBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    editingNow();
                    saveItemBtn.setVisibility(View.VISIBLE);
                } else {
                    editingDone();
                    saveItemBtn.setVisibility(View.GONE);
                }
            }
        });

        deleteItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem();
            }
        });

    }

    private void initializeFields() {

    }

    private void returnHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void saveItem() {

    }

    private void deleteItem() {

    }

    private void editingNow() {
        titleTextView.setVisibility(View.GONE);
        titleEditText.setVisibility(View.VISIBLE);

    }

    private void editingDone() {
        titleEditText.setVisibility(View.GONE);
        titleTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onEditSuccess(EditItemResponse response) {

    }

    @Override
    public void onEditFailure(EditItemResponse response) {

    }
}