package com.example.libraryofpeers.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.libraryofpeers.R;


public class FriendProfileFragment extends Fragment {
    private SectionsPagerAdapter sectionsPagerAdapter;

    ImageView friendToggleBtn;

    public FriendProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_profile, container, false);

        friendToggleBtn = (ImageView) view.findViewById(R.id.toggle_friend_btn);
        friendToggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Toggle Friends");
                friendToggleBtn.setActivated(!friendToggleBtn.isActivated());
            }
        });

        return view;
    }
}