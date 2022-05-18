package com.zolmeet.zolmeet.domain.couple;

import com.zolmeet.zolmeet.domain.member.*;
import com.zolmeet.zolmeet.domain.statusmanage.StatusManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoupleImplTest {

    MemberRepository memberRepository = new MemberRepositoryImpl();
    StatusManager statusManager = new StatusManager(memberRepository);
    CoupleImpl coupleService = new CoupleImpl(statusManager);

    @DisplayName("커플은 서로의 프로필을 확인할 수 있어야 한다.")
    @Test
    void shareProfile() {
        Member member = new Member("B335129", Gender.MALE, "01046577199");
        Member partner = new Member("B335128", Gender.FEMALE, "01042321133");

        partner.setProfile("안녕하세요 저는 아무개입니다.");

        member.setPartnerProfile(coupleService.checkProfile(partner));
        assertThat(member.getPartnerProfile()).isEqualTo("안녕하세요 저는 아무개입니다.");

    }

    @DisplayName("파트너가 마음에 든다면 상태를 MEETING 으로 바꿀 수 있다.")
    @Test
    void acceptPartner() {
        Member myself = new Member("B335129", Gender.MALE, "01046577199");
        statusManager.ifLikePartner(myself);

        assertThat(myself.getStatus()).isEqualTo(Status.MEETING);
    }

    @DisplayName("서로 맘에 든다면(양방이 MEETING 상태일 경우) 상대방에게 번호를 전달할 수 있어야 한다.")
    @Test
    void exchangeTel() {
        Member myself = new Member("B335129", Gender.MALE, "01046577199");
        Member partner = new Member("B335128", Gender.FEMALE, "01042321133");

        statusManager.ifLikePartner(myself);
        statusManager.ifLikePartner(partner);

        coupleService.exchangeTel(myself, partner);

        assertThat(partner.getStatus()).isEqualTo(Status.MEETING);
        assertThat(myself.getStatus()).isEqualTo(Status.MEETING);

        assertThat(partner.getPartnerTel()).isEqualTo("01046577199");
        assertThat(myself.getPartnerTel()).isEqualTo(partner.getTel());
    }

    @DisplayName("양방이 MEETING 상태가 아닌 경우에는 전화번호가 교환되지 않는다.")
    @Test
    void sole() {
        Member myself = new Member("B335129", Gender.MALE, "01046577199");
        Member partner = new Member("B335128", Gender.FEMALE, "01042321133");

        statusManager.ifLikePartner(myself);

        assertThrows(IllegalAccessError.class, () -> coupleService.exchangeTel(myself, partner));
    }
}