package com.example.repository;

import com.example.model.MemberEntity;

public interface MemberRepository {
    public boolean addMember(MemberEntity member);
    public boolean removeMember(long memberId);
    public boolean updateMember(MemberEntity member);
    public MemberEntity getMemberById(long memberId);
}
