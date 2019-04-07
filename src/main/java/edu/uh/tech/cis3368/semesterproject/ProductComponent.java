package edu.uh.tech.cis3368.semesterproject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PRODUCT_COMPONENT", schema = "PUBLIC", catalog = "PROJECT")
public class ProductComponent {
    private int id;
    private int quantity;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "QUANTITY", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductComponent that = (ProductComponent) o;
        return id == that.id &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity);
    }
}
