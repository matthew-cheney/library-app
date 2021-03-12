package com.example.libraryofpeers.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.libraryofpeers.R;

import java.util.ArrayList;
import java.util.List;

public class NewItemActivity extends AppCompatActivity {

    AddBookItemFragment addBookItemFragment;
    AddMovieItemFragment addMovieItemFragment;
    AddGameItemFragment addGameItemFragment;
    Spinner spinner;
    List<String> itemNames;
    ImageView returnHomeArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        spinner = findViewById(R.id.itemDropdownMenu);
        addBookItemFragment = new AddBookItemFragment();
        addMovieItemFragment = new AddMovieItemFragment();
        addGameItemFragment = new AddGameItemFragment();
        returnHomeArrow = findViewById(R.id.returnHomeArrow);

        itemNames = new ArrayList<>();
        itemNames.add("Book");
        itemNames.add("Movie");
        itemNames.add("Game");

        returnHomeArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(NewItemActivity.this, R.layout.new_item, itemNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        selectBookFragment(addBookItemFragment);
                        break;
                    case 1:
                        selectMovieFragment(addMovieItemFragment);
                        break;
                    case 2:
                        selectGameFragment(addGameItemFragment);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void returnHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void selectBookFragment(AddBookItemFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.itemFragmentLayout, fragment);
        fragmentTransaction.commit();
    }

    private void selectMovieFragment(AddMovieItemFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.itemFragmentLayout, fragment);
        fragmentTransaction.commit();
    }

    private void selectGameFragment(AddGameItemFragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.itemFragmentLayout, fragment);
        fragmentTransaction.commit();
    }
}