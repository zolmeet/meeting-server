package com.zolmeet.zolmeet.domain.match;

import com.zolmeet.zolmeet.domain.couple.CoupleImpl;
import com.zolmeet.zolmeet.domain.member.Member;
import com.zolmeet.zolmeet.domain.member.MemberRepository;
import com.zolmeet.zolmeet.domain.member.MemberRepositoryImpl;
import com.zolmeet.zolmeet.domain.member.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matchinglmpl implements Matching{

    private final MemberRepository memberRepository;

    public Matchinglmpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> collectCandidatesOf(Member myself) {
        List<Member> members = memberRepository.findAll();
        List<Member> candidatesList = new ArrayList<>();

        for (Member member : members) {

            if (myself.isSameStudentId(member)) {
                continue;
            }

            if (!myself.isEqualGender(member)) {
                candidatesList.add(member);
            }
        }
        return candidatesList;
    }

    @Override
    public Map<String, Member> match(Member myself) {
        List<Member> candidates = collectCandidatesOf(myself);
        Member partner = candidates.get(0); //임시 후보자 선정방식

        changeStatusToMatched(myself, partner);

        Map<String, Member> duo = new HashMap<>();
        duo.put("myself", myself);
        duo.put("partner", partner);

        return duo;

    }

    private void changeStatusToMatched(Member myself, Member partner) {
        myself.setStatus(Status.MATCHED);
        partner.setStatus(Status.MATCHED);
    }
}
