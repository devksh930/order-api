package me.devksh930.orderapi.account.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "nickname", nullable = false, length = 30)
    private String nickname;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "phonenumber", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    private AccountGender accountGender;

    private enum AccountGender {
        MALE, FEMALE, NONE
    }

    @Builder
    public Account(String name, String nickname, String email, String phoneNumber, String password, AccountGender accountGender) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.accountGender = accountGender;
    }
}
