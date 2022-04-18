package backend.rest.service.repository.entity;

import backend.rest.service.repository.entity.exception.NegativeNumberException;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "charges")
public class Charge {
    public Charge(){
    }
    public Charge(Double amount, LocalDateTime charge_date, ExpenseItem expenseItem) {
        if (amount < 0) {
            throw new NegativeNumberException();
        }
        this.amount = amount;
        this.charge_date = charge_date;
        this.expenseItem = expenseItem;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "charge_date", nullable = false)
    private LocalDateTime charge_date;

    @ManyToOne
    @JoinColumn(name = "expense_item_id", nullable = false, referencedColumnName = "id")
    private ExpenseItem expenseItem;

    public Integer getId() {
        return id;
    }
    public Double getAmount() {
        return amount;
    }
    public LocalDateTime getChargeDate() {
        return charge_date;
    }
    public ExpenseItem getExpenseItem() {
        return expenseItem;
    }
}
