package com.example.midterm.ENTITY;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String item_name;

    @NotBlank
    private String category_name;

    @NotBlank
    private int price;

    public Item() {
    }

    public Item(@NotBlank String item_name,
                @NotBlank String category_name,
                @NotBlank int price) {
        this.item_name = item_name;
        this.category_name = category_name;
        this.price = price;
    }

    public Item(@NotBlank String item_name,
                @NotBlank String category_name,
                @NotBlank Integer price) {
        this.item_name = item_name;
        this.category_name = category_name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item= (Item) o;
        return
//              Objects.equals(email, customer.email) &&
                Objects.equals(item_name, item.item_name) &&
                        Objects.equals(category_name, item.category_name) &&
                        Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item_name, category_name, price);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", item_name='" + item_name + '\'' +
                ", category='" + category_name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerid", nullable = false)
    private Customer customer;

    // Getters and Setters (Omitted for brevity)
}