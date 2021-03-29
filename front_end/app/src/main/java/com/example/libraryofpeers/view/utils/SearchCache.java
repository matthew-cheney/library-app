package com.example.libraryofpeers.view.utils;

public class SearchCache {

    private static SearchCache cache;

    public String catalogQuery;
    public String friendsQuery;
    public String categoryFilter;

    private SearchCache() {
        this.catalogQuery = "";
        this.friendsQuery = "";
        this.categoryFilter = null;
    }

    public static SearchCache getInstance() {
        if (cache == null) {
            cache = new SearchCache();
        }
        return cache;
    }

    public static void setCatalogQuery(String catalogQuery) {
        getInstance().catalogQuery = catalogQuery;
    }

    public static String getCatalogQuery() {
        return getInstance().catalogQuery;
    }

    public static void setFriendsQuery(String friendsQuery) {
        getInstance().friendsQuery = friendsQuery;
    }

    public static String getFriendsQuery() {
        return getInstance().friendsQuery;
    }

    public static void setCategoryFilter(String categoryFilter) {
        getInstance().categoryFilter = categoryFilter;
    }

    public static String getCategoryFilter() {
        return getInstance().categoryFilter;
    }


}
