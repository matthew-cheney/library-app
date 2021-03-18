package com.example.libraryofpeers.view.utils;

public class SearchCache {

    private static SearchCache cache;

    public String catalogQuery;

    private SearchCache() {
        this.catalogQuery = "";
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


}
