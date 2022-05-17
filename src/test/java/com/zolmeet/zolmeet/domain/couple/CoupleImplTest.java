package com.zolmeet.zolmeet.domain.couple;

import com.zolmeet.zolmeet.domain.match.Matching;
import com.zolmeet.zolmeet.domain.match.Matchinglmpl;
import com.zolmeet.zolmeet.domain.member.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoupleImplTest {

    MemberRepository memberRepository = new MemberRepositoryImpl();
    Matching matchingService = new Matchinglmpl(memberRepository);
    CoupleImpl coupleService = new CoupleImpl(matchingService);

    @BeforeEach
    void beforeEach() {
        memberRepository.clear();
        memberRepository.save(new Member("B335123", Gender.MALE, "01014651234"));
        memberRepository.save(new Member("B331129", Gender.MALE, "01014651237"));
        memberRepository.save(new Member("B335128", Gender.FEMALE, "01014651234"));
        memberRepository.save(new Member("B335126", Gender.FEMALE, "01014151234"));
    }

    @DisplayName("커플은 서로의 프로필을 확인할 수 있어야 한다.")
    @Test
    void shareProfile() {
        Member member = new Member("B335129", Gender.MALE, "01046577199");
        Map<String, Member> couple = matchingService.match(member);
        Member partner = couple.get("partner");

        assertThat(coupleService.checkProfile(partner)).isEqualTo(partner.getProfile());

    }

    @DisplayName("파트너가 마음에 든다면 상태를 MEETING 으로 바꿀 수 있다.")
    @Test
    void acceptPartner() {
        Member myself = new Member("B335129", Gender.MALE, "01046577199");
        coupleService.iflikePartner(myself);

        assertThat(myself.getStatus()).isEqualTo(Status.MEETING);
    }

    @DisplayName("서로 맘에 든다면 상대방에게 번호를 전달할 수 있어야 한다.")
    @Test
    void exchangeTel() {
        Member member = new Member("B335129", Gender.MALE, "01046577199");
        Map<String, Member> couple = matchingService.match(member);

        Member myself = couple.get("myself");
        Member partner = couple.get("partner");

        coupleService.iflikePartner(myself);
        coupleService.iflikePartner(partner);

        coupleService.exchangeTel(myself, partner);
        assertThat(partner.getPartnerTel()).isEqualTo("01046577199");
        assertThat(myself.getPartnerTel()).isEqualTo(partner.getTel());
    }

    @DisplayName("한쪽만 MEETING일 경우에는 전화번호가 교환되지 않는다.")
    @Test
    void sole() {
        Member member = new Member("B335129", Gender.MALE, "01046577199");
        Map<String, Member> couple = matchingService.match(member);

        Member myself = couple.get("myself");
        Member partner = couple.get("partner");

        coupleService.iflikePartner(myself);

        assertThrows(IllegalAccessError.class, () -> coupleService.exchangeTel(myself, partner));
    }
}