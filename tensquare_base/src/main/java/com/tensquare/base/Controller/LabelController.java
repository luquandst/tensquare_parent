package com.tensquare.base.Controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询所有的标签
     * @return
     */
    @GetMapping
    public Result findAll(){
        //查询所有的标签
        List<Label> list = labelService.findAll();
        //返回结果
       return new Result(true,StatusCode.OK,"查询所有",list) ;
    }

    /**
     * 根据id查询表标签id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable String id){
        Label label = labelService.findOne(id);
        return new Result(true,StatusCode.OK,"根据id查询",label);
    }

    /**
     * 添加标签
     * @param label
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result addLabel(@RequestBody Label label){
        //添加标签
        labelService.addLabel(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 更新标签
     * @param id
     * @param label
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@PathVariable String id,@RequestBody Label label){
        //设置label的id为传入的id
        label.setId(id);
        //更新label
        labelService.update(label);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    /**
     * 根据id进行删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        labelService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 根据条件进行查询
     * @param label
     * @return
     */
    @PostMapping("search")
    public Result search(@RequestBody Label label){
        //根据条件进行查询
        List<Label> labelList = labelService.search(label);
        //返回查询结果
        return new Result(true,StatusCode.OK,"条件查询成功",labelList);
    }

    /**
     * 根据查询条件进行分页查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    @PostMapping("search/{page}/{size}")
    public Result search(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
        //根据查询条件进行分页查询
        PageResult pageResult = labelService.search(label,page,size);
        //返回结果到页面
        return new Result(true,StatusCode.OK,"条件分页查询成功",pageResult);
    }


}
