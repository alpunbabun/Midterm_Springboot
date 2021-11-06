package com.example.midterm.CONTROLLER;

import com.example.midterm.ENTITY.Customer;
import com.example.midterm.REPOSITORY.CustomerRepository;
import com.example.midterm.STATUS.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/login")
    public String login(){
        return "This is Home Page";
    }

    @GetMapping("/login/admin")
    public String admin(){
        return "This is Admin Page";
    }

    @GetMapping("/customers")
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @GetMapping(value = "/customers/{id}")
    public Optional<Customer> getCustomer(@PathVariable("id") Long id) {
        return customerRepository.findById(id);
    }

    @RequestMapping(value = "/customers/register", method= RequestMethod.POST)
    public String registerCustomer(@Valid @RequestBody Customer newCustomer) {
        List<Customer> customers = customerRepository.findAll();

        System.out.println("New customer: " + newCustomer.toString());

        for (Customer customer : customers) {
            System.out.println("Registered customer: " + newCustomer.toString());

            if (customer.equals(newCustomer)) {
                System.out.println("Customer Already exists!");
                return "USER_ALREADY_EXISTS";
            }
        }
        customerRepository.save(newCustomer);
        return "SUCCESSFULLY_REGISTERED";
    }

    @PostMapping("/customers/login")
    public Customer loginCustomer(@Valid @RequestBody Customer customer) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer other : customers) {
            if (other.equals(customer)) {
//                customerRepository.save(customer);
                return other;
            }
        }        return null;
    }

    @PostMapping("/customers/logout")
    public Status logOutCustomer(@Valid @RequestBody Customer customer) {
        List<Customer> customers = customerRepository.findAll();
        for (Customer other : customers) {
            if (other.equals(customer)) {
//                customerRepository.save(customer);
                return Status.SUCCESSFULLY_LOGOUT;
            }
        }        return Status.FAILURE;
    }

    @PutMapping("/customers/{customerid}")
    public Customer updatePost(@PathVariable Long customerid, @Valid @RequestBody Customer customerRequest) {
        return customerRepository.findById(customerid).map(customer -> {
            customer.setEmail(customerRequest.getEmail());
            customer.setEmail(customerRequest.getEmail());
            customer.setUsername(customerRequest.getUsername());
            customer.setPassword(customerRequest.getPassword());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer id " + customerid + " not found"));
    }

    @DeleteMapping("/customers/{customerid}")
    public Status deleteCustomer(@PathVariable("id") Long id) {
        boolean exists = customerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("customer with id" + id + "does not exists");
        }
        customerRepository.deleteById(id);
        return Status.SUCCESSFULLY_DELETED;
    }

    @DeleteMapping("/customers/deleteall")
    public Status deleteCustomers() {
        customerRepository.deleteAll();
        return Status.SUCCESSFULLY_DELETED;
    }
}