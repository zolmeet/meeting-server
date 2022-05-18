package com.zolmeet.zolmeet.domain.match;

import com.zolmeet.zolmeet.domain.member.Member;

import java.util.*;

public class MatchingHistory {

    private static final Map<Member, List<Member>> store = new HashMap<>();

    public void save(Member myself, Member partner) {

        if (store.get(myself) != null) {
            ifHasRecord(myself, partner);
            ifHasRecord(partner, myself);
        }
        else {
            ifRecordFirst(partner, myself);
            ifRecordFirst(myself, partner);
        }

    }
    public List<Member> checkOf(Member member) {
        if (store.isEmpty()) {
            return null;
        }
        return store.get(member);
    }

    private void ifHasRecord(Member myself, Member partner) {
        List<Member> myMatchingHistory = store.get(myself);
        myMatchingHistory.add(partner);
        store.put(myself, myMatchingHistory);
    }

    private void ifRecordFirst(Member partner, Member myself) {
        List<Member> myInitHistory = new ArrayList<>();
        myInitHistory.add(partner);
        store.put(myself, myInitHistory);
    }
}
