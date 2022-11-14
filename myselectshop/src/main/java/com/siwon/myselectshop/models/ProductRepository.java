package com.siwon.myselectshop.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository 사용할 때 어떤 녀석을 대상으로(product) JPA를 할거냐가 중요. Long 형태다.

}
