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
    @Column(name = "user_id", nullable = false)
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


    @Builder
    public Account(String name, String nickname, String email, String phoneNumber, String password, PasswordEncoder passwordEncoder, AccountGender accountGender) {
        this.name = new Name(name);
        this.nickname = new Nickname(nickname);
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.password = new Password(password, passwordEncoder);
        this.accountGender = accountGender;
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
}
