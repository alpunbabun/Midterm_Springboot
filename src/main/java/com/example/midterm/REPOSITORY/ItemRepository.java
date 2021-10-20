package com.example.midterm.REPOSITORY;

import com.example.midterm.ENTITY.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByIdAndCustomerId(Long id, Long itemId);
    List<Item> findByCustomerId(Long id);
}
