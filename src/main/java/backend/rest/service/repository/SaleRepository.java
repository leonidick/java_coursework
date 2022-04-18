package backend.rest.service.repository;

import backend.rest.service.repository.entity.Sale;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Integer> {
    @Query(value = "FROM sales a JOIN a.warehouse c WHERE c.name = :name")
    List<Sale> findByWarehouseName(@Param("name") String name);

    @Query(value = "SELECT * FROM sales WHERE quantity < :quantity", nativeQuery = true)
    List<Sale> getSalesWhereQuantityLowerThan(@Param("quantity") Integer quantity);

    @Query(value = "SELECT * FROM sales WHERE quantity > :quantity", nativeQuery = true)
    List<Sale> getSalesWhereQuantityGreaterThan(@Param("quantity") Integer quantity);

    @Modifying
    @Transactional
    @Query(value = "DELETE sales FROM sales INNER JOIN warehouses ON sales.warehouse_id = warehouses.id WHERE warehouses.name = :name", nativeQuery = true)
    void deleteSaleByName(@Param("name") String name);
}
