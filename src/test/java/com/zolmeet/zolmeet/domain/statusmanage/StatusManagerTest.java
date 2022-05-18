package com.zolmeet.zolmeet.domain.statusmanage;

import com.zolmeet.zolmeet.domain.member.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class StatusManagerTest {

    MemberRepository memberRepository = new MemberRepositoryImpl();
    StatusManager statusManager = new StatusManager(memberRepository);

    @BeforeEach
    void beforeEach() {
        memberRepository.clear();
    }

    @DisplayName("매칭 완료(Matched) 상태가 7일 지속되면 응답 없음으로 판단하고 Member 객체를 폐기한다.")
    @Test
    void matchedStatus7DayAgo() {
        Member member = new Member("B335129", Gender.MALE, "0104655123");
        member.setStatus(Status.MATCHED);
        Status memberStatus = member.getStatus();

        for (int i = 0; i < 7; i++) {
            memberStatus.oneDayLater();
        }
        memberRepository.save(member);

        statusManager.kickOut(member);
        assertThat(memberRepository.findAll().size()).isEqualTo(0);
    }

    @DisplayName("매칭 완료를 거부하는 경우 JOIN 상태로 돌아간다.")
    @Test
    void dislikePartner() {
    }

    @DisplayName("만남이 수락되고 30일이 지난 경우 Member 객체를 폐기")
    @Test
    void meetStatus30DayAgo() {
    }
}