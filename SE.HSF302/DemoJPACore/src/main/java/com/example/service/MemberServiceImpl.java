package com.example.service;

import com.example.model.MemberEntity;
import com.example.repository.MemberRepository;
import com.example.repository.MemberRepositoryImpl;

import java.util.List;

public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository; // Viết code theo interface, không theo implementation cụ thể

    public MemberServiceImpl(String jpaName) {
        memberRepository = new MemberRepositoryImpl(jpaName);
    }

    @Override
    public boolean addMember(MemberEntity member) {
        return memberRepository.addMember(member);
    }

    @Override
    public boolean removeMember(long memberId) {
        return memberRepository.removeMember(memberId);
    }

    @Override
    public boolean updateMember(MemberEntity member) {
        return memberRepository.updateMember(member);
    }

    @Override
    public MemberEntity getMemberById(long memberId) {
        return memberRepository.getMemberById(memberId);
    }


}
