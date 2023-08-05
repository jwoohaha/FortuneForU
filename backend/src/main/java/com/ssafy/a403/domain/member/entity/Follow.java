package com.ssafy.a403.domain.member.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @EmbeddedId
    private FollowId id;

    public Follow(Member follower, Member followee) {
        this.id = new FollowId(follower, followee);
    }
}
