package com.zolmeet.zolmeet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Status {
    JOIN("가입 완료/매칭 진행 중", 0),
    MATCHED("상대방이 매칭된 상태",7),
    MEETING("상대방과의 만남은 수락한 상태", 30),
    END("폐기", 0);

    private final String description;
    private Integer life;

    Status(String description, Integer life) {
        this.description = description;
        this.life = life;
    }

    public void oneDayLater() {
        this.life -= 1;
    }


}

