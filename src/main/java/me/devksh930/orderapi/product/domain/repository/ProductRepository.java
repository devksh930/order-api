package me.devksh930.orderapi.product.domain.repository;

import me.devksh930.orderapi.product.domain.Product;
import me.devksh930.orderapi.product.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT new me.devksh930.orderapi.product.dto.ProductResponse(p.productId,p.productName.productName,p.createdDate) from Product p ORDER BY  p.createdDate DESC ")
    Page<ProductResponse> findAllDto(Pageable pageable);
}
