package backend.rest.service.warehouse;

import backend.rest.service.repository.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse addWarehouse(Warehouse warehouse);

    List<Warehouse> findWarehouseByName(String name);
    List<Warehouse> findWhereQuantityMoreThan(Integer quantity);
    List<Warehouse> findWhereQuantityLessThan(Integer quantity);

    List<Warehouse> findWarehouseWhereSaleQuantityMoreThan(Integer quantity);
    List<Warehouse> findWarehouseWhereSaleQuantityLessThan(Integer quantity);

    Warehouse getWarehouseById(Integer id);
    List<Warehouse> listWarehouse();

    void deleteWarehouseById(Integer id);
    void deleteWarehouseByName(String name);
    void deleteAllWarehouse();
}
