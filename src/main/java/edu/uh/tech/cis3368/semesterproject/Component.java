package edu.uh.tech.cis3368.semesterproject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Component {
    private int id;
    private String name;
    private String description;
    private BigDecimal wholesalePrice;
    private List<ProductComponent> productComponents = new ArrayList<>();

    @OneToMany(mappedBy = "component",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public List<ProductComponent> getProductComponents() {
        return productComponents;
    }

    public void setProductComponents(List<ProductComponent> productComponents) {
        this.productComponents = productComponents;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 24)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "WHOLESALE_PRICE", nullable = true, precision = 4)
    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return id == component.id &&
                Objects.equals(name, component.name) &&
                Objects.equals(description, component.description) &&
                Objects.equals(wholesalePrice, component.wholesalePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, wholesalePrice);
    }
}
