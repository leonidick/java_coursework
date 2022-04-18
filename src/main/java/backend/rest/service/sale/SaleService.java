package backend.rest.service.sale;

import backend.rest.service.repository.entity.Sale;

import java.util.List;

public interface SaleService {
    Sale addSale(Sale sale);

    List<Sale> getByWarehouseName(String name);
    List<Sale> getWhereQuantityMoreThan(Integer quantity);
    List<Sale> getWhereQuantityLessThan(Integer quantity);

    Sale getSaleById(Integer id);
    List<Sale> listSale();

    void deleteAllSale();
    void deleteSaleById(Integer id);
    void deleteSaleByWarehouseName(String name);
}
