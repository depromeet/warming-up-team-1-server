package com.depromeet.warmup1.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginDto {
    private String jwtToken;
    private String refreshToken;

    public static LoginDto from(String jwtToken, String refreshToken) {
        return new LoginDto(jwtToken, refreshToken);
    }

}
