package com.zolmeet.zolmeet.domain.couple;

import com.zolmeet.zolmeet.domain.member.Member;
import com.zolmeet.zolmeet.domain.member.Status;
import com.zolmeet.zolmeet.domain.statusmanage.StatusManager;

public class CoupleImpl {

    StatusManager statusManager;

    public CoupleImpl(StatusManager statusManager) {
        this.statusManager = statusManager;
    }

    public String checkProfile(Member partner) {
        return partner.getProfile();
    }

    public void exchangeTel(Member myself, Member partner) {
        if (statusManager.equalStatus(myself, partner, Status.MEETING)) {
            throw new IllegalAccessError("아직 모두가 만남을 수락한 상태가 아니기 때문에 번호 교환에 실패합니다.");
        }
        myself.setPartnerTel(partner.getTel());
        partner.setPartnerTel(myself.getTel());
    }
}
