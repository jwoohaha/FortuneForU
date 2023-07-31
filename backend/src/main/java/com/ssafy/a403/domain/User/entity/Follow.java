package com.ssafy.a403.domain.User.entity;

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
    @Column(name = "touser")
    private Long toUser;

    @Id
    @Column(name="fromuser")
    private Long fromUser;

    public static class PK implements Serializable {
        Long toUser;
        Long fromUser;
    }
}
