package com.zolmeet.zolmeet.domain.member;
import java.util.*;

public class MemberRepositoryImpl implements MemberRepository {

    private static final Map<Integer, Member> store = new HashMap<>();
    private static Integer index = 0;

    @Override
    public void save(Member member) {
        store.put(index++, member);
    }
    @Override
    public Member find(Integer id) {
        return store.get(id);
    }

    @Override
    public Member findByStudentId(String studentId) {
        return store.values().stream()
                .filter(member -> (member.getStudentId()).matches(studentId))
                .findAny().get();
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clear() {
        store.clear();
        index = 0;
    }
}
