package com.sparta.springlv4.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank
    @Pattern(regexp ="^[a-z][0-9]{4,10}",message = "영어 소문자와 숫자 를 이용해서 4자이상 10자 이상으로 작성하시오")
    private String username;

    @NotBlank
    @Pattern(regexp="^[a-zA-Z0-9~`!@#$%^&*()-_+=]{8,15}$", message="영대소문자와 숫자(0~9) 및 특수문자(~`!@#$%^&*()-_+=)로 이뤄진 8자 이상 15자 이하의 값으로 이뤄졌습니다.")
    private String password;


}
