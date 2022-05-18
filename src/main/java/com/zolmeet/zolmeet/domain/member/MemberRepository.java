package com.zolmeet.zolmeet.domain.member;

import java.util.List;

public interface MemberRepository {
    void save(Member member);

    Member findById(Integer id);
    Member findByStudentId(String studentId);
    Member findByMember(Member member);
    List<Member> findAll();

    void update(String studentId, Member updateParam);

    void delete(Member member);
    void clear();

}
