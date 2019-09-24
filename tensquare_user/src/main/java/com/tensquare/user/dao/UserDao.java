package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User> {
    //根据手机号从数据库查询用户
    User findByMobile(String mobile);

    //更新粉丝数
    @Query(nativeQuery = true,value="update tb_user set fanscount=fanscount+?2 where id=?1")
    void incfans(String friendid, int x);

    //更新关注数
    @Query(nativeQuery = true,value="update tb_user set followcount=followcount+?2 where id=?1")
    @Modifying
    void incfollow(String userid, int x);
}
