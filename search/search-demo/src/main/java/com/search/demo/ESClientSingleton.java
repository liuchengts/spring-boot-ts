package com.search.demo;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 单例模式
 * 为elasticsearch提供客户client
 */
@Component
@Slf4j
public class ESClientSingleton {

    /**
     * 配置节点列表
     */
    @Value("${elasticsearch.properties.urls}")
    public String urls;

    /**
     * 集群名称
     */
    @Value("${elasticsearch.properties.cluster}")
    public String clusterName;

    /**
     * 节点端口号
     */
    @Value("${elasticsearch.properties.port}")
    public int port;

    /**
     * 解析配置文件中节点地址列表
     */
    private static List<String> staticUrlList;

    /**
     * 解析集群名称
     */
    private static String staticClusterName;

    /**
     * 解析节点端口号
     */
    private static int staticPort;

    private static TransportClient client;

    @PostConstruct
    public void init() {
        // 解析配置文件中节点地址
        if (!Strings.isNullOrEmpty(urls)) {
            staticUrlList = new ArrayList<>();
            String[] urlsArgs = urls.split(",");
            Collections.addAll(staticUrlList, urlsArgs);
        }
        staticClusterName = clusterName;
        staticPort = port;
    }

    @Bean
    @LoadBalanced
    public static TransportClient get() {
        if (client == null) {
            synchronized (ESClientSingleton.class) {
                if (client == null) {
                    Settings settings = Settings.builder().put("cluster.name", staticClusterName).build();
                    client = new PreBuiltTransportClient(settings);
                    for (String url : staticUrlList) {
                        try {
                            InetAddress inetAddress = InetAddress.getByName(url);
                            client.addTransportAddress(new InetSocketTransportAddress(inetAddress, staticPort));
                        } catch (UnknownHostException e) {
                            log.error("elasticsearch客户端构造失败!", e);
                        }
                    }
                }
                return client;
            }
        }
        return client;
    }
}
