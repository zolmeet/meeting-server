package com.zolmeet.zolmeet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryImplTest {

    MemberRepository memberRepository = new MemberRepositoryImpl();

    @BeforeEach
    void beforeEach() {
        memberRepository.clear();
    }
    @DisplayName("멤버가 등록을 완료하면 저장소에 저장되여야 한다.")
    @Test
    void name() {
        Member member = new Member("B335129", Gender.MALE, "01014541111");
        memberRepository.save(member);

        assertThat(member).isEqualTo(memberRepository.findById(0));
    }

    @DisplayName("학번으로 멤버 찾는거 가능해야 함")
    @Test
    void findByStudentId() {
        Member member = new Member("B335129", Gender.MALE, "01014541111");
        memberRepository.save(member);

        assertThat(member).isEqualTo(memberRepository.findByStudentId("B335129"));
    }

    @DisplayName("삭제 가능해야함")
    @Test
    void delete() {
        Member member = new Member("B335129", Gender.MALE, "01014541111");
        Member member2 = new Member("B335128", Gender.MALE, "01014541114");

        memberRepository.save(member);
        memberRepository.save(member2);
        memberRepository.delete(member);

        assertThat(member).isNotIn(memberRepository.findAll());
    }

    @DisplayName("업데이트 가능해야함")
    @Test
    void updateData() {
        Member member = new Member("B335129", Gender.MALE, "01014541111");

        Assertions.assertThat(member.getStatus()).isEqualTo(Status.JOIN);

        memberRepository.save(member);
        member.setStatus(Status.MATCHED);
        memberRepository.update("B335129", member);

        Assertions.assertThat(member.getStatus()).isEqualTo(Status.MATCHED);
    }

    @DisplayName("멤버로 찾는것도 가능해야함")
    @Test
    void findByMember() {
        Member member = new Member("B335129", Gender.MALE, "01014541111");
        memberRepository.save(member);

        assertThat(member).isEqualTo(memberRepository.findByMember(member));
    }
}