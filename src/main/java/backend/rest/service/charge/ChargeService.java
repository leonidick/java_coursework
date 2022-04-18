package backend.rest.service.charge;

import backend.rest.service.repository.entity.Charge;

import java.util.List;

public interface ChargeService {
    Charge addCharge(Charge charge);

    Charge getChargeById(Integer id);
    List<Charge> getChargeByExpenseItemName(String name);
    List<Charge> getChargeWhereAmountLessThan(Double amount);
    List<Charge> getChargeWhereAmountMoreThan(Double amount);
    List<Charge> listCharge();

    void deleteAllCharge();
    void deleteChargeById(Integer id);
    void deleteChargeByExpenseItemName(String name);
}
