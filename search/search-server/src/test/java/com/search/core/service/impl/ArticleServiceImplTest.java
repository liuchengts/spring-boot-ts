package com.search.core.service.impl;

import com.search.common.Article;
import com.search.core.service.ArticleService;
import com.search.server.SearchServerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


public class ArticleServiceImplTest extends SearchServerApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Test
    public void save() {
        articleService.save(Article.builder()
                .id(1l)
                .title("标题1")
                .abstracts("摘要1")
                .content("这是内容")
                .postTime(new Date())
                .clickCount(100l)
                .author("lc")
                .createTime(new Date())
                .updateTime(new Date())
                .build());
        findAll();
    }

    @Test
    public void delete() {
    }

    @Test
    public void findOne() {

    }

    @Test
    public void findAll() {
        Iterable<Article> articles=  articleService.findAll();
        while ( articles.iterator().hasNext()){
            System.out.println(articles.iterator().next().getAuthor());
        }
    }

    @Test
    public void findByAuthor() {
    }

    @Test
    public void findByTitle() {
    }
}