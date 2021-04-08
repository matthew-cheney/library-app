package com.example.libraryofpeers.view;

import Entities.Friendship;
import Entities.User;
import Request.AddFriendRequest;
import Request.RemoveFriendRequest;
import Response.AddFriendResponse;
import Response.RemoveFriendResponse;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.async_tasks.AddFriendTask;
import com.example.libraryofpeers.async_tasks.RemoveFriendTask;
import com.example.libraryofpeers.presenters.AddFriendPresenter;
import com.example.libraryofpeers.presenters.RemoveFriendPresenter;
import com.example.libraryofpeers.service_proxy.LoginServiceProxy;
import com.example.libraryofpeers.view.utils.SearchCache;

import java.util.ArrayList;
import java.util.List;

public class FriendProfileActivity extends AppCompatActivity implements AddFriendTask.AddFriendObserver, RemoveFriendTask.RemoveFriendObserver {

    ImageView friendToggleBtn;
    ImageView returnHomeBtn;
    TextView userNameTitle;
    User userFriend;
    SectionsPagerAdapter sectionsPagerAdapter;
    private boolean firstLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        userFriend = (User) getIntent().getSerializableExtra("user");
        firstLoad = true;

        returnHomeBtn = (ImageView) findViewById(R.id.returnHomeArrow);
        friendToggleBtn = (ImageView) findViewById(R.id.toggle_friend_btn);
        userNameTitle = (TextView) findViewById(R.id.userNameTitle);
        
        String fullname = userFriend.getFirstName() + " " + userFriend.getLastName();
        userNameTitle.setText(fullname);

        returnHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnHome();
            }
        });

        friendToggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFriendship();
            }
        });

        sectionsPagerAdapter = getSectionsPagerAdapter();
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        Spinner categorySpinner = findViewById(R.id.category_dropdown);
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("All");
        categoryNames.add("Book");
        categoryNames.add("Movie");
        categoryNames.add("Game");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, R.layout.new_item_selected, categoryNames);
        categoryAdapter.setDropDownViewResource(R.layout.new_item_dropdown_category);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ViewPager viewPager = findViewById(R.id.view_pager);
                switch (i) {
                    case 0:
                        if (firstLoad) {
                            firstLoad = false;
                            break;
                        }
                        SearchCache.setCategoryFilter(null);
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        findViewById(R.id.catalog_search).setVisibility(View.VISIBLE);
                        break;
                    case 1:
//                        ((SearchView) getActivity().findViewById(R.id.catalog_search)).setQuery("", false);
//                        SearchCache.setCatalogQuery("");
                        SearchCache.setCategoryFilter("book");
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        findViewById(R.id.catalog_search).setVisibility(View.GONE);
                        break;
                    case 2:
//                        ((SearchView) getActivity().findViewById(R.id.catalog_search)).setQuery("", false);
//                        SearchCache.setCatalogQuery("");
                        SearchCache.setCategoryFilter("movie");
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        findViewById(R.id.catalog_search).setVisibility(View.GONE);
                        break;
                    case 3:
//                        ((SearchView) getActivity().findViewById(R.id.catalog_search)).setQuery("", false);
//                        SearchCache.setCatalogQuery("");
                        SearchCache.setCategoryFilter("board_game");
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        findViewById(R.id.catalog_search).setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private SectionsPagerAdapter getSectionsPagerAdapter() {
        return new SectionsPagerAdapter(this, this.getSupportFragmentManager(), userFriend, SectionsPagerAdapter.DEFAULT_TAB_TITLES);
    }

    private void returnHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void toggleFriendship() {
        System.out.println("Toggle Friends");
        User curUser = LoginServiceProxy.getInstance().getCurrentUser();
        Friendship friendship = new Friendship(curUser.getId(), userFriend.getId());
        if (friendToggleBtn.isActivated()) {
            System.out.println("Trying to become friends!");
            AddFriendRequest request = new AddFriendRequest(friendship);
            AddFriendPresenter presenter = new AddFriendPresenter();
            AddFriendTask task = new AddFriendTask(this, presenter);
            task.execute(request);
        } else {
            System.out.println("Removing our friendship.");
            RemoveFriendRequest request = new RemoveFriendRequest(friendship);
            RemoveFriendPresenter presenter = new RemoveFriendPresenter();
            RemoveFriendTask task = new RemoveFriendTask(this, presenter);
            task.execute(request);
        }
    }

    @Override
    public void onAddFriendSuccess(AddFriendResponse response) {
        friendToggleBtn.setActivated(false);
        Toast.makeText(this, "You are now friends!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddFriendFailure(AddFriendResponse response) {
        Toast.makeText(this, "Error adding Friend.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRemoveFriendSuccess(RemoveFriendResponse response) {
        friendToggleBtn.setActivated(true);
        Toast.makeText(this, "Friend removed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRemoveFriendFailure(RemoveFriendResponse response) {
        Toast.makeText(this, "Error removing Friend.", Toast.LENGTH_LONG).show();
    }
}