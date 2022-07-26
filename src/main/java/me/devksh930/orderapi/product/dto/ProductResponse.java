package me.devksh930.orderapi.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import me.devksh930.orderapi.product.domain.Product;

import java.time.LocalDateTime;

@Getter
public class ProductResponse {
    @Schema(description = "상품의 db상의 id")
    private final Long productId;
    @Schema(description = "상품명")
    private final String productName;

    @Schema(description = "상품추가날짜")
    private final LocalDateTime createdAt;

    public ProductResponse(final Long productId, final String productName, LocalDateTime createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.createdAt = createdAt;
    }

    public static ProductResponse of(final Product product) {
        return new ProductResponse(product.getProductId(), product.getProductName(), product.getCreatedDate());
    }
}
