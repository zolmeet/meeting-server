package com.zolmeet.zolmeet.domain.member;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Member {

    private final String studentId;
    private final Gender gender;
    private String tel;
    private String profile = "";
    public List<String> matchingHistory;

    private String partnerProfile;
    private String partnerTel;
    private Status status = Status.JOIN;

    public Member(String studentId, Gender gender, String tel) {
        this.studentId = studentId;
        this.gender = gender;
        this.tel = tel;
        this.matchingHistory = new ArrayList<>();
        matchingHistory.add(this.studentId);
    }

    public Member(String studentId, Gender gender) {
        this.studentId = studentId;
        this.gender = gender;
    }

    public boolean isEqualGender(Member member) {
        return this.gender == member.gender;
    }

    public boolean isSameStudentId(Member member) {
        return this.studentId.equals(member.studentId);
    }

    public String getTel() {
        return tel;
    }

    public void setPartnerTel(String partnerTel) {
        this.partnerTel = partnerTel;
    }
    public String getPartnerTel() {
        return partnerTel;
    }

    public String getStudentId() {
        return studentId;
    }

    public Status nowStatus() {
        return this.status;
    }
    public void changedStatus(Status status) {
        this.status = status;
    }

    public boolean isValidStudentId() {
        String firstString = this.studentId.substring(0, 1);
        String afterString = this.studentId.substring(1);

        return firstString.matches("[A-Z]") && afterString.matches("[0-9]{6}");
    }


}
