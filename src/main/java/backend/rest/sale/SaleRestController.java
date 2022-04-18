package backend.rest.sale;

import backend.rest.service.repository.entity.Sale;
import backend.rest.service.repository.entity.Warehouse;
import backend.rest.warehouse.WarehouseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.rest.service.sale.SaleService;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleRestController {
    @Autowired
    private SaleService saleService;
    @Autowired
    private WarehouseRestController warehouseRestController;

    @PostMapping("/add")
    public ResponseEntity<Sale> addSale(@RequestBody SaleJsonHandler saleJsonHandler) {
        Warehouse warehouse = warehouseRestController.getWarehouseService().getWarehouseById(saleJsonHandler.warehouse_id);
        Sale sale = new Sale(saleJsonHandler.amount,
                             saleJsonHandler.quantity,
                             saleJsonHandler.sale_date,
                             warehouse);
        return new ResponseEntity<>(saleService.addSale(sale), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Sale>> getAllSale() {
        return new ResponseEntity<>(saleService.listSale(), HttpStatus.OK);
    }
    @GetMapping("/geti-{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(saleService.getSaleById(id), HttpStatus.OK);
    }
    @GetMapping("/getn-{name}")
    public ResponseEntity<List<Sale>> getSaleByWarehouseName(@PathVariable("name") String name) {
        return new ResponseEntity<>(saleService.getByWarehouseName(name), HttpStatus.OK);
    }
    @GetMapping("/get/where_quantity_more-{quantity}")
    public ResponseEntity<List<Sale>> getSaleWhereQuantityMoreThan(@PathVariable("quantity") Integer quantity) {
        return new ResponseEntity<>(saleService.getWhereQuantityMoreThan(quantity), HttpStatus.OK);
    }
    @GetMapping("/get/where_quantity_less-{quantity}")
    public ResponseEntity<List<Sale>> getSaleWhereQuantityLessThan(@PathVariable("quantity") Integer quantity) {
        return new ResponseEntity<>(saleService.getWhereQuantityLessThan(quantity), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteAllSale() {
        saleService.deleteAllSale();
    }
    @DeleteMapping("/deletei-{id}")
    public void deleteSaleById(@PathVariable("id") Integer id) {
        saleService.deleteSaleById(id);
    }
    @DeleteMapping("/deleten-{name}")
    public void deleteSaleByWarehouseName(@PathVariable("name") String name) {
        saleService.deleteSaleByWarehouseName(name);
    }
}
