package edu.uh.tech.cis3368.semesterproject;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Job {
    private int id;
    private String name;
    private String description;
    private Integer stage;
    Product product;

    Customer customer;

    public Job(){

    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = false, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "STAGE", nullable = false, length = 15)
    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id == job.id &&
                Objects.equals(name, job.name) &&
                Objects.equals(description, job.description) &&
                Objects.equals(stage, job.stage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, stage);
    }
}
