package me.devksh930.orderapi.product.dto;

import lombok.Getter;
import me.devksh930.orderapi.product.domain.Product;

import java.time.LocalDateTime;

@Getter
public class ProductResponse {
    private final Long productId;
    private final String productName;

    private final LocalDateTime createdAt;

    public ProductResponse(final Long productId, final String productName, LocalDateTime createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.createdAt = createdAt;
    }

    public static ProductResponse of(final Product product) {
        return new ProductResponse(product.getProductId(), product.getProductName().getProductName(), product.getCreatedDate());
    }
}
