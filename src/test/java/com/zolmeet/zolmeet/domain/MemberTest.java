package com.zolmeet.zolmeet.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @DisplayName("회원가입을 하면 회원의 기본 상태는 JOIN 이여야 한다.")
    @Test
    void name5() {
        Member luffy = new Member("B335008", Gender.MALE);

        assertThat(luffy.nowStatus()).isEqualTo(Status.JOIN);

    }

    @DisplayName("회원의 studentId의 형식은 대문자(1개) + 숫자(6개)로 구성되어야 합니다.")
    @Test
    void name44() {
        Member member2 = new Member("1335129", Gender.MALE);

    }

    @DisplayName("회원의 studentId는 대문자(1개)로 시작해야 합니다.")
    @Test
    void studentIdFormat() {

        //올바른 학번 포멧
        Member member1 = new Member("B335129", Gender.MALE);
        assertThat(member1.isValidStudentId()).isTrue();

        //시작이 대문자 알파벳이 아닌 경우
        Member member2 = new Member("1335129", Gender.MALE);
        assertThat(member2.isValidStudentId()).isFalse();

        //끝이 6개의 숫자가 아닌경우
        Member member3= new Member("B3351291", Gender.MALE);
        assertThat(member3.isValidStudentId()).isFalse();
    }


}