package com.example.libraryofpeers.view;

import Config.Constants;
import Entities.Item;
import Request.DeleteItemRequest;
import Request.EditItemRequest;
import Response.DeleteItemResponse;
import Response.EditItemResponse;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.async_tasks.DeleteItemTask;
import com.example.libraryofpeers.async_tasks.EditItemTask;
import com.example.libraryofpeers.presenters.DeleteItemPresenter;
import com.example.libraryofpeers.presenters.EditItemPresenter;

public class ViewItemActivity extends AppCompatActivity implements EditItemTask.EditItemObserver, DeleteItemTask.DeleteItemObserver {
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
    Item item;
    ConstraintLayout authorLayout;
    ConstraintLayout genreLayout;
    ConstraintLayout releaseYearLayout;
    ConstraintLayout itemFormatLayout;
    ConstraintLayout numPlayersLayout;
    ConstraintLayout timeGamePlayLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        item = (Item) getIntent().getSerializableExtra("item");


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

        authorLayout = (ConstraintLayout) findViewById(R.id.authorLayout);
        genreLayout = (ConstraintLayout) findViewById(R.id.genreLayout);
        releaseYearLayout = (ConstraintLayout) findViewById(R.id.releaseYearLayout);
        itemFormatLayout = (ConstraintLayout) findViewById(R.id.itemFormatLayout);
        numPlayersLayout = (ConstraintLayout) findViewById(R.id.numPlayersLayout);
        timeGamePlayLayout = (ConstraintLayout) findViewById(R.id.timeGamePlayLayout);

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
                    editingNow(item.getCategory());
                    saveItemBtn.setVisibility(View.VISIBLE);
                } else {
                    editingDone(item.getCategory());
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
        titleTextView.setText(item.getTitle());
        titleEditText.setText(item.getTitle());
        dateAddedTextView.setText(item.getDateCreated());
        descriptionTextView.setText(item.getDescription());
        descriptionEditText.setText(item.getDescription());
        imageUrlTextView.setText(item.getImageUrl());
        imageUrlEditText.setText(item.getImageUrl());
        authorTextView.setText(item.getAuthor());
        authorEditText.setText(item.getAuthor());
        genreTextView.setText(item.getGenre());
        genreEditText.setText(item.getGenre());
        releaseYearTextView.setText(String.valueOf(item.getReleaseYear()));
        releaseYearEditText.setText(String.valueOf(item.getReleaseYear()));
        itemFormatTextView.setText(item.getItemFormat());
        itemFormatEditText.setText(item.getItemFormat());
        numPlayersTextView.setText(String.valueOf(item.getNumPlayers()));
        numPlayersEditText.setText(String.valueOf(item.getNumPlayers()));
        timeGamePlayTextView.setText(String.valueOf(item.getTimeToPlayInMins()));
        timeGamePlayEditText.setText(String.valueOf(item.getTimeToPlayInMins()));

        String itemCategory = item.getCategory();
        if (itemCategory.equals(Constants.BOARD_GAME_CATEGORY)) {
            numPlayersLayout.setVisibility(View.VISIBLE);
            timeGamePlayLayout.setVisibility(View.VISIBLE);
        } else {
            genreLayout.setVisibility(View.VISIBLE);
            releaseYearLayout.setVisibility(View.VISIBLE);
            itemFormatLayout.setVisibility(View.VISIBLE);
            if (itemCategory.equals(Constants.BOOK_CATEGORY)) {
                authorLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void returnHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void saveItem() {

        item.setTitle(titleEditText.getText().toString());
        item.setDescription(descriptionEditText.getText().toString());
        item.setImageUrl(imageUrlEditText.getText().toString());
        item.setAuthor(authorEditText.getText().toString());
        item.setGenre(genreEditText.getText().toString());
        String releaseYear = releaseYearEditText.getText().toString();
        item.setReleaseYear(Integer.parseInt(releaseYear));
        item.setItemFormat(itemFormatEditText.getText().toString());
        String numPlayers = numPlayersEditText.getText().toString();
        item.setNumPlayers(Integer.parseInt(numPlayers));
        String timeGamePlay = timeGamePlayEditText.getText().toString();
        item.setTimeToPlayInMins(Integer.parseInt(timeGamePlay));

        EditItemRequest request = new EditItemRequest(item);
        EditItemPresenter presenter = new EditItemPresenter();
        EditItemTask task = new EditItemTask(this, presenter);
        task.execute(request);
    }

    private void deleteItem() {
        DeleteItemRequest request = new DeleteItemRequest(item.getId());
        DeleteItemPresenter presenter = new DeleteItemPresenter();
        DeleteItemTask task = new DeleteItemTask(this, presenter);
        task.execute(request);
    }

    private void editingNow(String category) {
        titleTextView.setVisibility(View.GONE);
        titleEditText.setVisibility(View.VISIBLE);
        descriptionTextView.setVisibility(View.GONE);
        descriptionEditText.setVisibility(View.VISIBLE);
        imageUrlTextView.setVisibility(View.GONE);
        imageUrlEditText.setVisibility(View.VISIBLE);

        if (category.equals(Constants.BOARD_GAME_CATEGORY)) {
            numPlayersTextView.setVisibility(View.GONE);
            numPlayersEditText.setVisibility(View.VISIBLE);
            timeGamePlayTextView.setVisibility(View.GONE);
            timeGamePlayEditText.setVisibility(View.VISIBLE);
        } else {
            genreTextView.setVisibility(View.GONE);
            genreEditText.setVisibility(View.VISIBLE);
            releaseYearTextView.setVisibility(View.GONE);
            releaseYearEditText.setVisibility(View.VISIBLE);
            itemFormatTextView.setVisibility(View.GONE);
            itemFormatEditText.setVisibility(View.VISIBLE);
            if (category.equals(Constants.BOOK_CATEGORY)) {
                authorTextView.setVisibility(View.GONE);
                authorEditText.setVisibility(View.VISIBLE);
            }
        }
    }

    private void editingDone(String category) {
        titleEditText.setVisibility(View.GONE);
        titleTextView.setVisibility(View.VISIBLE);
        descriptionEditText.setVisibility(View.GONE);
        descriptionTextView.setVisibility(View.VISIBLE);
        imageUrlEditText.setVisibility(View.GONE);
        imageUrlTextView.setVisibility(View.VISIBLE);

        if (category.equals(Constants.BOARD_GAME_CATEGORY)) {
            numPlayersTextView.setVisibility(View.VISIBLE);
            numPlayersEditText.setVisibility(View.GONE);
            timeGamePlayTextView.setVisibility(View.VISIBLE);
            timeGamePlayEditText.setVisibility(View.GONE);
        } else {
            genreTextView.setVisibility(View.VISIBLE);
            genreEditText.setVisibility(View.GONE);
            releaseYearTextView.setVisibility(View.VISIBLE);
            releaseYearEditText.setVisibility(View.GONE);
            itemFormatTextView.setVisibility(View.VISIBLE);
            itemFormatEditText.setVisibility(View.GONE);
            if (category.equals(Constants.BOOK_CATEGORY)) {
                authorTextView.setVisibility(View.VISIBLE);
                authorEditText.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onEditSuccess(EditItemResponse response) {
        System.out.println("Success saving item");
        Toast.makeText(this, "Item Saved!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onEditFailure(EditItemResponse response) {
        System.out.println("Failed to save item");
        Toast.makeText(this, "Failed To Save Item", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDeleteSuccess(DeleteItemResponse response) {
        System.out.println("Successfully deleted item");
        Toast.makeText(this, "Item Deleted!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDeleteFail(DeleteItemResponse response) {
        System.out.println("Failed to delete item");
        Toast.makeText(this, "Failed To Delete Item", Toast.LENGTH_LONG).show();
    }
}