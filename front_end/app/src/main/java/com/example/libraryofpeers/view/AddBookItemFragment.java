package com.example.libraryofpeers.view;

import android.content.Intent;
import android.os.Bundle;

import Config.Constants;
import Entities.User;
import Request.AddItemRequest;
import Response.AddItemResponse;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libraryofpeers.BarcodeScanner;
import com.example.libraryofpeers.R;
import com.example.libraryofpeers.async_tasks.AddItemTask;
import com.example.libraryofpeers.async_tasks.QueryAPITask;
import com.example.libraryofpeers.presenters.AddItemPresenter;
import com.example.libraryofpeers.service_proxy.LoginServiceProxy;
import com.example.libraryofpeers.view.utils.APICommunicator;

public class AddBookItemFragment extends Fragment implements AddItemTask.AddItemObserver, QueryAPITask.QueryAPIObserver {
    View view;
    EditText titleEditText;
    EditText authorEditText;
    EditText genreEditText;
    EditText releaseYearEditText;
    EditText descriptionEditText;
    EditText imageUrlEditText;
    EditText itemFormatEditText;
    Button scanButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_book_item, container, false);

        final Button addItemBtn = (Button) view.findViewById(R.id.addItemBtn);
        titleEditText = (EditText) view.findViewById(R.id.titleEditor);
        authorEditText = (EditText) view.findViewById(R.id.authorEditor);
        genreEditText = (EditText) view.findViewById(R.id.genreEditor);
        releaseYearEditText = (EditText) view.findViewById(R.id.releaseYearEditor);
        descriptionEditText = (EditText) view.findViewById(R.id.descriptionEditor);
        imageUrlEditText = (EditText) view.findViewById(R.id.imageEditor);
        itemFormatEditText = (EditText) view.findViewById(R.id.itemFormatEditor);

        final Button addImageBtn = (Button) view.findViewById(R.id.addBookImageButton);
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartImageSelectorActivity();
            }
        });

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        scanButton = view.findViewById(R.id.scan_barcode_button);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BarcodeScanner.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, Constants.BARCODE_CODE);
            }
        });


        return view;
    }

    private void addItem() {
        User user = LoginServiceProxy.getInstance().getCurrentUser();
        String title = titleEditText.getText().toString();
        String imageUrl = imageUrlEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String releaseYearString = releaseYearEditText.getText().toString();
        String genre = genreEditText.getText().toString();
        String itemFormat = itemFormatEditText.getText().toString();
        String author = authorEditText.getText().toString();
        Integer releaseYear;

        if (title.isEmpty()) {
            Toast.makeText(getActivity(), "Title is required!", Toast.LENGTH_LONG).show();
        } else {
            imageUrl = (imageUrl.isEmpty()) ? null : imageUrl;
            description = (description.isEmpty()) ? null : description;
            genre = (genre.isEmpty()) ? null : genre;
            itemFormat = (itemFormat.isEmpty()) ? null : itemFormat;
            author = (author.isEmpty()) ? null : author;
            releaseYear = (releaseYearString.isEmpty()) ? null : Integer.parseInt(releaseYearString);

            AddItemRequest addItemRequest = new AddItemRequest(
                    titleEditText.getText().toString(),
                    Constants.BOOK_CATEGORY,
                    false,
                    user.getId(),
                    imageUrl,
                    description,
                    releaseYear,
                    genre,
                    itemFormat,
                    author
            );

            AddItemPresenter presenter = new AddItemPresenter();
            AddItemTask task = new AddItemTask(this, presenter);
            task.execute(addItemRequest);
        }
    }

    @Override
    public void onAddSuccess(AddItemResponse response) {
        System.out.println("Success adding item");
        Toast.makeText(getActivity(), "Item Added!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAddFailure(AddItemResponse response) {
        System.out.println("Failed to add item");
        Toast.makeText(getActivity(), "Failed To Add Item", Toast.LENGTH_LONG).show();
    }

    public void StartImageSelectorActivity() {
        Intent intent = new Intent(this.getActivity(), ImageSelectorActivity.class);
        startActivityForResult(intent, Constants.IMAGE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (Constants.BARCODE_CODE) : {
                if (resultCode == BarcodeScanner.RESULT_OK) {
                    // TODO Extract the data returned from the child Activity.
                    String isbn = data.getStringExtra("isbn");
                    QueryAPITask task = new QueryAPITask(this);
                    task.execute(isbn);
                }
                break;
            }
            case Constants.IMAGE_CODE:
                imageUrlEditText.setText(data.getStringExtra(Constants.IMAGE_URL_EXTRA));
                break;
        }
    }

    @Override
    public void onQuerySuccess(APICommunicator.BookResult bookResult) {
        if (!bookResult.any()) {
            Toast.makeText(getContext(), "book not found", Toast.LENGTH_SHORT).show();
            return;
        }
        if (bookResult.getTitle() != null) {
            titleEditText.setText(bookResult.getTitle());
        }
        if (bookResult.getAuthor() != null) {
            authorEditText.setText(bookResult.getAuthor());
        }
        if (bookResult.getPublishDate() != null) {
            releaseYearEditText.setText(bookResult.getPublishDate());
        }
        if (bookResult.getCoverUrl() != null) {
            imageUrlEditText.setText(bookResult.getCoverUrl());
        }
    }

    @Override
    public void onQueryFailure(APICommunicator.BookResult response) {
        Toast.makeText(getContext(), "unable to find book", Toast.LENGTH_SHORT).show();
    }
}