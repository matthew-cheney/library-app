package com.example.libraryofpeers.view.utils;

public class SearchCache {

    private static SearchCache cache;

    public String catalogQuery;
    public String friendsQuery;

    private SearchCache() {
        this.catalogQuery = "";
        this.friendsQuery = "";
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


}
