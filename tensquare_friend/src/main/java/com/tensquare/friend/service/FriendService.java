package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;

    //添加好友
    @Transactional
    public int addFriend(String userid, String friendid) {
        //根据用户id及friendid查询是否添加过此好友，如果添加过就返回0
        int count = friendDao.selectCount(userid, friendid);
        if (count > 0) return 0;
        //添加好友到tb_friend表中
        Friend friend = new Friend(userid, friendid);
        friend.setIslike("0");
        //保存好友到数据库
        friendDao.save(friend);
        //查询是否添加过好友
        count = friendDao.selectCount(friendid, userid);
        if (count > 0) {
            friendDao.updateLike("1", userid, friendid); //更新自己
            friendDao.updateLike("1", friendid, userid); //更新对方
        }
        return 1;
    }

    //删除好友
    public void delete(String friendid) {
        friendDao.deleteById(friendid);
    }
}
