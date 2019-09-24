package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {
    //记录userid和friendid查询满足条件的记录数
    @Query(nativeQuery = true,value = "select count(*) from tb_friend where userid=?1 and friendid=?2")
    int selectCount(String userid,String friendid);

    //更新为互相喜欢
    @Modifying
    @Query(nativeQuery = true,value = "update tb_friend set islike=?1 where userid=?2 and friendid=?3")
    void updateLike(String islike,String userid,String friendid);

}
