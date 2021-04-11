package com.example.libraryofpeers.view;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;

import Entities.User;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView.OnQueryTextListener;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.service_proxy.LoginServiceProxy;
import com.example.libraryofpeers.view.utils.SearchCache;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import Config.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SectionsPagerAdapter sectionsPagerAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private User loggedInUser;

    private boolean firstLoad;

    public HomeFragment() {
        // Required empty public constructor
        firstLoad = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    @Override
    public void onResume() {
        super.onResume();
        SearchCache.setCatalogQuery("");
        SearchCache.setCategoryFilter(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        loggedInUser = LoginServiceProxy.getInstance().getCurrentUser();

        FloatingActionButton addItem = (FloatingActionButton) view.findViewById(R.id.addItemButton);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddItem();
            }
        });

        return view;
    }

    public void AddItem() {
        System.out.println("Adding an item");
        Intent intent = new Intent(getActivity(), NewItemActivity.class);
        startActivity(intent);
    }

    private SectionsPagerAdapter getSectionsPagerAdapter() {
        return new SectionsPagerAdapter(getContext(), this.getChildFragmentManager(), loggedInUser, SectionsPagerAdapter.DEFAULT_TAB_TITLES);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sectionsPagerAdapter = getSectionsPagerAdapter();
        ViewPager viewPager = getView().findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = getView().findViewById(R.id.tabs);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    view.findViewById(R.id.category_toolbar).setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.category_toolbar).setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabs.setupWithViewPager(viewPager);

        final SearchView search = getView().findViewById(R.id.catalog_search);

        search.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("Searching catalog with query: " + query);
                SearchCache.setCatalogQuery(query);
                sectionsPagerAdapter.notifyDataSetChanged();
                ViewPager viewPager = getView().findViewById(R.id.view_pager);
                viewPager.setAdapter(sectionsPagerAdapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")) {
//                    Toast.makeText(getContext(), "it's empty!", Toast.LENGTH_LONG).show();
                    System.out.println("Resetting catalog to no search");
                    SearchCache.setCatalogQuery("");
                    sectionsPagerAdapter.notifyDataSetChanged();
                    ViewPager viewPager = getView().findViewById(R.id.view_pager);
                    viewPager.setAdapter(sectionsPagerAdapter);
                }
                return false;
            }
        });

        Spinner categorySpinner = getView().findViewById(R.id.category_dropdown);
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("All");
        categoryNames.add("Book");
        categoryNames.add("Movie");
        categoryNames.add("Game");

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getActivity(), R.layout.new_item_selected, categoryNames);
        categoryAdapter.setDropDownViewResource(R.layout.new_item_dropdown_category);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ViewPager viewPager = getView().findViewById(R.id.view_pager);
                switch (i) {
                    case 0:
                        if (firstLoad) {
                            firstLoad = false;
                            break;
                        }
                        SearchCache.setCategoryFilter(null);
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        getActivity().findViewById(R.id.catalog_search).setVisibility(View.VISIBLE);
                        break;
                    case 1:
//                        ((SearchView) getActivity().findViewById(R.id.catalog_search)).setQuery("", false);
//                        SearchCache.setCatalogQuery("");
                        SearchCache.setCategoryFilter(Constants.BOOK_CATEGORY);
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        getActivity().findViewById(R.id.catalog_search).setVisibility(View.GONE);
                        break;
                    case 2:
//                        ((SearchView) getActivity().findViewById(R.id.catalog_search)).setQuery("", false);
//                        SearchCache.setCatalogQuery("");
                        SearchCache.setCategoryFilter(Constants.MOVIE_CATEGORY);
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        getActivity().findViewById(R.id.catalog_search).setVisibility(View.GONE);
                        break;
                    case 3:
//                        ((SearchView) getActivity().findViewById(R.id.catalog_search)).setQuery("", false);
//                        SearchCache.setCatalogQuery("");
                        SearchCache.setCategoryFilter(Constants.BOARD_GAME_CATEGORY);
                        viewPager.setAdapter(getSectionsPagerAdapter());
                        getActivity().findViewById(R.id.catalog_search).setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
