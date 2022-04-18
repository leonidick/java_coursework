package backend.rest.service.repository;

import backend.rest.service.repository.entity.Warehouse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WarehouseRepository extends CrudRepository<Warehouse, Integer> {
     List<Warehouse> findByName(String name);

     @Query(value = "SELECT * FROM warehouses WHERE quantity > :quantity", nativeQuery = true)
     List<Warehouse> findWhereQuantityMoreThan(@Param("quantity") Integer quantity);

     @Query(value = "SELECT * FROM warehouses WHERE quantity < :quantity", nativeQuery = true)
     List<Warehouse> findWhereQuantityLessThan(@Param("quantity") Integer quantity);

     //получить позиции со склада которые были проданы больше раз чем quantity
     @Query(value = "SELECT DISTINCT warehouses.id, warehouses.name, warehouses.quantity, warehouses.amount FROM warehouses INNER JOIN sales ON warehouses.id = sales.warehouse_id WHERE sales.quantity > :quantity", nativeQuery = true)
     List<Warehouse> findWarehouseWhereSaleQuantityMoreThan(@Param("quantity") Integer quantity);

     //получить позиции со склада которые были проданы меньше раз чем quantity
     @Query(value = "SELECT DISTINCT warehouses.id, warehouses.name, warehouses.quantity, warehouses.amount FROM warehouses INNER JOIN sales ON warehouses.id = sales.warehouse_id WHERE sales.quantity < :quantity", nativeQuery = true)
     List<Warehouse> findWarehouseWhereSaleQuantityLessThan(@Param("quantity") Integer quantity);

     @Modifying
     @Transactional
     @Query(value = "DELETE FROM warehouses  WHERE name = :name")
     void deleteByName(@Param("name") String name);
}
