package com.zolmeet.zolmeet.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PendingMatchingTest {

    @DisplayName("멤버가 매칭 시작을 하면, 매칭의 상태는 PENDING 이다.")
    @Test
    void name() {
        Member requestMember = new Member("a", Gender.MALE);
        PendingMatching matching = new PendingMatching(requestMember);

        assertThat(matching.isPending()).isTrue();
    }

    @DisplayName("매칭 시작을 누른 두 멤버의 매칭을 연결할 수 있다. 연결되면 매칭의 상태는 COMPLETED 이다.")
    @Test
    void name2() {
        Member male = new Member("a", Gender.MALE);
        PendingMatching maleMatching = new PendingMatching(male);

        Member female = new Member("a", Gender.FEMALE);
        PendingMatching pendingMatching = new PendingMatching(female);

        CompletedMatching completedMatching = maleMatching.match(pendingMatching);

        assertThat(completedMatching.isMatched()).isTrue();
    }

    @DisplayName("동일한 사람과 매칭을 시도하면 예외가 발생한다.")
    @Test
    void name3() {
        Member male = new Member("a", Gender.MALE);
        PendingMatching maleMatching = new PendingMatching(male);

        assertThrows(IllegalArgumentException.class, () -> maleMatching.match(maleMatching));
    }

    @DisplayName("동일 성별끼리 매칭되면 예외가 발생한다.")
    @Test
    void name4() {
        Member male1 = new Member("a", Gender.MALE);
        Member male2 = new Member("a", Gender.MALE);

        PendingMatching male1Matching = new PendingMatching(male1);
        PendingMatching male2Matching = new PendingMatching(male2);

        assertThrows(IllegalArgumentException.class, () -> male1Matching.match(male2Matching));
    }

}