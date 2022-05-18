package com.zolmeet.zolmeet.domain.couple;

import com.zolmeet.zolmeet.domain.member.Member;

public class Couple {
    Member male;
    Member female;

    public Couple(Member male, Member female) {
        this.male = male;
        this.female = female;
    }
}
