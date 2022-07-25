package me.devksh930.orderapi.order.domain;

import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.order.exception.NotMyOrderException;
import me.devksh930.orderapi.order.exception.PaymentAlreadyException;
import me.devksh930.orderapi.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderInfoTest {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Account account;
    private Product product;

    @BeforeEach
    void init() {
        account = initAccount("test@test.com", 1L);
        product = initProduct(1L);
    }

    @Test
    @DisplayName("주문 생성")
    void createOrder() {
        OrderInfo orderInfo = OrderInfo.builder()
                .orderer(account)
                .product(product)
                .build();

        assertThat(orderInfo.getOrderer()).isEqualTo(account);
        assertThat(orderInfo.getProduct()).isEqualTo(product);
        assertThat(orderInfo.getPayment()).isNull();
    }

    @Test
    @DisplayName("주문 결제")
    void orderPayment() {
        OrderInfo orderInfo = OrderInfo.builder()
                .orderer(account)
                .product(product)
                .build();

        orderInfo.pay(account);

        assertThat(orderInfo.getPayment()).isNotNull();
    }

    @Test
    @DisplayName("주문 결제시 내가 주문한 주문이 아닐경우")
    void orderPaymentNotMyOrderException() {
        OrderInfo orderInfo = OrderInfo.builder()
                .orderer(account)
                .product(product)
                .build();
        Account account2 = initAccount("test2@test.com", 2L);


        assertThatThrownBy(() -> orderInfo.pay(account2)).isInstanceOf(NotMyOrderException.class);

        assertThat(orderInfo.getPayment()).isNull();
    }

    @Test
    @DisplayName("주문 결제시 이미 결제한 주문일경우")
    void orderPaymentAlreadyPayment() {
        OrderInfo orderInfo = OrderInfo.builder()
                .orderer(account)
                .product(product)
                .build();
        orderInfo.pay(account);

        assertThatThrownBy(() -> orderInfo.pay(account)).isInstanceOf(PaymentAlreadyException.class);

        assertThat(orderInfo.getPayment()).isNotNull();
    }

    private Product initProduct(Long prudctId) {
        product = Product.builder()
                .productName("상품🧑🏻‍💻")
                .build();
        ReflectionTestUtils.setField(product, "productId", prudctId);
        return product;
    }

    private Account initAccount(String email, Long id) {
        account = Account.builder()
                .name("이름")
                .nickname("nickname")
                .email(email)
                .phoneNumber("01012341234")
                .password("Test1234!11")
                .passwordEncoder(passwordEncoder)
                .accountGender("NONE")
                .build();
        ReflectionTestUtils.setField(account, "id", id);
        return account;
    }
}