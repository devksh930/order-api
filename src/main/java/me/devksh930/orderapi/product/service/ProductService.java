package me.devksh930.orderapi.product.service;

import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.product.domain.Product;
import me.devksh930.orderapi.product.domain.repository.ProductRepository;
import me.devksh930.orderapi.product.dto.ProductRequest;
import me.devksh930.orderapi.product.dto.ProductResponse;
import me.devksh930.orderapi.product.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse create(final ProductRequest productRequest) {
        Product save = productRepository.save(productRequest.toEntity());
        return ProductResponse.of(save);
    }

    public ProductResponse findById(final Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        return ProductResponse.of(product);
    }

    public Page<ProductResponse> findAllPaging(final PageRequest pageRequest) {
        return productRepository.findAllDto(pageRequest);
    }
}
