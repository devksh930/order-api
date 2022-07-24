package me.devksh930.orderapi.product.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void creatProduct() {
        Product product = Product.builder()
                .productName("상품🧑🏻‍💻")
                .build();

        assertThat(product).isNotNull();
        assertThat(product.getProductName()).isNotEmpty();
    }
}