package com.li.elasticsearch.cluster;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName ESConfig
 * @Author lihaodong
 * @Date 2018/12/19 23:57
 * @Mail lihaodongmail@163.com
 * @Description elasticsearch集群配置
 * @Version 1.0
 **/

//@Configuration
@Slf4j
public class EsClusterConfig {

    @Value("${elasticsearch.cluster.port}")
    private int esPort;

    @Value("${spring.data.elasticsearch.cluster-name}")
    private String esClusterName;


    @Bean(name = "transportClient")
    public TransportClient transportClient() {
        log.info("Elasticsearch初始化开始。。。。。");
        TransportClient transportClient = null;
        try {
            //es集群连接 ip 端口号
            TransportAddress node1 = new TransportAddress(InetAddress.getByName("ip"), esPort);
            TransportAddress node2 = new TransportAddress(InetAddress.getByName("ip"), esPort);
            //es集群配置（自定义配置） 连接自己安装的集群名称
            Settings settings = Settings.builder().put("cluster.name", esClusterName)
                    //增加嗅探机制，找到ES集群 如果报org.elasticsearch.transport.ReceiveTimeoutTransportException: 把他注释掉 即可
                    .put("client.transport.sniff", true)
                    //增加线程池个数，暂时设为5
                    .put("thread_pool.search.size", Integer.parseInt("5"))
                    .build();
            transportClient = new PreBuiltTransportClient(settings);
            transportClient.addTransportAddress(node1);
            transportClient.addTransportAddress(node2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            log.error("elasticsearch TransportClient create error!!", e);
        }
        return transportClient;
    }

}
