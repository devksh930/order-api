package me.devksh930.orderapi.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.account.domain.repository.AccountRepository;
import me.devksh930.orderapi.account.domain.repository.AccountRepositoryExtension;
import me.devksh930.orderapi.account.dto.AccountLastOrderResponse;
import me.devksh930.orderapi.account.dto.AccountResponse;
import me.devksh930.orderapi.account.exception.AccountNotFounException;
import me.devksh930.orderapi.order.domain.repository.OrderRepositoryExtension;
import me.devksh930.orderapi.order.dto.AdminOrderInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AdminAccountService {
    private final AccountRepository accountRepository;
    private final AccountRepositoryExtension accountRepositoryExtension;
    private final OrderRepositoryExtension orderRepositoryExtension;

    public AccountResponse getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFounException::new);
        return AccountResponse.of(account);
    }

    public Page<AccountLastOrderResponse> getAccountList(final String name, final String email, final PageRequest pageRequest) {
        Page<AccountLastOrderResponse> accountResult = accountRepositoryExtension.findByNameOrEmail(name, email, pageRequest);

        List<AccountLastOrderResponse> content = accountResult.getContent();

        List<Long> ids = accountResult.getContent().stream()
                .map(AccountLastOrderResponse::getId)
                .collect(Collectors.toList());


        Map<Long, List<AdminOrderInfoResponse>> collect = orderRepositoryExtension.findAdminOrderInfoResponse(ids)
                .stream()
                .collect(Collectors.groupingBy(AdminOrderInfoResponse::getAccountId));


        for (AccountLastOrderResponse accountLastOrderResponse : content) {
            List<AdminOrderInfoResponse> adminOrderInfoResponse = collect.get(accountLastOrderResponse.getId());
            if (adminOrderInfoResponse != null && !adminOrderInfoResponse.isEmpty()) {
                adminOrderInfoResponse
                        .stream()
                        .max(Comparator.comparing(AdminOrderInfoResponse::getOrderDate))
                        .ifPresent(accountLastOrderResponse::orderInfoAdd);

            }
        }

        return accountResult;
    }
}
