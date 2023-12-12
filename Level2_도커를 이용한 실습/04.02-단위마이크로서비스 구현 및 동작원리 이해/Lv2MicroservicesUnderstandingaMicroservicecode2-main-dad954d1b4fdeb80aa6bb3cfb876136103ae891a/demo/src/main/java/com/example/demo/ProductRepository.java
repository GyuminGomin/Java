package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // Entity의 Repository패턴으로 CRUD에 준하는 rest api가 자동 생성
    
}
