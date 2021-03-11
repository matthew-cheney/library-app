package com.example.libraryofpeers.service_proxy;

import java.util.ArrayList;
import java.util.List;

import Entities.Item;
import Request.CatalogRequest;
import Response.CatalogResponse;
import Service.IGetCatalogService;

public class CatalogServiceProxy implements IGetCatalogService {
    @Override
    public CatalogResponse getCatalog(CatalogRequest request) {
        return new CatalogResponse(getRandomItems(), true);
    }

    private List<Item> getRandomItems() {
        List<Item> resList = new ArrayList<>();
        resList.add(new Item("42", "War and Peace", "book", "Mar 4 2021", true, "_matvei", "https://image.flaticon.com/icons/png/512/130/130304.png", "An exciting Russian book", 0, 0, 1850, "literature", "print", "Leo Tolstoy"));
        resList.add(new Item("43", "Anna Karenina", "book", "Mar 11 2021", true, "_matvei", "https://image.flaticon.com/icons/png/512/130/130304.png", "An exciting Russian book", 0, 0, 1850, "literature", "print", "Leo Tolstoy"));
        return resList;
        }
}
