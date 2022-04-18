package backend.rest.service.repository;

import backend.rest.service.repository.entity.ExpenseItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseItemRepository extends CrudRepository<ExpenseItem, Integer> {
    ExpenseItem findByName(String name);

    @Query(value = "SELECT * FROM expense_items WHERE expense_items.name REGEXP CONCAT('^', :start)", nativeQuery = true)
    List<ExpenseItem> getExpenseItemWhereNameStartedBy(@Param("start") String start);

    //получить пункты из таблицы расходов, расходы на которые превысилы amount
    @Query(value = "SELECT DISTINCT expense_items.id, expense_items.name FROM expense_items INNER JOIN charges ON expense_items.id = charges.expense_item_id WHERE charges.amount > :amount", nativeQuery = true)
    List<ExpenseItem> getExpenseItemWhereChargeAmountGreaterThan(@Param("amount") Double amount);

    //получить пункты из таблицы расходов, расходы на которые меньше amount
    @Query(value = "SELECT DISTINCT expense_items.id, expense_items.name FROM expense_items INNER JOIN charges ON expense_items.id = charges.expense_item_id WHERE charges.amount < :amount", nativeQuery = true)
    List<ExpenseItem> getExpenseItemWhereChargeAmountLowerThan(@Param("amount") Double amount);
}
