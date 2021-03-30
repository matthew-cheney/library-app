package com.example.libraryofpeers.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.libraryofpeers.BarcodeScanner;
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
    Button scanButton;
    final int BARCODE_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        spinner = findViewById(R.id.itemDropdownMenu);
        addBookItemFragment = new AddBookItemFragment();
        addMovieItemFragment = new AddMovieItemFragment();
        addGameItemFragment = new AddGameItemFragment();
        returnHomeArrow = findViewById(R.id.returnHomeArrow);
        scanButton = findViewById(R.id.scan_barcode_button);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeScanner.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, BARCODE_CODE);
            }
        });

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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(NewItemActivity.this, R.layout.new_item_selected, itemNames);
        arrayAdapter.setDropDownViewResource(R.layout.new_item_dropdown);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (BARCODE_CODE) : {
                if (resultCode == BarcodeScanner.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    String returnValue = data.getStringExtra("isbn");
                    Toast.makeText(getApplicationContext(), returnValue, Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
}