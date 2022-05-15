package com.zolmeet.zolmeet.domain;

public class Member {

    private final String studentId;
    private final Gender gender;


    public Member(String studentId, Gender gender) {
        this.studentId = studentId;
        this.gender = gender;
    }

    public boolean isEqualGender(Member member) {
        return this.gender == member.gender;
    }

    public boolean isSameStudentId(Member otherMember) {
        return this.studentId.equals(otherMember.studentId);
    }

    public boolean canCheckProfile(Member female) {
        return true;
    }
}
