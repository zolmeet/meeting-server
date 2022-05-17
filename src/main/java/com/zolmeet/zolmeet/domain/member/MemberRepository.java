package com.zolmeet.zolmeet.domain.member;

import java.util.List;

public interface MemberRepository {
    void save(Member member);
    Member find(Integer id);
    Member findByStudentId(String studentId);
    List<Member> findAll();
    void clear();

}
