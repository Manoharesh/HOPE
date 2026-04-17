package com.library.service;

import com.library.exception.MemberNotFoundException;
import com.library.model.Member;
import com.library.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalStateException("A member with email '" + member.getEmail() + "' is already registered.");
        }
        member.setTotalPendingFines(0.0);
        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member payFine(Long memberId) {
        Member member = getMemberById(memberId);
        if (member.getTotalPendingFines() <= 0.0) {
            throw new IllegalStateException("Member has no pending fines.");
        }
        member.setTotalPendingFines(0.0);
        return memberRepository.save(member);
    }
}
