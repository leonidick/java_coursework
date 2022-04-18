package backend.rest.service.expense_item;

import backend.rest.service.repository.entity.ExpenseItem;

import java.util.List;

public interface ExpenseItemService {
    ExpenseItem addExpenseItem(ExpenseItem expenseItem);

    ExpenseItem findExpenseItemByName(String name);
    List<ExpenseItem> getExpenseItemWhereNameStartedBy(String start);
    List<ExpenseItem> getExpenseItemWhereChargeAmountMoreThan(Double amount);
    List<ExpenseItem> getExpenseItemWhereChargeAmountLessThan(Double amount);

    ExpenseItem getExpenseItemById(Integer id);
    List<ExpenseItem> listExpenseItem();

    void deleteAllExpenseItem();
    void deleteExpenseItemById(Integer id);
}
