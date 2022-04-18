package backend.rest.service.repository;

import backend.rest.service.repository.entity.Charge;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChargeRepository extends CrudRepository<Charge, Integer> {
    @Query(value = "FROM charges a JOIN a.expenseItem c WHERE c.name = :name")
    List<Charge> findByExpenseItemName(@Param("name") String name);

    @Query(value = "SELECT * FROM charges WHERE amount < :amount", nativeQuery = true)
    List<Charge> getChargeWhereAmountLowerThan(@Param("amount") Double amount);

    @Query(value = "SELECT * FROM charges WHERE amount > :amount", nativeQuery = true)
    List<Charge> getChargeWhereAmountGreaterThan(@Param("amount") Double amount);

    @Modifying
    @Transactional
    @Query(value = "DELETE charges FROM charges INNER JOIN expense_items ON charges.expense_item_id = expense_items.id WHERE expense_items.name = :name", nativeQuery = true)
    void deleteChargeByExpenseItemName(@Param("name") String name);
}
