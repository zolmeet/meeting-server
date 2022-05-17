package com.zolmeet.zolmeet.domain.match;

import com.zolmeet.zolmeet.domain.member.Gender;
import com.zolmeet.zolmeet.domain.member.Member;
import com.zolmeet.zolmeet.domain.member.MemberRepository;
import com.zolmeet.zolmeet.domain.member.MemberRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static org.assertj.core.api.Assertions.assertThat;

class MatchinglmplTest {

    MemberRepository memberRepository = new MemberRepositoryImpl();
    Matching matchingService = new Matchinglmpl(memberRepository);
    @BeforeEach
    void beforeEach() {
        memberRepository.clear();
        memberRepository.save(new Member("B335129", Gender.MALE, "01014651234"));
        memberRepository.save(new Member("B331129", Gender.MALE, "01014651237"));
        memberRepository.save(new Member("B335128", Gender.FEMALE, "01014651234"));
        memberRepository.save(new Member("B335126", Gender.FEMALE, "01014151234"));
    }

    /**
     *
     *  collectCandidates()
     */

    @DisplayName("자기 자신은 candidateList 에 포함될 수 없다.")
    @Test
    void isNotMyself() {
        Member member1 = memberRepository.find(0);
        List<Member> candidateList = matchingService.collectCandidatesOf(member1);
        assertThat(member1).isNotIn(candidateList);
    }

    @DisplayName("동성은 candidateList 에 포함 될 수 없다.")
    @Test
    void isNotSameGender() {
        Member member1 = memberRepository.find(0);
        List<Member> candidateList = matchingService.collectCandidatesOf(member1);

        List<Gender> candidatesGender = candidateList.stream()
                .filter(member -> member.getGender().equals(Gender.FEMALE))
                .map(member -> member.getGender())
                .collect(Collectors.toList());

        assertThat(candidatesGender).containsOnly(Gender.FEMALE);
    }

    @DisplayName("같은 후보가 중복해서 candidateList 에 들어갈 수 없다.")
    @Test
    void name2() {
        Member member1 = memberRepository.find(0);
        List<Member> candidateList = matchingService.collectCandidatesOf(member1);

        assertThat(candidateList.size()).isEqualTo(candidateList.stream().distinct().count());
    }

    @DisplayName("커플 매칭되어야한다.")
    @Test
    void name() {
        Member member1 = memberRepository.find(0);
        Map<String, Member> couple = matchingService.match(member1);

    }
}