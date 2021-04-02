package com.example.libraryofpeers.view;

import android.content.Intent;
import android.os.Bundle;

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

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.async_tasks.AddItemTask;
import com.example.libraryofpeers.presenters.AddItemPresenter;
import com.example.libraryofpeers.service_proxy.LoginServiceProxy;

public class AddGameItemFragment extends Fragment implements AddItemTask.AddItemObserver {
    View view;
    EditText titleEditText;
    EditText numPlayersEditText;
    EditText timeGamePlayEditText;
    EditText descriptionEditText;
    EditText imageUrlEditText;
    String BOARD_GAME_CATEGORY = "BOARD_GAME";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_add_game_item, container, false);


        final Button addItemBtn = (Button) view.findViewById(R.id.addItemBtn);
        titleEditText = (EditText) view.findViewById(R.id.titleEditor);
        numPlayersEditText = (EditText) view.findViewById(R.id.numPlayersEditor);
        timeGamePlayEditText = (EditText) view.findViewById(R.id.timeEditor);
        descriptionEditText = (EditText) view.findViewById(R.id.descriptionEditor);
        imageUrlEditText = (EditText) view.findViewById(R.id.imageEditor);

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
        String numPlayersString = numPlayersEditText.getText().toString();
        String timeGamePlayString = timeGamePlayEditText.getText().toString();
        Integer numPlayers;
        Integer timeGamePlay;

        if (title.isEmpty()) {
            Toast.makeText(getActivity(), "Title is required!", Toast.LENGTH_LONG).show();
        } else {
            imageUrl = (!imageUrl.isEmpty()) ? imageUrl : null;
            description = (!description.isEmpty()) ? description : null;
            numPlayers = (!numPlayersString.isEmpty()) ? null : Integer.parseInt(numPlayersString);
            timeGamePlay = (!timeGamePlayString.isEmpty()) ? null : Integer.parseInt(timeGamePlayString);

            AddItemRequest addItemRequest = new AddItemRequest(
                    titleEditText.getText().toString(),
                    BOARD_GAME_CATEGORY,
                    false,
                    user.getId(),
                    imageUrl,
                    description,
                    numPlayers,
                    timeGamePlay
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
}