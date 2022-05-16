package com.zolmeet.zolmeet.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompletedMembersTest {

    Member male = new Member("B335129", Gender.MALE, "01046577199");
    Member female = new Member("B423132", Gender.FEMALE, "01012345678");
    PendingMember pendedMale = new PendingMember(male);
    PendingMember pendingFemale = new PendingMember(female);

    @DisplayName("매칭 완료가 된 멤버끼리는 프로필을 볼 수 있다. 남성이라면 여성의 프로필을 볼 수 있어야 한다.")
    @Test
    void name() {
        CompletedMembers completedMembers = new CompletedMembers(male, female);
        Member matchedFemale = completedMembers.checkProfileBy(male);

        assertThat(matchedFemale.isSameStudentId(female)).isTrue();
        assertThat(matchedFemale).isEqualTo(female);
    }

    /*@DisplayName("매칭 완료가 된 멤버끼리는 프로필을 볼 수 있다. 여성이라면 남성의 프로필을 볼 수 잇어야 한다.")
    @Test
    void name2() {
        CompletedMembers completedMembers = new CompletedMembers(male, female);
        Member matchedMale = completedMembers.checkProfileBy(female);

        assertThat(matchedMale.isSameStudentId(male)).isTrue();
        assertThat(matchedMale).isEqualTo(male);
    }*/

    @DisplayName("매칭이 완료된 멤버(나/상대방) 모두 매칭 상태는 MATCHED 여야 합니다.")
    @Test
    void name4() {
        CompletedMembers completedMembers = new CompletedMembers(male, female);

        assertThat(male.nowStatus()).isEqualTo(Status.MATCHED);
        assertThat(female.nowStatus()).isEqualTo(Status.MATCHED);
    }

    /**
     * cancelBy()
     */

    @DisplayName("매칭을 취소한다면 나 자신과 상대방 모두 PENDING 상태로 돌아가야 한다.")
    @Test
    void name3() {
        CompletedMembers completedMembers = new CompletedMembers(male, female);
        completedMembers.cancelBy();

        assertThat(female.nowStatus()).isEqualTo(Status.PENDING);
        assertThat(male.nowStatus()).isEqualTo(Status.PENDING);
    }

    /**
     * acceptMeet()
     */

    @DisplayName("상대방이 마음에 들면 미팅을 수락하고 자신의 상태는 MEETING 이 되어야 한다.")
    @Test
    void tempTest() {
        CompletedMembers completedMembers = new CompletedMembers(male, female);
        completedMembers.acceptMeet(male);

        assertThat(male.nowStatus()).isEqualTo(Status.MEETING);
    }

    /**
     * Test exchangedTel()
     */

    @DisplayName("양쪽이 MEETING 상태라면 매칭된 상대방의 전화번호가 교환된다.")
    @Test
    void isWorkExchangedTel() {
        CompletedMembers completedMembers = new CompletedMembers(male, female);
        completedMembers.acceptMeet(male);
        completedMembers.acceptMeet(female);

        completedMembers.exchangedTel();
        assertThat(male.getPartnerTel()).isEqualTo("01012345678");
    }

    @DisplayName("양쪽이 MEETING 상태가 아니라면 예외가 발생한다.")
    @Test
    void ifCompletedMembersNotMeetingStatus() {
        CompletedMembers completedMembers = new CompletedMembers(male, female);
        completedMembers.acceptMeet(male);
        assertThrows(IllegalArgumentException.class, () -> completedMembers.exchangedTel());

    }
}