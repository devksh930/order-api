package me.devksh930.orderapi.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devksh930.orderapi.product.dto.ProductRequest;
import me.devksh930.orderapi.product.dto.ProductResponse;
import me.devksh930.orderapi.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.create(productRequest);
        URI uri = URI.create("/api/products/" + productResponse.getProductId());
        return ResponseEntity.created(uri).body(productResponse);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> allProduct(@RequestParam(required = false, defaultValue = "5") Integer size,
                                                            @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        final PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return ResponseEntity.ok(productService.findAllPaging(pageRequest));
    }
}
