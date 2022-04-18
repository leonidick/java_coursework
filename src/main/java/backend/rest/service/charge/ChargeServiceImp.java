package backend.rest.service.charge;

import backend.rest.service.repository.ChargeRepository;
import backend.rest.service.repository.entity.Charge;
import backend.rest.service.repository.entity.exception.EntryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargeServiceImp implements ChargeService {
    @Autowired
    private ChargeRepository chargeRepository;

    @Override
    public Charge addCharge(Charge charge) {
        return chargeRepository.save(charge);
    }

    @Override
    public Charge getChargeById(Integer id) {
        Optional<Charge> chargeOptional = chargeRepository.findById(id);
        if (!chargeOptional.isPresent()) {
            throw new EntryNotFoundException();
        }
        return chargeOptional.get();
    }

    @Override
    public List<Charge> getChargeByExpenseItemName(String name) {
        return chargeRepository.findByExpenseItemName(name);
    }

    @Override
    public List<Charge> getChargeWhereAmountLessThan(Double amount) {
        return chargeRepository.getChargeWhereAmountLowerThan(amount);
    }

    @Override
    public List<Charge> getChargeWhereAmountMoreThan(Double amount) {
        return chargeRepository.getChargeWhereAmountGreaterThan(amount);
    }

    @Override
    public List<Charge> listCharge() {
        return (List<Charge>) chargeRepository.findAll();
    }

    @Override
    public void deleteChargeById(Integer id) {
        Optional<Charge> chargeOptional = chargeRepository.findById(id);
        if (!chargeOptional.isPresent()) {
            throw new EntryNotFoundException();
        }
        chargeRepository.delete(chargeOptional.get());
    }

    @Override
    public void deleteChargeByExpenseItemName(String name) {
        chargeRepository.deleteChargeByExpenseItemName(name);
    }

    @Override
    public void deleteAllCharge() {
        chargeRepository.deleteAll();
    }
}
