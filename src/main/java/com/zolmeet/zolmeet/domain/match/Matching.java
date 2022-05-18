package com.zolmeet.zolmeet.domain.match;

import com.zolmeet.zolmeet.domain.member.Member;

import java.util.List;
import java.util.Map;

public interface Matching {
    List<Member> collectCandidatesOf(Member member);
    Map<String, Member> match(Member member);
}
