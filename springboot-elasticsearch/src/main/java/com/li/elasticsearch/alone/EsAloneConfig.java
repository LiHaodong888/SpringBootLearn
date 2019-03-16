package com.li.elasticsearch.alone;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName EsAloneConfig
 * @Author lihaodong
 * @Date 2018/12/24 09:59
 * @Mail lihaodongmail@163.com
 * @Description elasticsearch单机配置
 * @Version 1.0
 **/
@Configuration
public class EsAloneConfig {

    /**
     * es的构造
     * @return
     * @throws UnknownHostException
     */
    @Bean("TransportClient")
    public TransportClient client() throws UnknownHostException {

        // 机器IP 端口号
        TransportAddress node = new TransportAddress(
                InetAddress.getByName("ip"),
                9300
        );

        // 集群名称
        Settings settings = Settings.builder()
                .put("cluster.name", "es6.2")
                .build();

        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(node);
        return client;
    }

}
