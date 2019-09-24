package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import com.tensquare.friend.service.NoFriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by WF on 2019/9/24 11:08
 */
@RestController
@RequestMapping("friend")
public class FriendController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FriendService friendService;
    @Autowired
    private UserClient userClient;
    @Autowired
    private NoFriendService noFriendService;
    //添加好友或非好友
    @PutMapping("like/{friendid}/{type}")
    public Result add(@PathVariable String friendid, @PathVariable String type){
        //从request中得到当前用户的登录信息
        Claims claims = (Claims) request.getAttribute("claims");
        //判断是否为null
        if(claims == null){
            return new Result(false,StatusCode.ACCESSERROR,"无权访问！");
        }
        //添加好友
        if(type.equals("1")){
            //第一件事情：更新好友模块的数据
            int count = friendService.addFriend(claims.getId(),friendid);
            if(count == 0){
                return new Result(false,StatusCode.REPERROR,"重复添加好友！");
            }
            //更新用户模块的数据
            userClient.incFansCount(friendid,1);
            userClient.incFollowCount(claims.getId(),1);
        }else if(type.equals("2")){     //代表类型为：不喜欢
            //添加非好友
            noFriendService.add(claims.getId(),friendid);
        }

        return new Result(true,StatusCode.OK,"添加好友成功！");
    }
    //删除好友
    @DeleteMapping("{friendid}")
    public Result delete(@PathVariable String friendid){
        //得到请求作用域中的claims值
        Claims claims = (Claims) request.getAttribute("claims");
        //如果存在，就可以删除它
        if(claims != null){
            //好友模块要做的事情
            friendService.delete(friendid);
            //用户模块要更新粉丝数及关注数
            userClient.incFansCount(friendid,-1);
            userClient.incFollowCount(claims.getId(),-1);
            return new Result(true,StatusCode.OK,"删除成功!");
        }
        return new Result(false,StatusCode.ACCESSERROR,"无权删除!");
    }
}
