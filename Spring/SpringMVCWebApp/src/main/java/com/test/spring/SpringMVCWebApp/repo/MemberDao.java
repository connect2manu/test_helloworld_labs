package com.test.spring.SpringMVCWebApp.repo;

import java.util.List;

import com.test.spring.SpringMVCWebApp.domain.Member;

public interface MemberDao
{
    public Member findById(Long id);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public void register(Member member);
}
