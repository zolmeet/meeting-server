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

    public CompletedMembers match(PendingMember pendingMember) {
        if (this.member.isSameStudentId(pendingMember.member)) {
            throw new IllegalArgumentException("자신과는 매칭이 되지 않습니다.");
        }
        if (this.member.isEqualGender(pendingMember.member)) {
            throw new IllegalArgumentException("동일한 성별끼리는 매칭이 되지 않습니다.");
        }
        if (!isPending() || !(pendingMember.isPending())) {
            throw  new IllegalArgumentException("PENDING 상태가 아닌 멤버와는 매칭이 되지 않습니다.");
        }
        return new CompletedMembers(this.member, pendingMember.member);
    }
}
