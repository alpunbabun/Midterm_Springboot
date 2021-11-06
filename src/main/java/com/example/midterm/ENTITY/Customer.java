package com.example.midterm.ENTITY;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String role;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "customer")
    private Set<Item> items = new HashSet<>();

    public Customer() {
    }

    public Customer(@NotBlank String email,
                    @NotBlank String username,
                    @NotBlank String password) {
        this.email = email;
        this.username = username;
        this.password = password;

    }

    public Customer(@NotBlank String username,
                    @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer= (Customer) o;
        return
//              Objects.equals(email, customer.email) &&
                Objects.equals(username, customer.username) &&
                        Objects.equals(password, customer.password) &&
                        Objects.equals(role, customer.role);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, role);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}