package com.zolmeet.zolmeet.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CompletedMatchingTest {

    @DisplayName("매칭 완료가 된 멤버끼리는 프로필을 볼 수 있다.")
    @Test
    void name() {
        Member male = new Member("a", Gender.MALE);
        Member female = new Member("b", Gender.FEMALE);
        CompletedMatching completedMatching = new CompletedMatching(male, female);

        Member matchedFemale = completedMatching.checkProfileBy(male);

        assertThat(matchedFemale.isSameStudentId(female)).isTrue();
    }

    @DisplayName("매칭 완료가 된 멤버끼리는 프로필을 볼 수 있다. 2")
    @Test
    void name2() {
        Member male = new Member("a", Gender.MALE);
        Member female = new Member("b", Gender.FEMALE);
        CompletedMatching completedMatching = new CompletedMatching(male, female);

        Member matchedMale = completedMatching.checkProfileBy(female);

        assertThat(matchedMale.isSameStudentId(male)).isTrue();
    }
}