package me.devksh930.orderapi.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {

    @Schema(description = "이메일주소")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @Schema(description = "비밀번호")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}