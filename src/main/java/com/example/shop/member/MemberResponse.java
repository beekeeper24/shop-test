package com.example.shop.member;

import java.time.OffsetDateTime;
import java.util.UUID;

public record MemberResponse(
        UUID id,
        String email,
        String name,
        String phone,
        String flag,
        OffsetDateTime regDt,
        OffsetDateTime modifyDt
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getPhone(),
                member.getFlag(),
                member.getRegDt(),
                member.getModifyDt()
        );
    }
}
