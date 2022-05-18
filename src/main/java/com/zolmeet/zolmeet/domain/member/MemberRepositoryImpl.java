package com.zolmeet.zolmeet.domain.member;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MemberRepositoryImpl implements MemberRepository {

    private static final Map<Integer, Member> store = new ConcurrentHashMap<>();
    private static Integer index = 0;

    @Override
    public void save(Member member) {
        store.put(index++, member);
    }
    @Override
    public Member findById(Integer id) {
        return store.get(id);
    }

    @Override
    public Member findByStudentId(String studentId) {
        return store.values().stream()
                .filter(member -> (member.getStudentId()).matches(studentId))
                .findAny().get();
    }

    @Override
    public Member findByMember(Member member) {
        return store.values().stream()
                .filter(member1 -> member1.equals(member))
                .findAny().get();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(String studentId, Member updateParam) {
        Member updateMember = findByStudentId(studentId);
        updateMember.setStatus(updateMember.getStatus());
    }

    @Override
    public void delete(Member member) {
        for (Map.Entry<Integer, Member> entry : store.entrySet()) {
            if (entry.getValue().equals(member)) {
                store.remove(entry.getKey());
            }
        }
    }

    @Override
    public void clear() {
        store.clear();
        index = 0;
    }
}
