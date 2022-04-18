package backend.rest.service.repository.entity;

import backend.rest.service.repository.entity.exception.NegativeNumberException;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "sales")
public class Sale {
    public Sale(){
    }
    public Sale(Double amount,
                Integer quantity,
                LocalDateTime sale_date,
                Warehouse warehouse) {
        if (amount < 0 || quantity < 0) {
            throw new NegativeNumberException();
        }
        this.amount = amount;
        this.quantity = quantity;
        this.sale_date = sale_date;
        this.warehouse = warehouse;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime sale_date;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false, referencedColumnName = "id")
    private Warehouse warehouse;

    public Integer getId() {
        return id;
    }
    public Double getAmount() {
        return amount;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public LocalDateTime getSaleDate() {
        return sale_date;
    }
    public Warehouse getWarehouse() {
        return warehouse;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", sale_date=" + sale_date +
                ", warehouse=" + warehouse +
                '}';
    }
}
