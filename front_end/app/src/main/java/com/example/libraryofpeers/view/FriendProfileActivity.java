package com.example.libraryofpeers.view;

import Config.Constants;
import Entities.User;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.view.utils.SearchCache;

import java.util.ArrayList;
import java.util.List;

public class FriendProfileActivity extends AppCompatActivity {

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

        final SearchView search = findViewById(R.id.catalog_search_friend);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("Searching catalog with query: " + query);
                SearchCache.setFriendCatalogQuery(query);
                sectionsPagerAdapter.notifyDataSetChanged();
                ViewPager viewPager = findViewById(R.id.view_pager);
                viewPager.setAdapter(sectionsPagerAdapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
//                    Toast.makeText(getContext(), "it's empty!", Toast.LENGTH_LONG).show();
                    System.out.println("Resetting catalog to no search");
                    SearchCache.setFriendCatalogQuery("");
                    sectionsPagerAdapter.notifyDataSetChanged();
                    ViewPager viewPager = findViewById(R.id.view_pager);
                    viewPager.setAdapter(sectionsPagerAdapter);
                }
                return false;
            }
        });

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
                        SearchCache.setFriendCategoryFilter(null);
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        findViewById(R.id.catalog_search_friend).setVisibility(View.VISIBLE);
                        break;
                    case 1:
//                        ((SearchView) getActivity().findViewById(R.id.catalog_search)).setQuery("", false);
//                        SearchCache.setCatalogQuery("");
                        SearchCache.setFriendCategoryFilter(Constants.BOOK_CATEGORY);
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        findViewById(R.id.catalog_search_friend).setVisibility(View.GONE);
                        break;
                    case 2:
//                        ((SearchView) getActivity().findViewById(R.id.catalog_search)).setQuery("", false);
//                        SearchCache.setCatalogQuery("");
                        SearchCache.setFriendCategoryFilter(Constants.MOVIE_CATEGORY);
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        findViewById(R.id.catalog_search_friend).setVisibility(View.GONE);
                        break;
                    case 3:
//                        ((SearchView) getActivity().findViewById(R.id.catalog_search)).setQuery("", false);
//                        SearchCache.setCatalogQuery("");
                        SearchCache.setFriendCategoryFilter(Constants.BOARD_GAME_CATEGORY);
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        findViewById(R.id.catalog_search_friend).setVisibility(View.GONE);
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
        if (friendToggleBtn.isActivated()) {
            System.out.println("We are now friends!");
            friendToggleBtn.setActivated(false);
            Toast.makeText(this, "You are now friends!", Toast.LENGTH_LONG).show();
        } else {
            System.out.println("Sorry, we are not friends any more.");
            friendToggleBtn.setActivated(true);
            Toast.makeText(this, "Friend removed", Toast.LENGTH_LONG).show();
        }
    }
}