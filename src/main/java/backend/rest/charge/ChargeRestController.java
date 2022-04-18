package backend.rest.charge;

import backend.rest.expense_item.ExpenseItemRestController;
import backend.rest.service.charge.ChargeService;
import backend.rest.service.repository.entity.Charge;
import backend.rest.service.repository.entity.ExpenseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/charge")
public class ChargeRestController {
    @Autowired
    private ChargeService chargeService;
    @Autowired
    private ExpenseItemRestController expenseItemRestController;

    @PostMapping("/add")
    public ResponseEntity<Charge> addCharge(@RequestBody ChargeJsonHandler chargeJsonHandler) {
        ExpenseItem expenseItem = expenseItemRestController.getExpenseItemService().getExpenseItemById(chargeJsonHandler.expense_item_id);
        Charge charge = new Charge(chargeJsonHandler.amount,
                                   chargeJsonHandler.charge_date,
                                   expenseItem);
        return new ResponseEntity<>(chargeService.addCharge(charge), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Charge>> getAllCharge() {
        return new ResponseEntity<>(chargeService.listCharge(), HttpStatus.OK);
    }
    @GetMapping("/geti-{id}")
    public ResponseEntity<Charge> getChargeById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(chargeService.getChargeById(id), HttpStatus.OK);
    }
    @GetMapping("/getn-{name}")
    public ResponseEntity<List<Charge>> getChargeByExpenseItemName(@PathVariable("name") String name) {
        return new ResponseEntity<>(chargeService.getChargeByExpenseItemName(name), HttpStatus.OK);
    }
    @GetMapping("/get/where_amount_less-{amount}")
    public ResponseEntity<List<Charge>> getChargeWhereAmountLessThan(@PathVariable("amount") Double amount) {
        return new ResponseEntity<>(chargeService.getChargeWhereAmountLessThan(amount), HttpStatus.OK);
    }
    @GetMapping("/get/where_amount_more-{amount}")
    public ResponseEntity<List<Charge>> getChargeWhereAmountMoreThan(@PathVariable("amount") Double amount) {
        return new ResponseEntity<>(chargeService.getChargeWhereAmountMoreThan(amount), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteAllCharge() {
        chargeService.deleteAllCharge();
    }
    @DeleteMapping("/deletei-{id}")
    public void deleteChargeById(@PathVariable("id") Integer id) {
        chargeService.deleteChargeById(id);
    }
    @DeleteMapping("/deleten-{name}")
    public void deleteChargeByExpenseItemName(@PathVariable("name") String name) {
        chargeService.deleteChargeByExpenseItemName(name);
    }
}
