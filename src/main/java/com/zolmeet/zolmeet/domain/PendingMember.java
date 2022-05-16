package com.zolmeet.zolmeet.domain;


public class PendingMember {

    private final Member member;

    public PendingMember(Member member) {
        this.member = member;
        this.member.changedStatus(Status.PENDING);
    }

    public boolean isPending() {
        return this.member.nowStatus() == Status.PENDING;
    }

    public CompletedMembers match(PendingMember partner) {
        if (this.member.isSameStudentId(partner.member)) {
            throw new IllegalArgumentException("자신과는 매칭이 되지 않습니다.");
        }
        if (this.member.isEqualGender(partner.member)) {
            throw new IllegalArgumentException("동일한 성별끼리는 매칭이 되지 않습니다.");
        }
        if (!isPending() || !(partner.isPending())) {
            throw new IllegalArgumentException("PENDING 상태가 아닌 멤버와는 매칭이 되지 않습니다.");
        }
        if (checkMatchingHistory(partner)) {
            throw new IllegalArgumentException("과거에 매칭된 이력이 있는 이성입니다.");
        }

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
}
