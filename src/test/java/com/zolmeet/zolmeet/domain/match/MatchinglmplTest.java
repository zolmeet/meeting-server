package com.zolmeet.zolmeet.domain.match;

import com.zolmeet.zolmeet.domain.member.Gender;
import com.zolmeet.zolmeet.domain.member.Member;
import com.zolmeet.zolmeet.domain.member.MemberRepository;
import com.zolmeet.zolmeet.domain.member.MemberRepositoryImpl;
import com.zolmeet.zolmeet.domain.statusmanage.StatusManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static org.assertj.core.api.Assertions.assertThat;

class MatchinglmplTest {

    MemberRepository memberRepository = new MemberRepositoryImpl();
    MatchingHistory matchingHistory = new MatchingHistory();
    StatusManager statusManager = new StatusManager(memberRepository);
    Matching matchingService = new Matchinglmpl(memberRepository, matchingHistory, statusManager);

    @BeforeEach
    void beforeEach() {
        memberRepository.clear();
        memberRepository.save(new Member("B335129", Gender.MALE, "01014651234"));
        memberRepository.save(new Member("B331129", Gender.MALE, "01014651237"));
        memberRepository.save(new Member("B335128", Gender.FEMALE, "01014651234"));
        memberRepository.save(new Member("B335126", Gender.FEMALE, "01014151234"));
    }

    /**
     *  collectCandidates()
     */

    @DisplayName("자기 자신은 candidateList 에 포함될 수 없다.")
    @Test
    void isNotMyself() {
        Member member1 = memberRepository.findById(0);
        List<Member> candidateList = matchingService.collectCandidatesOf(member1);
        assertThat(member1).isNotIn(candidateList);
    }

    @DisplayName("동성은 candidateList 에 포함 될 수 없다.")
    @Test
    void isNotSameGender() {
        Member member1 = memberRepository.findById(0);
        List<Member> candidateList = matchingService.collectCandidatesOf(member1);

        List<Gender> candidatesGender = candidateList.stream()
                .filter(member -> member.getGender().equals(Gender.FEMALE))
                .map(member -> member.getGender())
                .collect(Collectors.toList());

        assertThat(candidatesGender).containsOnly(Gender.FEMALE);
    }

    @DisplayName("같은 후보가 중복해서 candidateList 에 들어갈 수 없다.")
    @Test
    void isNotReduplication() {
        Member member1 = memberRepository.findById(0);
        List<Member> candidateList = matchingService.collectCandidatesOf(member1);

        assertThat(candidateList.size()).isEqualTo(candidateList.stream().distinct().count());
    }

    @DisplayName("이전에 매칭된 기록이 있는 상대방은 candidateList 에 들어갈 수 없습니다.")
    @Test
    void isNotExPartner() {

        Member member1 = memberRepository.findById(0);

        // member1의 매칭 파트너 partner
        Map<String, Member> memberMap = matchingService.match(member1);
        Member partner = memberMap.get("partner");

        //member1이 매칭을 취소, 이후 새롭게 매칭을 시도
        List<Member> newCandidateList = matchingService.collectCandidatesOf(member1);

        //새로운 매칭 후보자에는 partner 가 포함되면 않아야된다.
        assertThat(partner).isNotIn(newCandidateList);
    }

    // 진행중
    @DisplayName("커플 매칭되어야한다.")
    @Test
    void name() {
        Member member1 = memberRepository.findById(0);
        Map<String, Member> couple = matchingService.match(member1);

    }
}