package backend.rest.service.warehouse;


import backend.rest.service.repository.WarehouseRepository;
import backend.rest.service.repository.entity.Warehouse;
import backend.rest.service.repository.entity.exception.EntryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImp implements WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> findWarehouseByName(String name) {
        return warehouseRepository.findByName(name);
    }

    @Override
    public List<Warehouse> findWhereQuantityMoreThan(Integer quantity) {
        return warehouseRepository.findWhereQuantityMoreThan(quantity);
    }

    @Override
    public List<Warehouse> findWhereQuantityLessThan(Integer quantity) {
        return warehouseRepository.findWhereQuantityLessThan(quantity);
    }

    @Override
    public List<Warehouse> findWarehouseWhereSaleQuantityMoreThan(Integer quantity) {
        return warehouseRepository.findWarehouseWhereSaleQuantityMoreThan(quantity);
    }

    @Override
    public List<Warehouse> findWarehouseWhereSaleQuantityLessThan(Integer quantity) {
        return warehouseRepository.findWarehouseWhereSaleQuantityLessThan(quantity);
    }

    @Override
    public Warehouse getWarehouseById(Integer id) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        if (!warehouseOptional.isPresent()) {
            throw new EntryNotFoundException();
        }
        return warehouseOptional.get();
    }

    @Override
    public List<Warehouse> listWarehouse() {
        return (List<Warehouse>) warehouseRepository.findAll();
    }

    @Override
    public void deleteWarehouseById(Integer id) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        if (!warehouseOptional.isPresent()) {
            throw new EntryNotFoundException();
        }
        warehouseRepository.deleteById(id);
    }

    @Override
    public void deleteWarehouseByName(String name) {
        warehouseRepository.deleteByName(name);
    }

    @Override
    public void deleteAllWarehouse() {
        warehouseRepository.deleteAll();
    }
}
