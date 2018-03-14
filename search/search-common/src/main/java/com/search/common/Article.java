package com.search.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "test", type = "article")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    @Id
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘要
     */
    private String abstracts;
    /**
     * 内容
     */
    private String content;
    /**
     * 发表时间
     */
    private Date postTime;
    /**
     * 点击率
     */
    private Long clickCount;
    /**
     * 作者
     */
    private String author;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
