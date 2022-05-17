package com.zolmeet.zolmeet.domain.couple;

import com.zolmeet.zolmeet.domain.match.Matching;
import com.zolmeet.zolmeet.domain.member.Member;
import com.zolmeet.zolmeet.domain.member.Status;

public class CoupleImpl {

    private final Matching matchingService;

    public CoupleImpl(Matching matchingService) {
        this.matchingService = matchingService;
    }

    public void exchangeTel(Member myself, Member partner) {
        if (notYetStatusMeeting(myself, partner)) {
            throw new IllegalAccessError("아직 모두가 만남을 수락한 상태가 아니기 때문에 번호 교환에 실패합니다.");
        }
        myself.setPartnerTel(partner.getTel());
        partner.setPartnerTel(myself.getTel());

    }
    private boolean notYetStatusMeeting(Member myself, Member partner) {
        return myself.getStatus() != Status.MEETING || partner.getStatus() != Status.MEETING;
    }

    public String checkProfile(Member partner) {
        return partner.getProfile();
    }

    public void iflikePartner(Member myself) {
        myself.setStatus(Status.MEETING);
    }
}
