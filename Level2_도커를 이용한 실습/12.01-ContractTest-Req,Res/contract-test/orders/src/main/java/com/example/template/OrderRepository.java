package com.example.template;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    List<Order> findByCustomerId(String customerId);
}

