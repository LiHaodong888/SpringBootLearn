package com.li.elasticsearch;/**
 * @author lihaodong
 * @create 2018-12-20 00:06
 * @desc
 **/

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ClassName ESClient
 * @Author lihaodong
 * @Date 2018/12/20 00:06
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/
@Slf4j
@Configuration
public class ESClient {
    @Autowired
    private TransportClient transportClient;
    private static TransportClient client;

    /**
     * @PostContruct 是spring容器初始化的时候执行该方法 * @param: [] * @return: void * @auther: LHL * @date: 2018/10/16 14:19
     */
    @PostConstruct
    public void init() {
        client = this.transportClient;
    }

    @Bean("bulkProcessor")
    public BulkProcessor bulkProcessor() {
        log.info("bulkProcessor初始化开始。。。。。");
        return BulkProcessor.builder(client, new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long l, BulkRequest bulkRequest) {
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {
                log.error("{} data bulk failed,reason :{}", bulkRequest.numberOfActions(), throwable);
            }
        }).setBulkActions(10000)
                // 批量导入个数
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
                // 满5MB进行导入
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                // 冲刷间隔
                .setConcurrentRequests(3)
                // 并发数
                .setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueSeconds(1), 3))
                // 重试3次，间隔1s
                .build();
    }
}

