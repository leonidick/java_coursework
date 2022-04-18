package backend.rest.service.repository.entity;

import backend.rest.service.repository.entity.exception.NegativeNumberException;
import backend.rest.service.repository.entity.exception.EmptyStringException;

import javax.persistence.*;

@Entity(name = "warehouses")
public class Warehouse {
    public Warehouse() {
    }
    public Warehouse(String name,
                     Integer quantity,
                     Double amount) {
        if (quantity < 0 || amount < 0) {
            throw new NegativeNumberException();
        }
        if (name.isEmpty()) {
            throw new EmptyStringException();
        }
        this.name = name;
        this.quantity = quantity;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "amount", nullable = false)
    private Double amount;

    public Integer getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public Integer getQuantity() {
        return this.quantity;
    }
    public Double getAmount() {
        return this.amount;
    }

    public void setQuantity(Integer quantity) {
        if (quantity < 0) {
            throw new NegativeNumberException();
        }
        this.quantity = quantity;
    }
    public void setAmount(Double amount) {
        if (amount < 0) {
            throw new NegativeNumberException();
        }
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }
}
