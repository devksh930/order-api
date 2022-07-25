package me.devksh930.orderapi.admin.controller;

import lombok.RequiredArgsConstructor;
import me.devksh930.orderapi.account.dto.AccountLastOrderResponse;
import me.devksh930.orderapi.account.dto.AccountResponse;
import me.devksh930.orderapi.admin.service.AdminAccountService;
import me.devksh930.orderapi.admin.service.AdminOrderService;
import me.devksh930.orderapi.order.dto.OrderInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminAccountService adminAccountService;
    private final AdminOrderService adminOrderService;

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountResponse> accountDetails(@PathVariable("accountId") Long accountId) {
        return ResponseEntity.ok(adminAccountService.getAccount(accountId));
    }

    @GetMapping("/accounts")
    public Page<AccountLastOrderResponse> accountResponses(@RequestParam(required = false) String email,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false, defaultValue = "5") Integer size,
                                                           @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        final PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return adminAccountService.getAccountList(name, email, pageRequest);
    }

    @GetMapping("/orders/accounts/{accountId}")
    @Transactional
    public Page<OrderInfoResponse> accountOrderList(@PathVariable("accountId") Long accountId,
                                                    @RequestParam(required = false, defaultValue = "5") Integer size,
                                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber) {
        final PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return adminOrderService.getAccountOrderList(pageRequest, accountId);
    }
}
