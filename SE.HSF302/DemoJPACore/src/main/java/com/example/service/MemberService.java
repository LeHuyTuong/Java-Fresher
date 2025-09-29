package com.example.service;

import com.example.model.MemberEntity;

import java.util.List;

public interface MemberService {

    public boolean addMember(MemberEntity member);

    public boolean removeMember(long memberId);
    public boolean updateMember(MemberEntity member);
    public MemberEntity getMemberById(long memberId);
}
