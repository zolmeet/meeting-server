package com.zolmeet.zolmeet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private final String studentId;
    private final Gender gender;
    private final String tel;
    private String profile;

    private String partnerProfile;
    private String partnerTel;
    private Status status = Status.JOIN;

    public Member(String studentId, Gender gender, String tel) {
        this.studentId = studentId;
        this.gender = gender;
        this.tel = tel;
    }

    public boolean isSameStudentId(Member member) {
        return this.studentId.equals(member.studentId);
    }

    public boolean isEqualGender(Member member) {
        return this.gender == member.gender;
    }

    /*public boolean isValidStudentId() {
        String firstString = this.studentId.substring(0, 1);
        String afterString = this.studentId.substring(1);

        return firstString.matches("[A-Z]") && afterString.matches("[0-9]{6}");
    }*/


}
