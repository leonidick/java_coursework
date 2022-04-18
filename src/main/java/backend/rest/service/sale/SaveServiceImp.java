package backend.rest.service.sale;

import backend.rest.service.repository.SaleRepository;
import backend.rest.service.repository.entity.Sale;
import backend.rest.service.repository.entity.exception.EntryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaveServiceImp implements SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale addSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> getByWarehouseName(String name) {
        return saleRepository.findByWarehouseName(name);
    }

    @Override
    public List<Sale> getWhereQuantityMoreThan(Integer quantity) {
        return saleRepository.getSalesWhereQuantityGreaterThan(quantity);
    }

    @Override
    public List<Sale> getWhereQuantityLessThan(Integer quantity) {
        return saleRepository.getSalesWhereQuantityLowerThan(quantity);
    }

    @Override
    public Sale getSaleById(Integer id) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (!saleOptional.isPresent()) {
            throw new EntryNotFoundException();
        }
        return saleOptional.get();
    }

    @Override
    public List<Sale> listSale() {
        return (List<Sale>) saleRepository.findAll();
    }

    @Override
    public void deleteSaleById(Integer id) {
        Optional<Sale> saleOptional = saleRepository.findById(id);
        if (!saleOptional.isPresent()) {
            throw new EntryNotFoundException();
        }
        saleRepository.delete(saleOptional.get());
    }

    @Override
    public void deleteSaleByWarehouseName(String name) {
        saleRepository.deleteSaleByName(name);
    }

    @Override
    public void deleteAllSale() {
        saleRepository.deleteAll();
    }
}
