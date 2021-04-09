package com.example.libraryofpeers.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryofpeers.R;
import com.example.libraryofpeers.async_tasks.CatalogTask;
import com.example.libraryofpeers.async_tasks.PseudoSearchTask;
import com.example.libraryofpeers.async_tasks.SearchItemsTask;
import com.example.libraryofpeers.presenters.CatalogPresenter;
import com.example.libraryofpeers.presenters.SearchItemsPresenter;
import com.example.libraryofpeers.service_proxy.LoginServiceProxy;
import com.example.libraryofpeers.view.utils.ItemClickListener;
import com.example.libraryofpeers.view.utils.SearchCache;

//import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import Entities.Item;
import Entities.User;
import Request.CatalogRequest;
import Request.SearchItemsRequest;
import Response.CatalogResponse;
import Response.SearchItemsResponse;

import static com.example.libraryofpeers.view.utils.ItemBindingUtils.bindItemToViews;

public class CatalogFragment extends Fragment implements CatalogPresenter.View {

    private static final String LOG_TAG = "CatalogFragment";
    private static final String ITEM_KEY = "ItemKey";

    private static final int LOADING_DATA_VIEW = 0;
    private static final int ITEM_VIEW = 1;

    private static final int PAGE_SIZE = 10;

    private String query;

    private User user;
    private CatalogPresenter presenter;
    private SearchItemsPresenter searchPresenter;

//    private int itemsLoaded = 0;

    private CatalogFragment.CatalogRecyclerViewAdapter CatalogRecyclerViewAdapter;

    public CatalogFragment(String query) {
        this.query = query;
    }

    public static CatalogFragment newInstance(User user) {
        return newInstance(user, "");
    }

    public static CatalogFragment newInstance(User user, String query) {

        CatalogFragment fragment = new CatalogFragment(query);

        Bundle args = new Bundle(2);
        args.putSerializable(ITEM_KEY, user);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalogitems, container, false);

        user = (User) getArguments().getSerializable(ITEM_KEY);

        presenter = new CatalogPresenter(this);
        searchPresenter = new SearchItemsPresenter();

        RecyclerView CatalogRecyclerView = view.findViewById(R.id.statusesRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        CatalogRecyclerView.setLayoutManager(layoutManager);

        CatalogRecyclerViewAdapter = new CatalogFragment.CatalogRecyclerViewAdapter();
        CatalogRecyclerView.setAdapter(CatalogRecyclerViewAdapter);

        CatalogRecyclerView.addOnScrollListener(new CatalogFragment.FollowRecyclerViewPaginationScrollListener(layoutManager));

        return view;
    }

    private class CatalogHolder extends RecyclerView.ViewHolder {

        //        private final ImageView itemImage;
//        private final TextView itemAlias;
//        private final TextView itemName;
//        private final TextView itemContent;
        private final TextView itemTitle;
        private final TextView itemCategory;
        private final ImageView itemImage;
        public Item currentItem;
        private View itemView;

        CatalogHolder(@NonNull View itemView) {
            super(itemView);

//            itemName = itemView.findViewById(R.id.userName);
//            itemAlias = itemView.findViewById(R.id.userAlias);
            itemImage = itemView.findViewById(R.id.ItemImage);
            itemCategory = itemView.findViewById(R.id.ItemSubtitle);
            itemTitle = itemView.findViewById(R.id.ItemTitle);
            this.itemView = itemView;
        }

        void bindItem(Item item) {
            bindItemToViews(item, itemTitle, itemCategory, itemImage, getContext());
            currentItem = item;
            itemView.setOnClickListener(new ItemClickListener(currentItem,  user.getId().equals(LoginServiceProxy.getInstance().getCurrentUser().getId())));
        }
    }

    private class CatalogRecyclerViewAdapter extends RecyclerView.Adapter<CatalogFragment.CatalogHolder> implements CatalogTask.Observer, SearchItemsTask.SearchItemsObserver {

        private final List<Item> items = new ArrayList<>();

        private Item lastItem = Item.getNullItem();

        private boolean hasMorePages;
        private boolean isLoading = false;

        private int itemsLoaded = 0;

        CatalogRecyclerViewAdapter() {
            loadMoreItems();
        }

        void addItems(List<Item> newItems) {
            int startInsertPosition = items.size();
            items.addAll(newItems);
            this.notifyItemRangeInserted(startInsertPosition, newItems.size());
        }

        void addItem(Item item) {
            if (item == null)
                return;
            items.add(item);
            this.notifyItemInserted(items.size() - 1);
        }

        void removeItem(int position) {
            items.remove(position);
            this.notifyItemRemoved(position);
        }

        @NonNull
        @Override
        public CatalogFragment.CatalogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(CatalogFragment.this.getContext());
            View view;

            if(viewType == LOADING_DATA_VIEW) {
                view =layoutInflater.inflate(R.layout.loading_row, parent, false);

            } else {
                view = layoutInflater.inflate(R.layout.item_row, parent, false);
            }

            return new CatalogFragment.CatalogHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CatalogFragment.CatalogHolder CatalogHolder, int position) {
            if(!isLoading) {
                CatalogHolder.bindItem(items.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        @Override
        public int getItemViewType(int position) {
            return (position == items.size() - 1 && isLoading) ? LOADING_DATA_VIEW : ITEM_VIEW;
        }

        void loadMoreItems() {
            isLoading = true;
            addLoadingFooter();

            if (SearchCache.getCatalogQuery().equals("") || SearchCache.getCategoryFilter() != null) {
//                Toast.makeText(getContext(), SearchCache.getCategoryFilter(), Toast.LENGTH_SHORT).show();
                CatalogTask getCatalogTask = new CatalogTask(presenter, this);
                CatalogRequest request = new CatalogRequest(user.getId(), SearchCache.getCategoryFilter(), itemsLoaded);  // Eventually this will track how many items loaded so far
                //            if (lastItem != null) {
                //                request.setLastItemInCatalogId(lastItem.getId());
                //            }
                getCatalogTask.execute(request);
            } else {
                // This will be a search task once that's available
//                Toast.makeText(getContext(), "Using search mode", Toast.LENGTH_LONG).show();
                SearchItemsTask searchTask = new SearchItemsTask(this, searchPresenter);
                SearchItemsRequest request = new SearchItemsRequest(user.getId(), SearchCache.getCatalogQuery(), itemsLoaded);  // Eventually this will track how many items loaded so far
                //            if (lastItem != null) {
                //                request.setLastItemInCatalogId(lastItem.getId());
                //            }
                searchTask.execute(request);
            }
        }

        @Override
        public void catalogRetrieved(CatalogResponse CatalogResponse) {
            List<Item> items = CatalogResponse.getItems();

            lastItem = (items.size() > 0) ? items.get(items.size() -1) : null;
            hasMorePages = items.size() == 10;

            itemsLoaded += items.size();

            isLoading = false;
            removeLoadingFooter();
            CatalogRecyclerViewAdapter.addItems(items);
        }

        @Override
        public void handleException(Exception exception) {
            Log.e(LOG_TAG, exception.getMessage(), exception);
            removeLoadingFooter();
            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
        }

        private void addLoadingFooter() {
            addItem(Item.getNullItem());
        }

        private void removeLoadingFooter() {
            removeItem(items.size() - 1);
        }

        @Override
        public void onSearchSuccess(SearchItemsResponse response) {
            List<Item> items = response.getItems();

            lastItem = (items.size() > 0) ? items.get(items.size() -1) : null;
            hasMorePages = items.size() == 10;

            itemsLoaded += items.size();

            isLoading = false;
            removeLoadingFooter();
            CatalogRecyclerViewAdapter.addItems(items);

        }

        @Override
        public void onSearchFail(SearchItemsResponse response) {
            removeLoadingFooter();
            Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private class FollowRecyclerViewPaginationScrollListener extends RecyclerView.OnScrollListener {

        private final LinearLayoutManager layoutManager;

        FollowRecyclerViewPaginationScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!CatalogRecyclerViewAdapter.isLoading && CatalogRecyclerViewAdapter.hasMorePages) {
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {
                    CatalogRecyclerViewAdapter.loadMoreItems();
                }
            }
        }
    }
}
