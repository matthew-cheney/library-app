package com.example.libraryofpeers.service_proxy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.net.ServerFacade;

import java.io.IOException;

import Request.CatalogRequest;
import Response.CatalogResponse;
import Service.IGetCatalogService;

public class GetCatalogServiceProxy implements IGetCatalogService {
    private ServerFacade serverFacade;
    private static String urlpath = "/catalog";

    public GetCatalogServiceProxy() {
        serverFacade = new ServerFacade();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public CatalogResponse getCatalog(CatalogRequest request) {
        try {
            return serverFacade.getCatalog(request, urlpath);
        } catch(IOException e) {
            return new CatalogResponse(false, e.getMessage());
        }
    }
}
