package com.zolmeet.zolmeet.domain;

public class CompletedMatching {

    private final Member male;
    private final Member female;

    public CompletedMatching(Member male, Member female) {
        this.male = male;
        this.female = female;
    }

    public boolean isMatched() {
        return true;
    }

    public Member checkProfileBy(Member otherMember) {
        if (this.male.isSameStudentId(otherMember)) {
            return female;
        }
        return male;
    }
}
