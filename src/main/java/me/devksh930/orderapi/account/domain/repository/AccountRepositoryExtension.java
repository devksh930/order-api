package me.devksh930.orderapi.account.domain.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.devksh930.orderapi.account.domain.Account;
import me.devksh930.orderapi.account.dto.AccountLastOrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static me.devksh930.orderapi.account.domain.QAccount.account;

@Repository
public class AccountRepositoryExtension extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public AccountRepositoryExtension(final JPAQueryFactory queryFactory) {
        super(Account.class);
        this.queryFactory = queryFactory;
    }

    public Page<AccountLastOrderResponse> findByNameOrEmail(final String name, final String email, final PageRequest pageRequest) {
        List<AccountLastOrderResponse> content = queryFactory
                .select(
                        Projections.constructor(AccountLastOrderResponse.class,
                                account.id,
                                account.name.name,
                                account.nickname.nickname,
                                account.phoneNumber.phoneNumber,
                                account.email.email,
                                account.accountGender
                        ))
                .from(account)
                .where(nameEq(name),
                        emailEq(email))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        List<Long> fetch = queryFactory
                .select(account.count())
                .from(account)
                .where(nameEq(name), emailEq(email))
                .fetch();

        return PageableExecutionUtils.getPage(content, pageRequest, () -> fetch.get(0));
    }

    private BooleanExpression nameEq(String name) {
        return name != null ? account.name.name.eq(name) : null;
    }

    private BooleanExpression emailEq(String email) {
        return email != null ? account.email.email.eq(email) : null;
    }
}
