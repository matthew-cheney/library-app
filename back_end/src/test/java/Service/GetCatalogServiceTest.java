package Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import DataAccess.DAO.MySql.MySqlItemDAO;
import Request.CatalogRequest;
import Response.CatalogResponse;
import TestUtils.BaseTest;
import TestUtils.DatabaseFiller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetCatalogServiceTest extends BaseTest {

    private GetCatalogService service;
    private MySqlItemDAO dao;
    private CatalogRequest successfulRequest = new CatalogRequest("TEST", 3);
    private CatalogRequest failureRequest = new CatalogRequest("BUBBLES", 0);

    @BeforeEach
    public void setUpTests() {
        dao = Mockito.spy(MySqlItemDAO.class);
        Mockito.when(dao.getConnectionPool()).thenReturn(CONNECTION_POOL);

        service = Mockito.spy(GetCatalogService.class);
        Mockito.when(service.getItemDAO()).thenReturn(dao);

        DatabaseFiller.addUnitTestItems("TEST", 0, 25, dao);
    }

    @AfterEach
    public void tearDownTests() {
        dao.clearItemsTable();
    }

    @Test
    public void getCatalog_success() {
        CatalogResponse response = service.getCatalog(successfulRequest);
        assertTrue(response.isSuccess());
        assertEquals(10, response.getItems().size());
    }

    @Test
    public void getCatalog_failure() {
        CatalogResponse response = service.getCatalog(failureRequest);
        assertEquals(0, response.getItems().size());
    }
}
