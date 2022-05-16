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
    void name() {
        Member male1 = new Member("B335129", Gender.MALE);
        Member male2 = new Member("B300000", Gender.FEMALE);

        PendingMember NotPendingMember1 = new PendingMember(male1);
        PendingMember NotPendingMember2 = new PendingMember(male2);

        CompletedMembers completedMembers = new CompletedMembers(male1, male2);

        assertThrows(IllegalArgumentException.class, () -> NotPendingMember1.match(NotPendingMember2));


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