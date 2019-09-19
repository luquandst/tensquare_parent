package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    //查询所有的吐槽
    @GetMapping()
    public Result findAll(){
        try {
            //查询所有的吐槽
            List<Spit> spitList = spitService.findAll();
            //返回结果
            return  new Result(true,StatusCode.OK,"查询成功",spitList);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,StatusCode.ERROR,"查询失败");
        }
    }

    //添加吐槽
    @PostMapping()
    public Result addSpit(@RequestBody Spit spit){
        try {
            //添加吐槽
            spitService.addSpit(spit);
            return new Result(true,StatusCode.OK,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"添加失败");
        }
    }

    //根据id查询吐槽的信息
    @GetMapping("/{spitid}")
    public Result findOne(@PathVariable String spitid){
        try {
            //根据id查询吐槽的信息
            Spit spit = spitService.findOne(spitid);
            //返回成功后的结果
            return new Result(true,StatusCode.OK,"查询成功",spit);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"查询失败");
        }
    }
    //修改吐槽信息
    @PutMapping("/{spitid}")
    public Result updateSpit(@RequestBody Spit spit,@PathVariable String spitid){
        try {
            //修改吐槽的信息
            spitService.updateSpit(spit,spitid);
            return new Result(true,StatusCode.OK,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"修改失败");
        }
    }
    //根据id进行删除
    @DeleteMapping("/{spitid}")
    public Result delete(@PathVariable String spitid){
        try {
            //根据id进行删除
            spitService.delete(spitid);
            return new Result(true,StatusCode.OK,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"删除失败");
        }
    }
    //根据上级id分页查询吐槽
    @GetMapping("/comment/{parentId}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentId,@PathVariable int page,@PathVariable int size){
        try {
            //根据上级id查询,并进行分页展示
            PageResult pageResult = spitService.findByParentId(parentId,page,size);
            return new Result(true,StatusCode.OK,"查询成功",pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"查询失败");
        }
    }

    //吐槽点赞,更新点赞的数量
    @PutMapping("/thumbup/{spitId}")
    public Result updateThumbup(@PathVariable String spitId){
        try {
            //修改点赞数量
            spitService.updateThumbup(spitId);
            return new Result(true,StatusCode.OK,"点赞成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"点赞失败");
        }
    }
}
