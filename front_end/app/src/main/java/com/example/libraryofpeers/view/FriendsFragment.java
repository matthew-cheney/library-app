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
import com.example.libraryofpeers.async_tasks.GetFriendsTask;
import com.example.libraryofpeers.async_tasks.PseudoFriendsTask;
import com.example.libraryofpeers.presenters.FriendsPresenter;
import com.example.libraryofpeers.presenters.GetFriendsPresenter;
import com.example.libraryofpeers.service_proxy.LoginServiceProxy;
import com.example.libraryofpeers.view.utils.UserClickListener;
import com.example.libraryofpeers.view.utils.SearchCache;

import java.util.ArrayList;
import java.util.List;

import Entities.User;
import Request.FriendsRequest;
import Response.FriendsResponse;

import static com.example.libraryofpeers.view.utils.ItemBindingUtils.bindUserToViews;

//import org.jetbrains.annotations.NotNull;

public class FriendsFragment extends Fragment implements FriendsPresenter.View {

    private static final String LOG_TAG = "FriendsFragment";
    private static final String ITEM_KEY = "ItemKey";

    private static final int LOADING_DATA_VIEW = 0;
    private static final int ITEM_VIEW = 1;

    private static final int PAGE_SIZE = 10;

    private String query;

    private User user;
    private GetFriendsPresenter presenter;

//    private int itemsLoaded = 0;

    private FriendsFragment.FriendsRecyclerViewAdapter FriendsRecyclerViewAdapter;

    public FriendsFragment(String query) {
        this.query = query;
    }

    public static FriendsFragment newInstance(User user) {
        return newInstance(user, "");
    }

    public static FriendsFragment newInstance(User user, String query) {

        FriendsFragment fragment = new FriendsFragment(query);

        Bundle args = new Bundle(2);
        args.putSerializable(ITEM_KEY, user);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendsitems, container, false);

        user = (User) getArguments().getSerializable(ITEM_KEY);

        presenter = new GetFriendsPresenter();

        RecyclerView FriendsRecyclerView = view.findViewById(R.id.statusesRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        FriendsRecyclerView.setLayoutManager(layoutManager);

        FriendsRecyclerViewAdapter = new FriendsFragment.FriendsRecyclerViewAdapter();
        FriendsRecyclerView.setAdapter(FriendsRecyclerViewAdapter);

        FriendsRecyclerView.addOnScrollListener(new FriendsFragment.FollowRecyclerViewPaginationScrollListener(layoutManager));

        return view;
    }

    private class FriendsHolder extends RecyclerView.ViewHolder {

//        private final ImageView itemImage;
//        private final TextView itemAlias;
//        private final TextView itemName;
//        private final TextView itemContent;
        private final TextView itemTitle;
        private final TextView itemCategory;
        private final ImageView itemImage;
        public User currentItem;
        private View itemView;

        FriendsHolder(@NonNull View itemView) {
            super(itemView);

//            itemName = itemView.findViewById(R.id.userName);
//            itemAlias = itemView.findViewById(R.id.userAlias);
            itemImage = itemView.findViewById(R.id.UserImage);
            itemCategory = itemView.findViewById(R.id.UserSubtitle);
            itemTitle = itemView.findViewById(R.id.UserTitle);
            this.itemView = itemView;
        }

        void bindItem(User user) {
            bindUserToViews(user, itemTitle, itemCategory, itemImage, getContext());
            currentItem = user;
            itemView.setOnClickListener(new UserClickListener(currentItem));
        }
    }

    private class FriendsRecyclerViewAdapter extends RecyclerView.Adapter<FriendsFragment.FriendsHolder> implements GetFriendsTask.GetFriendsObserver {

        private final List<User> items = new ArrayList<>();

        private User lastItem = new User(null, null, null, null, null, null, null, null, null);

        private boolean hasMorePages;
        private boolean isLoading = false;

        private int itemsLoaded = 0;

        FriendsRecyclerViewAdapter() {
            loadMoreItems();
        }

        void addItems(List<User> newItems) {
            int startInsertPosition = items.size();
            items.addAll(newItems);
            this.notifyItemRangeInserted(startInsertPosition, newItems.size());
        }

        void addItem(User item) {
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
        public FriendsFragment.FriendsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(FriendsFragment.this.getContext());
            View view;

            if(viewType == LOADING_DATA_VIEW) {
                view =layoutInflater.inflate(R.layout.loading_row, parent, false);

            } else {
                view = layoutInflater.inflate(R.layout.user_row, parent, false);
            }

            return new FriendsFragment.FriendsHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FriendsFragment.FriendsHolder FriendsHolder, int position) {
            if(!isLoading) {
                FriendsHolder.bindItem(items.get(position));
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

            if (SearchCache.getFriendsQuery().equals("")) {
                GetFriendsTask getFriendsTask = new GetFriendsTask(this, presenter);
                FriendsRequest request = new FriendsRequest(LoginServiceProxy.getInstance().getCurrentUser().getId(), itemsLoaded);
    //            if (lastItem != null) {
    //                request.setLastItemInFriendsId(lastItem.getId());
    //            }
                getFriendsTask.execute(request);
            } else {
                // This will be a search task once that's available
                Toast.makeText(getContext(), "Using search mode", Toast.LENGTH_LONG).show();
                GetFriendsTask getFriendsTask = new GetFriendsTask(this, presenter);
                FriendsRequest request = new FriendsRequest(LoginServiceProxy.getInstance().getCurrentUser().getId(), itemsLoaded);
                //            if (lastItem != null) {
                //                request.setLastItemInFriendsId(lastItem.getId());
                //            }
                getFriendsTask.execute(request);
            }
        }

        private void addLoadingFooter() {
            addItem(new User(null, null, null, null, null, null, null, null, null));
        }

        private void removeLoadingFooter() {
            removeItem(items.size() - 1);
        }

        @Override
        public void onGetFriendsSuccess(FriendsResponse response) {
            List<User> items = response.getFriends();

            lastItem = (items.size() > 0) ? items.get(items.size() -1) : null;
            hasMorePages = items.size() == 10;

            itemsLoaded += items.size();

            isLoading = false;
            removeLoadingFooter();
            FriendsRecyclerViewAdapter.addItems(items);
        }

        @Override
        public void onGetFriendsFail(FriendsResponse response) {
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

            if (!FriendsRecyclerViewAdapter.isLoading && FriendsRecyclerViewAdapter.hasMorePages) {
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {
                    FriendsRecyclerViewAdapter.loadMoreItems();
                }
            }
        }
    }
}
