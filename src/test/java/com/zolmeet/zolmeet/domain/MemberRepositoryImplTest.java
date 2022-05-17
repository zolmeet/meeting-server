package com.zolmeet.zolmeet.domain;

import com.zolmeet.zolmeet.domain.member.Gender;
import com.zolmeet.zolmeet.domain.member.Member;
import com.zolmeet.zolmeet.domain.member.MemberRepository;
import com.zolmeet.zolmeet.domain.member.MemberRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberRepositoryImplTest {

    MemberRepository memberRepository = new MemberRepositoryImpl();

    @DisplayName("멤버가 등록을 완료하면 저장소에 저장되여야 한다.")
    @Test
    void name() {
        Member member = new Member("B335129", Gender.MALE, "01014541111");
        memberRepository.save(member);

        Assertions.assertThat(member).isEqualTo(memberRepository.find(0));
    }

    @DisplayName("학번으로 멤버 찾는거 가능해야 함")
    @Test
    void findByStudentId() {
        Member member = new Member("B335129", Gender.MALE, "01014541111");
        memberRepository.save(member);

        Assertions.assertThat(member).isEqualTo(memberRepository.findByStudentId("B335129"));
    }
}