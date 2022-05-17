/*
package com.zolmeet.zolmeet.domain;

import com.zolmeet.zolmeet.domain.member.Member;
import com.zolmeet.zolmeet.domain.member.MemberRepository;
import com.zolmeet.zolmeet.domain.member.MemberRepositoryImpl;
import com.zolmeet.zolmeet.domain.member.Status;
import lombok.Getter;

import java.util.List;

@Getter
public class PendingMember {

    private final Member member;

    MemberRepository pendingMemberRepository = new MemberRepositoryImpl();

    public PendingMember(Member member) {
        this.member = member;
        this.member.changedStatus(Status.PENDING);
        pendingMemberRepository.save(this);
    }

    */
/*public boolean isPending() {
        return this.member.nowStatus() == Status.PENDING;
    }*//*


    public CompletedMembers match(PendingMember partner) {
        if (this.member.isSameStudentId(partner.member)) {
            throw new IllegalArgumentException("자신과는 매칭이 되지 않습니다.");
        }
        if (this.member.isEqualGender(partner.member)) {
            throw new IllegalArgumentException("동일한 성별끼리는 매칭이 되지 않습니다.");
        }
        if (checkMatchingHistory(partner)) {
            throw new IllegalArgumentException("과거에 매칭된 이력이 있는 이성입니다.");
        }

        */
/*if (!isPending() || !(partner.isPending())) {
            throw new IllegalArgumentException("PENDING 상태가 아닌 멤버와는 매칭이 되지 않습니다.");
        }*//*


        saveStudentId(partner);

        return new CompletedMembers(this.member, partner.member);
    }

    private boolean checkMatchingHistory(PendingMember partner) {
        return this.member.matchingHistory.contains(partner.member.getStudentId());
    }

    private void saveStudentId(PendingMember pendingMember) {
        this.member.matchingHistory.add(pendingMember.member.getStudentId());
        pendingMember.member.matchingHistory.add(this.member.getStudentId());
    }

    */
/**
     *
     *
     *//*


    public CompletedMembers matchV2() {
        List<PendingMember> members = pendingMemberRepository.findAll();

        for (PendingMember candidate : members) {
            if (isNotSelf(candidate)) continue;

            return new CompletedMembers(this.member, candidate.member);
        }
        return null;
    }

    private boolean isNotSelf(PendingMember candidate) {
        try {
            if (this.member.isSameStudentId(candidate.member)) {
                throw new IllegalArgumentException("자기자신과는 매칭 될 수 없습니다.");
            }
        }catch (IllegalArgumentException e) {
            return true;
        }
        return false;
    }


}
*/
