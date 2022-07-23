package me.devksh930.orderapi.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.devksh930.orderapi.product.domain.Product;
import me.devksh930.orderapi.product.domain.ProductName;

import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class ProductRequest {
    @Pattern(regexp = ProductName.REGEX, message = ProductName.ERR_MSG)
    private String productName;

    public Product toEntity() {
        return Product.builder()
                .productName(productName)
                .build();
    }

}
