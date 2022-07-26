package me.devksh930.orderapi.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.devksh930.orderapi.product.domain.Product;
import me.devksh930.orderapi.product.domain.ProductName;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class ProductRequest {
    @Schema(description = "상품명")
    @Pattern(regexp = ProductName.REGEX, message = ProductName.ERR_MSG)
    private String productName;

    public Product toEntity() {
        return Product.builder()
                .productName(productName)
                .build();
    }

}
