package com.ssafy.a403.domain.member.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"to_user", "from_user"})
)
@IdClass(Follow.PK.class)
public class Follow {

    @Id
    @Column(name = "to_user")
    private Long toUser;

    @Id
    @Column(name="from_user")
    private Long fromUser;

    public static class PK implements Serializable {
        Long toUser;
        Long fromUser;
    }
}
