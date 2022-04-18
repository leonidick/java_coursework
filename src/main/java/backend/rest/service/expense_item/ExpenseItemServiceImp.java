package backend.rest.service.expense_item;

import backend.rest.service.repository.ExpenseItemRepository;
import backend.rest.service.repository.entity.ExpenseItem;
import backend.rest.service.repository.entity.exception.EntryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseItemServiceImp implements ExpenseItemService {
    @Autowired
    private ExpenseItemRepository expenseItemRepository;

    @Override
    public ExpenseItem addExpenseItem(ExpenseItem expenseItem) {
        return expenseItemRepository.save(expenseItem);
    }

    @Override
    public ExpenseItem findExpenseItemByName(String name) {
        return expenseItemRepository.findByName(name);
    }

    @Override
    public List<ExpenseItem> getExpenseItemWhereNameStartedBy(String start) {
        return expenseItemRepository.getExpenseItemWhereNameStartedBy(start);
    }

    @Override
    public List<ExpenseItem> getExpenseItemWhereChargeAmountMoreThan(Double amount) {
        return expenseItemRepository.getExpenseItemWhereChargeAmountGreaterThan(amount);
    }

    @Override
    public List<ExpenseItem> getExpenseItemWhereChargeAmountLessThan(Double amount) {
        return expenseItemRepository.getExpenseItemWhereChargeAmountLowerThan(amount);
    }

    @Override
    public ExpenseItem getExpenseItemById(Integer id) {
        Optional<ExpenseItem> expenseItemOptional = expenseItemRepository.findById(id);
        if (!expenseItemOptional.isPresent()) {
            throw new EntryNotFoundException();
        }
        return expenseItemOptional.get();
    }

    @Override
    public List<ExpenseItem> listExpenseItem() {
        return (List<ExpenseItem>) expenseItemRepository.findAll();
    }

    @Override
    public void deleteExpenseItemById(Integer id) {
        Optional<ExpenseItem> expenseItemOptional = expenseItemRepository.findById(id);
        if (!expenseItemOptional.isPresent()) {
            throw new EntryNotFoundException();
        }
        expenseItemRepository.deleteById(id);
    }

    @Override
    public void deleteAllExpenseItem() {
        expenseItemRepository.deleteAll();
    }
}
