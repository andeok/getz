package kr.getz.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.getz.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
