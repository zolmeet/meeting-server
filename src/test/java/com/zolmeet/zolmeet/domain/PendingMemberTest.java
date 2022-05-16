package com.zolmeet.zolmeet.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PendingMemberTest {

    @DisplayName("멤버가 매칭 시작 버튼을 누르면 멤버의 매칭의 상태는 PENDING이다.")
    @Test
    void isPending() {
        Member requestMember = new Member("a", Gender.MALE);
        PendingMember pendingMember = new PendingMember(requestMember);

        assertThat(pendingMember.isPending()).isTrue();
    }

    /**
     * match()
     */

    @DisplayName("동일한 사람과 매칭을 시도한다면 예외가 발생한다.")
    @Test
    void name3() {
        Member male = new Member("a", Gender.MALE);
        PendingMember member = new PendingMember(male);

        assertThrows(IllegalArgumentException.class, () -> member.match(member));
    }

    @DisplayName("동일 성별과 매칭을 시도한다면 예외가 발생한다.")
    @Test
    void name4() {
        Member male1 = new Member("a", Gender.MALE);
        Member male2 = new Member("a", Gender.MALE);

        PendingMember maleMember1 = new PendingMember(male1);
        PendingMember maleMember2 = new PendingMember(male2);

        assertThrows(IllegalArgumentException.class, () -> maleMember1.match(maleMember2));
    }

    @DisplayName("한쪽이라도 PENDING 이 아닌 상태에서 매칭을 시도한다면 예외가 발생한다.")
    @Test
    void name5() {
        Member member1 = new Member("B335129", Gender.MALE);
        Member member2 = new Member("B300000", Gender.FEMALE);

        PendingMember NotPendingMember1 = new PendingMember(member1);
        PendingMember NotPendingMember2 = new PendingMember(member2);

        CompletedMembers completedMembers = new CompletedMembers(member1, member2);

        assertThrows(IllegalArgumentException.class, () -> NotPendingMember1.match(NotPendingMember2));
    }

    @DisplayName("매칭이 성사되면 MatchingHistory에 상대방의 StudentId가 저장되어야 한다.")
    @Test
    void name() {
        Member member1 = new Member("B335129", Gender.MALE, "01012341234");
        Member member2 = new Member("B300000", Gender.FEMALE, "01012341235");
        PendingMember pendingMember1 = new PendingMember(member1);
        PendingMember pendingMember2 = new PendingMember(member2);

        pendingMember1.match(pendingMember2);
        assertThat(member1.matchingHistory.contains("B300000")).isTrue();
    }

    @DisplayName("매칭된적이 있는 이성과 다시 매칭된다면 예외가 발생한다.")
    @Test
    void name6() {
        Member member1 = new Member("B335129", Gender.MALE);
        Member member2 = new Member("B300000", Gender.FEMALE);
        PendingMember pendingMember1 = new PendingMember(member1);
        PendingMember pendingMember2 = new PendingMember(member2);

        CompletedMembers completedMembers = pendingMember1.match(pendingMember2);

        completedMembers.cancelBy();

        assertThrows(IllegalArgumentException.class, () -> pendingMember1.match(pendingMember2));
    }

    @DisplayName("매칭 시작을 누른 두 멤버의 매칭을 연결할 수 있다. 연결되면 매칭의 상태는 COMPLETED 이다.")
    @Test
    void name2() {
        Member male = new Member("a", Gender.MALE);
        PendingMember pendingMember_male = new PendingMember(male);

        Member female = new Member("b", Gender.FEMALE);
        PendingMember pendingMember_female = new PendingMember(female);

        CompletedMembers completedMembers = pendingMember_male.match(pendingMember_female);

        assertThat(completedMembers.isMatched()).isTrue();
        assertThat(male.nowStatus()).isEqualTo(Status.MATCHED);
        assertThat(female.nowStatus()).isEqualTo(Status.MATCHED);
    }
}