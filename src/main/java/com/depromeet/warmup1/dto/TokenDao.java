package com.depromeet.warmup1.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDao {
    private Long memberId;
    private String connectKey;

}
