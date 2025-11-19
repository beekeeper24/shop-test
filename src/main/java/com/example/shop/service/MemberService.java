package com.example.shop.service;

import com.example.shop.common.ResponseEntity;
import com.example.shop.member.Member;
import com.example.shop.member.MemberRepository;
import com.example.shop.member.MemberRequest;
import com.example.shop.member.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity<List<MemberResponse>> findAll() {
        List<MemberResponse> responses = memberRepository.findAll()
                .stream()
                .map(MemberResponse::from)
                .toList();
        long totalCount = memberRepository.count();
        return new ResponseEntity<>(HttpStatus.OK.value(), responses, totalCount);
    }

    public ResponseEntity<MemberResponse> create(MemberRequest request) {
        Member member = new Member(
                UUID.randomUUID(),
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        Member saved = memberRepository.save(member);
        return new ResponseEntity<>(HttpStatus.CREATED.value(), MemberResponse.from(saved), 1);
    }

    public ResponseEntity<MemberResponse> update(String id, MemberRequest request) {
        Member member = new Member(
                id,
                request.email(),
                request.name(),
                request.password(),
                request.phone(),
                request.saltKey(),
                request.flag()
        );
        Member saved = memberRepository.save(member);
        return new ResponseEntity<>(HttpStatus.OK.value(), MemberResponse.from(saved), 1);
    }

    public ResponseEntity<Void> delete(String id) {
        memberRepository.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT.value(), null, 0);
    }
}
