package com.example.midterm.CONTROLLER;

import com.example.midterm.ENTITY.Item;
import com.example.midterm.REPOSITORY.CustomerRepository;
import com.example.midterm.REPOSITORY.ItemRepository;
import com.example.midterm.STATUS.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @GetMapping("/customers/all/items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/customers/{id}/items")
    public List<Item> getAllCommentsByPostId(@PathVariable (value = "id") Long id) {
        return itemRepository.findByCustomerId(id);
    }

    @PostMapping("/customers/{customerid}/items")
    public Item createItem(@PathVariable (value = "customerid") Long customerid,
                                 @Valid @RequestBody Item item) {
        return customerRepository.findById(customerid).map(customer -> {
            item.setCustomer(customer);
            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer id " + customerid + " not found"));
    }

    @PutMapping("/customers/{customerid}/items/{itemid}")
    public Item updateItem(@PathVariable (value = "customerid") Long customerid,
                                 @PathVariable (value = "itemid") Long itemid,
                                 @Valid @RequestBody Item itemRequest) {
        if(!customerRepository.existsById(customerid)) {
            throw new ResourceNotFoundException("Item id " + customerid + " not found");
        }

        return itemRepository.findById(itemid).map(item -> {
            item.setItem_name(itemRequest.getItem_name());
            item.setCategory_name(itemRequest.getCategory_name());
            item.setPrice(itemRequest.getPrice());
            return itemRepository.save(item);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer id " + itemid + "not found"));
    }


    @DeleteMapping(value = "/customers/{customerid}/items/{itemid}")
    public Status deleteItem(@PathVariable("itemid") Long itemid) {
        boolean exists = itemRepository.existsById(itemid);
        if (!exists) {
            throw new IllegalStateException("item with id " + itemid + " does not exists");
        }
        itemRepository.deleteById(itemid);
        return Status.SUCCESSFULLY_DELETED;
    }

    @DeleteMapping("/customers/all/items/all")
    public Status deleteItems() {
        customerRepository.deleteAll();
        return Status.SUCCESSFULLY_DELETED;
    }
}
