package DataAccess.Lambda;

import DataAccess.Request.CatalogRequest;
import DataAccess.Response.CatalogResponse;
import DataAccess.Service.GetCatalogService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetCatalogHandler implements RequestHandler<CatalogRequest, CatalogResponse> {
    @Override
    public CatalogResponse handleRequest(CatalogRequest request, Context context) {
        GetCatalogService service = new GetCatalogService();
        return service.getCatalog(request);
    }
}