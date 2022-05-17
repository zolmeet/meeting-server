package com.zolmeet.zolmeet.domain;

import com.zolmeet.zolmeet.domain.member.Member;
import com.zolmeet.zolmeet.domain.member.Status;

import java.time.LocalDate;

public class CompletedMembers {

    private final Member male;
    private final Member female;

    public LocalDate CreatedDate;

    public CompletedMembers(Member male, Member female) {
        this.male = male;
        this.female = female;
        this.male.changedStatus(Status.MATCHED);
        this.female.changedStatus(Status.MATCHED);
    }

    public boolean isMatched() {
        return this.male.nowStatus() == Status.MATCHED && this.female.nowStatus() == Status.MATCHED;
    }

    public Member checkProfileBy(Member member) {
        if (this.male.isSameStudentId(member)) {
            return female;
        }
        return male;
    }

    public void cancelBy(Member member) {
        member.changedStatus(Status.PENDING);
    }

    public void exchangedTel() {
        if (!allMeetingStatus()) {
            throw new IllegalArgumentException("상대방이 아직 만남 요청을 수락하지 않았습니다.");
        }
        else {
            this.male.setPartnerTel(this.female.getTel());
            this.female.setPartnerTel(this.male.getTel());
        }
    }

    private boolean allMeetingStatus() {
        return this.male.nowStatus() == Status.MEETING && this.female.nowStatus() == Status.MEETING;
    }

    public void acceptMeet(Member member) {
        member.changedStatus(Status.MEETING);
    }
}
