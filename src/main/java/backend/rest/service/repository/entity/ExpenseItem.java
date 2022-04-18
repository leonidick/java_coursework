package backend.rest.service.repository.entity;

import backend.rest.service.repository.entity.exception.EmptyStringException;

import javax.persistence.*;

@Entity(name = "expense_items")
public class ExpenseItem {
    public ExpenseItem() {
    }
    public ExpenseItem(String name) {
        if (name.isEmpty()) {
            throw new EmptyStringException();
        }
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Integer getId() {
        return id;
    }
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "ExpenseItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
