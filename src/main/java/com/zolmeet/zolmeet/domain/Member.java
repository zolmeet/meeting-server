package com.zolmeet.zolmeet.domain;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private final String studentId;
    private final Gender gender;
    private String tel;
    public List<String> matchingHistory = new ArrayList<>();

    private String partnerTel;
    private Status status = Status.JOIN;

    public Member(String studentId, Gender gender, String tel) {
        this.studentId = studentId;
        this.gender = gender;
        this.tel = tel;

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

    public boolean canCheckProfile(Member female) {
        return true;
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
