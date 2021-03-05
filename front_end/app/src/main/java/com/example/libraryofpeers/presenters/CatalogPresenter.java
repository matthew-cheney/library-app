package com.example.libraryofpeers.presenters;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.libraryofpeers.service_proxy.CatalogServiceProxy;

import java.io.IOException;

import Request.CatalogRequest;
import Response.CatalogResponse;

public class CatalogPresenter {

    private final CatalogPresenter.View view;

    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public CatalogPresenter(CatalogPresenter.View view) {
        this.view = view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CatalogResponse getCatalog(CatalogRequest request) throws IOException {
        CatalogServiceProxy CatalogService = getCatalogService();
        CatalogResponse response = CatalogService.getCatalog(request);
        return response;
    }

    CatalogServiceProxy getCatalogService() {
        return new CatalogServiceProxy();
    }
}
