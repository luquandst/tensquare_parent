package com.tensquare.friend.service;

import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoFriendService {
    @Autowired
    private NoFriendDao noFriendDao;
    //添加非好友
    public void add(String id, String friendid) {
        NoFriend nofriend = new NoFriend(id,friendid);
        //保存好友到数据库
        noFriendDao.save(nofriend);
    }
}
