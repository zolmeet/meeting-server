package com.zolmeet.zolmeet.domain;

public class PendingMatching {

    private final Member requestMember;

    public PendingMatching(Member requestMember) {
        this.requestMember = requestMember;
    }

    public boolean isPending() {
        return true;
    }

    public CompletedMatching match(PendingMatching otherMatching) {
        if (this.requestMember.isSameStudentId(otherMatching.requestMember)) {
            throw new IllegalArgumentException("자신과는 매칭이 되지 않습니다.");
        }
        if (this.requestMember.isEqualGender(otherMatching.requestMember)) {
            throw new IllegalArgumentException("동일한 성별끼리는 매칭이 되지 않습니다.");
        }
        return new CompletedMatching(this.requestMember, otherMatching.requestMember);
    }
}
