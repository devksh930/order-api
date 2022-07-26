package me.devksh930.orderapi.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product API", description = "상품조회, 상품등록, 상품전체 조회와 관련된 API")
public class ProductController {

    private final ProductService productService;


    @Operation( summary = "상품 등록")
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.create(productRequest);
        URI uri = URI.create("/api/products/" + productResponse.getProductId());
        return ResponseEntity.created(uri).body(productResponse);
    }

    @Operation( summary = "상품 productId로 조회")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @Operation( summary = "상품 전체 조회 페이징처리")
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> allProduct(@RequestParam(required = false, defaultValue = "5") Integer size,
                                                            @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        final PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return ResponseEntity.ok(productService.findAllPaging(pageRequest));
    }
}
