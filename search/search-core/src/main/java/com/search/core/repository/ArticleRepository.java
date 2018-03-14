package com.search.core.repository;

import com.search.common.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by apple on 2018/1/18.
 * db操作
 */
@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
    Page<Article> findByAuthor(String author, Pageable pageable);

    List<Article> findByTitle(String title);
}
