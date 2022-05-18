package com.zolmeet.zolmeet.domain.match;

import com.zolmeet.zolmeet.domain.member.Gender;
import com.zolmeet.zolmeet.domain.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MatchingHistoryTest {

    MatchingHistory matchingHistory = new MatchingHistory();


    /**
     * MatchingHistory 에서는  store(Map<Member, List<Member>> 구조)에 매칭 내역을 저장하고 관리합니다.
     * key (자신) / value (자신과 매칭됐던 파트너들, 자신의 history)
     */

    @DisplayName("매칭 기록을 저장하면 양측 모두 매칭 기록에 상대방과 매칭했다는 정보가 저장되어야 합니다.")
    @Test
    void saveBothSide() {
        Member member1 = new Member("B335129", Gender.MALE);
        Member member2 = new Member("B335128", Gender.FEMALE);

        matchingHistory.save(member1, member2);
        List<Optional<Member>> member1MatchingHistory = matchingHistory.checkOf(member1);
        assertThat(member1MatchingHistory).contains(Optional.ofNullable(member2));

        List<Optional<Member>> member2MatchingHistory = matchingHistory.checkOf(member2);
        assertThat(member2MatchingHistory).contains(Optional.ofNullable(member1));
    }
}