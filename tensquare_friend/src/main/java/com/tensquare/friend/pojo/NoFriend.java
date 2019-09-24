package com.tensquare.friend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;


@IdClass(NoFriend.class)
@Slf4j
@Entity
@Table(name = "no_friend")
public class NoFriend implements Serializable {
    @Id
    private String userid;
    @Id
    private String friendid;

    public NoFriend() {
    }

    public NoFriend(String userid, String friendid) {
        this.userid = userid;
        this.friendid = friendid;
    }
}
