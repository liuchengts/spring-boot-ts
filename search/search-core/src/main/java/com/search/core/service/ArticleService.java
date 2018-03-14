package com.search.core.service;


import com.search.common.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 需要做些业务本项目业务逻辑后再进行db处理的接口声明
 * 当直接调用Repository层无法完成时，就使用这一层
 */
public interface ArticleService {

    Article save(Article article);

    void delete(Article article);

    Article findOne(Long id);

    Iterable<Article> findAll();

    Page<Article> findByAuthor(String author, PageRequest pageRequest);

    List<Article> findByTitle(String title);

}
