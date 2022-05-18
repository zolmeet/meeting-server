package com.zolmeet.zolmeet.domain.match;


import com.zolmeet.zolmeet.domain.member.Member;
import com.zolmeet.zolmeet.domain.member.MemberRepository;
import com.zolmeet.zolmeet.domain.member.Status;
import com.zolmeet.zolmeet.domain.statusmanage.StatusManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matchinglmpl implements Matching{

    private final MemberRepository memberRepository;
    private final MatchingHistory matchingHistory;
    private final StatusManager statusManager;

    public Matchinglmpl(MemberRepository memberRepository, MatchingHistory matchingHistory, StatusManager statusManager) {
        this.memberRepository = memberRepository;
        this.matchingHistory = matchingHistory;
        this.statusManager = statusManager;
    }

    @Override
    public List<Member> collectCandidatesOf(Member myself) {
        List<Member> members = memberRepository.findAll();

        List<Member> candidatesList = new ArrayList<>();

        for (Member candidate : members) {

            if (myself.isSameStudentId(candidate)) {
                continue;
            }

            if (myself.isEqualGender(candidate)) {
                continue;
            }
            if (matchingHistory.checkOf(myself) == null) {
                //System.out.println("매칭 이력이 전무해서 넣는다.");
                candidatesList.add(candidate);
            }
            else if (!matchingHistory.checkOf(myself).contains(candidate)) {
                //System.out.println("해당 파트너와 매칭된 적이 없어서 넣는다.");
                candidatesList.add(candidate);
            }

        }
        return candidatesList;
    }

    @Override
    public Map<String, Member> match(Member myself) {
        List<Member> candidates = collectCandidatesOf(myself);
        Member partner = candidates.get(0); //임시 후보자 선정방식 >> 향후 이상형을 기반으로 선택

        statusManager.successMatch(myself, partner);
        matchingHistory.save(myself, partner);

        Map<String, Member> couple = new HashMap<>();
        couple.put("myself", myself);
        couple.put("partner", partner);

        return couple;

    }
}
