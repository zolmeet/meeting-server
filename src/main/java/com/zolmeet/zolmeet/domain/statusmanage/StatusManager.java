package com.zolmeet.zolmeet.domain.statusmanage;

import com.zolmeet.zolmeet.domain.member.Member;
import com.zolmeet.zolmeet.domain.member.MemberRepository;
import com.zolmeet.zolmeet.domain.member.Status;

public class StatusManager {

    private final MemberRepository memberRepository;

    public StatusManager(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void kickOut(Member member) {
        if (member.getStatus() == Status.MATCHED && member.getStatus().getLife() == 0) {
            memberRepository.delete(member); // 이 부분이 궁금함. 삭제를 여기서 하는게 맞나
        }
    }
    public boolean equalStatus(Member myself, Member partner, Status status) {
        return myself.getStatus() != status || partner.getStatus() != status;
    }

    public void ifLikePartner(Member myself) {
        myself.setStatus(Status.MEETING);
    }

    public void successMatch(Member myself, Member partner) {
        myself.setStatus(Status.MATCHED);
        partner.setStatus(Status.MATCHED);
    }

}

