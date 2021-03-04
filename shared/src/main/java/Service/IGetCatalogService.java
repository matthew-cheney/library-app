package Service;

import Request.CatalogRequest;
import Response.CatalogResponse;

public interface IGetCatalogService {
    CatalogResponse getCatalog(CatalogRequest request);
}
