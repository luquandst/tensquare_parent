package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("tensquare-base")
public interface LabelClient {

    //根据id查询标签
    @GetMapping("/label/{id}")
    public Result findOne(@PathVariable("id") String id);
}
