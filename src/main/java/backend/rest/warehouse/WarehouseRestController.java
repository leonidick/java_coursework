package backend.rest.warehouse;

import backend.rest.service.repository.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.rest.service.warehouse.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseRestController {
    @Autowired
    private WarehouseService warehouseService;
    public WarehouseService getWarehouseService() {
        return  this.warehouseService;
    }

    @PostMapping("/add")
    public ResponseEntity<Warehouse> addWarehouse(@RequestBody WarehouseJsonHandler warehouseJsonHandler) {
        Warehouse warehouse = new Warehouse(warehouseJsonHandler.name,
                                            warehouseJsonHandler.quantity,
                                            warehouseJsonHandler.amount);
        return new ResponseEntity<>(warehouseService.addWarehouse(warehouse), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Warehouse>> getAllWarehouse() {
        return new ResponseEntity<>(warehouseService.listWarehouse(), HttpStatus.OK);
    }
    @GetMapping("/geti-{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(warehouseService.getWarehouseById(id), HttpStatus.OK);
    }
    @GetMapping("/getn-{name}")
    public ResponseEntity<List<Warehouse>> getWarehouseByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(warehouseService.findWarehouseByName(name), HttpStatus.OK);
    }
    @GetMapping("/get/where_quantity_more-{quantity}")
    public ResponseEntity<List<Warehouse>> getWarehouseWhereQuantityMoreThan(@PathVariable("quantity") Integer quantity) {
        return new ResponseEntity<>(warehouseService.findWhereQuantityMoreThan(quantity), HttpStatus.OK);
    }
    @GetMapping("/get/where_quantity_less-{quantity}")
    public ResponseEntity<List<Warehouse>> getWarehouseWhereQuantityLessThan(@PathVariable("quantity") Integer quantity) {
        return new ResponseEntity<>(warehouseService.findWhereQuantityLessThan(quantity), HttpStatus.OK);
    }
    @GetMapping("/get/where_sale_quantity_more-{quantity}")
    public ResponseEntity<List<Warehouse>> getWarehouseWhereSaleQuantityMoreThan(@PathVariable("quantity") Integer quantity) {
        return new ResponseEntity<>(warehouseService.findWarehouseWhereSaleQuantityMoreThan(quantity), HttpStatus.OK);
    }
    @GetMapping("/get/where_sale_quantity_less-{quantity}")
    public ResponseEntity<List<Warehouse>> getWarehouseWhereSaleQuantityLessThan(@PathVariable("quantity") Integer quantity) {
        return new ResponseEntity<>(warehouseService.findWarehouseWhereSaleQuantityLessThan(quantity), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public void deleteAllWarehouse() {
        warehouseService.deleteAllWarehouse();
    }
    @DeleteMapping("/deletei-{id}")
    public void deleteWarehouseById(@PathVariable("id") Integer id) {
        warehouseService.deleteWarehouseById(id);
    }
    @DeleteMapping("/deleten-{name}")
    public void deleteWarehouseByName(@PathVariable("name") String name) {
        warehouseService.deleteWarehouseByName(name);
    }
}
