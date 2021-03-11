package com.example.libraryofpeers.presenters;

import com.example.libraryofpeers.service_proxy.GetCatalogServiceProxy;

import Request.CatalogRequest;
import Response.CatalogResponse;
import Service.IGetCatalogService;

public class GetCatalogPresenter {
    public CatalogResponse getCatalog(CatalogRequest request) {
        IGetCatalogService service = new GetCatalogServiceProxy();
        return service.getCatalog(request);
    }
}
