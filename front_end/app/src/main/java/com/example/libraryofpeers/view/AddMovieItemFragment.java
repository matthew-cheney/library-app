package com.example.libraryofpeers.view;

import android.os.Bundle;

import Entities.User;
import Request.AddItemRequest;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.service_proxy.LoginServiceProxy;

public class AddMovieItemFragment extends Fragment {
    View view;
    EditText titleEditText;
    EditText genreEditText;
    EditText releaseYearEditText;
    EditText descriptionEditText;
    EditText imageUrlEditText;
    EditText itemFormatEditText;
    String MOVIE_CATEGORY = "MOVIE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_movie_item, container, false);

        final Button addItemBtn = (Button) view.findViewById(R.id.addItemBtn);
        titleEditText = (EditText) view.findViewById(R.id.titleEditor);
        genreEditText = (EditText) view.findViewById(R.id.genreEditor);
        releaseYearEditText = (EditText) view.findViewById(R.id.releaseYearEditor);
        descriptionEditText = (EditText) view.findViewById(R.id.descriptionEditor);
        imageUrlEditText = (EditText) view.findViewById(R.id.imageEditor);
        itemFormatEditText = (EditText) view.findViewById(R.id.itemFormatEditor);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        return view;
    }

    private void addItem() {
        User user = LoginServiceProxy.getInstance().getCurrentUser();
        String title = titleEditText.getText().toString();
        String imageUrl = imageUrlEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String releaseYeaString = releaseYearEditText.getText().toString();
        String genre = genreEditText.getText().toString();
        String itemFormat = itemFormatEditText.getText().toString();
        Integer releaseYear;


        if (title.isEmpty()) {
            Toast.makeText(getActivity(), "Title is required!", Toast.LENGTH_LONG).show();
        } else {
            imageUrl = (imageUrl.isEmpty()) ? imageUrl : null;
            description = (description.isEmpty()) ? description : null;
            genre = (genre.isEmpty()) ? genre : null;
            itemFormat = (itemFormat.isEmpty()) ? itemFormat : null;
            releaseYear = (releaseYeaString.isEmpty()) ? null : Integer.parseInt(releaseYeaString);


            AddItemRequest addItemRequest = new AddItemRequest(
                    titleEditText.getText().toString(),
                    MOVIE_CATEGORY,
                    false,
                    user.getId(),
                    imageUrl,
                    description,
                    releaseYear,
                    genre,
                    itemFormat
            );


        }
    }
}