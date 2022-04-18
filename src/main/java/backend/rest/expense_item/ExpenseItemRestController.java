package backend.rest.expense_item;

import backend.rest.service.expense_item.ExpenseItemService;
import backend.rest.service.repository.entity.ExpenseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense_item")
public class ExpenseItemRestController {
    @Autowired
    private ExpenseItemService expenseItemService;
    public ExpenseItemService getExpenseItemService() {
        return this.expenseItemService;
    }

    @PostMapping("/add")
    public ResponseEntity<ExpenseItem> addExpenseItem(@RequestBody ExpenseItemJsonHandler expenseItemJsonHandler) {
        ExpenseItem expenseItem = new ExpenseItem(expenseItemJsonHandler.name);
        return new ResponseEntity<>(expenseItemService.addExpenseItem(expenseItem), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ExpenseItem>> getAllExpenseItem() {
        return new ResponseEntity<>(expenseItemService.listExpenseItem(), HttpStatus.OK);
    }
    @GetMapping("/geti-{id}")
    public ResponseEntity<ExpenseItem> getExpenseItemById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(expenseItemService.getExpenseItemById(id), HttpStatus.OK);
    }
    @GetMapping("/getn-{name}")
    public ResponseEntity<ExpenseItem> getExpenseItemByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(expenseItemService.findExpenseItemByName(name), HttpStatus.OK);
    }
    @GetMapping("/get/where_name_started-{start}")
    public ResponseEntity<List<ExpenseItem>> getExpenseItemWhereNameStartedBy(@PathVariable("start") String start) {
        return new ResponseEntity<>(expenseItemService.getExpenseItemWhereNameStartedBy(start), HttpStatus.OK);
    }
    @GetMapping("/get/where_charge_amount_more-{amount}")
    public ResponseEntity<List<ExpenseItem>> getExpenseItemWhereChargeAmountMoreThat(@PathVariable("amount") Double amount) {
        return new ResponseEntity<>(expenseItemService.getExpenseItemWhereChargeAmountMoreThan(amount), HttpStatus.OK);
    }
    @GetMapping("/get/where_charge_amount_less-{amount}")
    public ResponseEntity<List<ExpenseItem>> getExpenseItemWhereChargeAmountLessThat(@PathVariable("amount") Double amount) {
        return new ResponseEntity<>(expenseItemService.getExpenseItemWhereChargeAmountLessThan(amount), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void deleteAllExpenseItem() {
        expenseItemService.deleteAllExpenseItem();
    }
    @DeleteMapping("/deletei-{id}")
    public void deleteExpenseItemById(@PathVariable("id") Integer id) {
        expenseItemService.deleteExpenseItemById(id);
    }
}
