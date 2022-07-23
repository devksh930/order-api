package me.devksh930.orderapi.account.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Nickname nickname;

    @Embedded
    private Password password;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Email email;

    @Enumerated(EnumType.STRING)
    private AccountGender accountGender;

    @Enumerated(EnumType.STRING)
    private AccountRole accountRole;

    @Builder
    public Account(final String name,
                   final String nickname,
                   final String email,
                   final String phoneNumber,
                   final String password,
                   final PasswordEncoder passwordEncoder,
                   final String accountGender) {

        this.name = new Name(name);
        this.nickname = new Nickname(nickname);
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.password = new Password(password, passwordEncoder);
        this.accountGender = AccountGender.of(accountGender);
        this.accountRole = AccountRole.ROLE_USER;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.getName();
    }

    public String getNickname() {
        return nickname.getNickname();
    }

    public String getPassword() {
        return password.getPassword();
    }

    public String getPhoneNumber() {
        return phoneNumber.getPhoneNumber();
    }

    public String getEmail() {
        return email.getEmail();
    }

    public AccountGender getAccountGender() {
        return accountGender;
    }

    public AccountRole getAccountRole() {
        return accountRole;
    }
}
