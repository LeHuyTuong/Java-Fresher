package com.example.repository;

import com.example.dao.MemberDAO;
import com.example.model.MemberEntity;

public class MemberRepositoryImpl implements MemberRepository {

    private MemberDAO memberDAO;

    public MemberRepositoryImpl(String jpaName) {
        memberDAO = new MemberDAO(jpaName);
    }

    @Override
    public boolean addMember(MemberEntity member) {
        return memberDAO.addMember(member);
    }

    @Override
    public boolean removeMember(long memberId) {
        return memberDAO.removeMember(memberId);
    }

    @Override
    public boolean updateMember(MemberEntity member) {
        return memberDAO.updateMember(member);
    }

    @Override
    public MemberEntity getMemberById(long memberId) {
        return memberDAO.getMemberById(memberId);
    }


}
