package me.devksh930.orderapi.admin.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.account.dto.AccountResponse;
import me.devksh930.orderapi.admin.dto.AccountLastOrderResponse;
import me.devksh930.orderapi.admin.service.AdminAccountService;
import me.devksh930.orderapi.admin.service.AdminOrderService;
import me.devksh930.orderapi.order.dto.OrderInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin API", description = "회원 목록 조회, 회원 정보 조회 api")
public class AdminController {
    private final AdminAccountService adminAccountService;
    private final AdminOrderService adminOrderService;

    @Operation( summary = "회원정보 accountId로 조회")
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountResponse> accountDetails(@PathVariable("accountId") Long accountId) {
        return ResponseEntity.ok(adminAccountService.getAccount(accountId));
    }

    @Operation( summary = "회원정보 email or name으로 페이징 처리 검색(마지막주문 목록 포함)")
    @GetMapping("/accounts")
    public Page<AccountLastOrderResponse> accountResponses(@RequestParam(required = false) String email,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false, defaultValue = "5") Integer size,
                                                           @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        final PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return adminAccountService.getAccountList(name, email, pageRequest);
    }

    @Operation( summary = "회원의 주문목록 accountId로 검색 페이징처리")
    @GetMapping("/orders/accounts/{accountId}")
    public Page<OrderInfoResponse> accountOrderList(@PathVariable("accountId") Long accountId,
                                                    @RequestParam(required = false, defaultValue = "5") Integer size,
                                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        final PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return adminOrderService.getAccountOrderList(pageRequest, accountId);
    }
}
