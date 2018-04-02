package com.search.demo.controllers;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author liucheng
 * @create 2018-03-14 14:23
 **/
@RestController
public class TestController {
    @Autowired
    private TransportClient client;

    @GetMapping("/search")
    public Object search(@RequestParam String content) {
        //要查询的索引 对应数据库
        return client.prepareSearch("test")
                //索引类型是article  对应数据库表
                .setTypes("article")
                //查询作者是lc的
                .setQuery(QueryBuilders.termQuery("author", "lc"))
                //过滤掉点击量不在1到10以内的 包含临界值
                .setPostFilter(QueryBuilders.rangeQuery("click_count").from(1).to(10))
                .get();
    }
}
