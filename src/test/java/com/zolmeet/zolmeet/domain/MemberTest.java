package com.zolmeet.zolmeet.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @DisplayName("두 회원의 성별을 비교할 수 있어야 한다. 성별이 같으면 true 를 리턴한다.")
    @Test
    void name() {
        Member member1 = new Member("a", Gender.MALE);
        Member member2 = new Member("a", Gender.MALE);

        assertThat(member1.isEqualGender(member2)).isTrue();
    }

    @DisplayName("두 회원의 성별을 비교할 수 있어야 한다. 성별이 다르면 false 를 리턴한다.")
    @Test
    void name2() {
        Member member1 = new Member("a", Gender.MALE);
        Member member2 = new Member("a", Gender.FEMALE);

        assertThat(member1.isEqualGender(member2)).isFalse();
    }

    @DisplayName("학번이 같으면 회원을 비교했을 때, true 를 리턴한다.")
    @Test
    void name3() {
        Member mincheol = new Member("B335008", Gender.MALE);
        Member luffy = new Member("B335008", Gender.MALE);

        assertThat(mincheol.isSameStudentId(luffy)).isTrue();
    }

    @DisplayName("학번이 다르면 회원을 비교했을 때, false 를 리턴한다.")
    @Test
    void name4() {
        Member leesin = new Member("B335129", Gender.MALE);
        Member luffy = new Member("B335008", Gender.MALE);

        assertThat(leesin.isSameStudentId(luffy)).isFalse();
    }
}