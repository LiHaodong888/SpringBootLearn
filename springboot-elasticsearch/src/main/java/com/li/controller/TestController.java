package com.li.controller;

import com.li.elasticsearch.ElasticsearchUtil;
import com.li.elasticsearch.EsPage;
import com.li.model.Book;
import com.li.model.EsModel;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName TestController
 * @Author lihaodong
 * @Date 2018/12/20 22:30
 * @Mail lihaodongmail@163.com
 * @Description
 * @Version 1.0
 **/

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String helloTest() {
        return "Holle World";
    }


    /**
     * 测试索引
     */
    private String indexName = "entity";
    /**
     * 类型
     */
    private String esType = "book";

    /**
     * http://127.0.0.1:8080/test/createIndex
     * 创建索引
     * @param request
     * @param response
     * @return
     */
    @PutMapping("/createIndex")
    public String createIndex(HttpServletRequest request, HttpServletResponse response) {
        if (!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName);
        } else {
            return "索引已经存在";
        }
        return "索引创建成功";
    }

    /**
     * 创建索引以及类型，并给索引某些字段指定iK分词，以后向该索引中查询时，就会用ik分词。
     * @param: [request, response]
     * @return: java.lang.String
     * @auther: LHL
     * @date: 2018/10/15 17:11
     */
    @PutMapping("/createIndexTypeMapping")
    public String createIndexTypeMapping(HttpServletRequest request, HttpServletResponse response) {
        if (!ElasticsearchUtil.isIndexExist(indexName)) {
            ElasticsearchUtil.createIndex(indexName,esType);
        } else {
            return "索引已经存在";
        }
        return "索引创建成功";
    }

    @DeleteMapping("/deleteIndex")
    public String deleteIndex(String indexName, HttpServletRequest request, HttpServletResponse response) {
        boolean b = ElasticsearchUtil.deleteIndex(indexName);
        if (!b){
            return "失败";
        }
        return "索引删除成功";
    }

    /**
     * ik分词测试
     * @param: []
     * @return: void
     * @auther: LHL
     * @date: 2018/10/11 15:13
     */
    @GetMapping("getik")
    public String ikMapping(){
        String ik = ElasticsearchUtil.ik();
        return ik;
    }


    /**
     * 插入记录
     * @return
     */
    @PostMapping("/insertJson")
    public String insertJson() {
        JSONObject jsonOject = new JSONObject();
        jsonOject.put("id", DateFormatUtils.format(new Date(),"yyyyMMddhhmmss"));
        jsonOject.put("age", 25);
        jsonOject.put("name", "j-" + new Random(100).nextInt());
        jsonOject.put("date", new Date());
        String id = ElasticsearchUtil.addData(jsonOject, indexName, esType, jsonOject.getString("id"));
        return id;
    }

    /**
     * 插入记录
     * @return
     */
    @PostMapping("/insertModel")
    public String insertModel() {
        EsModel esModel = new EsModel();
        esModel.setId(DateFormatUtils.format(new Date(),"yyyyMMddhhmmss"));
        esModel.setName("m-" + new Random(100).nextInt());
        esModel.setAge(30);
        esModel.setDate(new Date());
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(esModel);
        String id = ElasticsearchUtil.addData(jsonObject, indexName, esType, jsonObject.getString("id"));
        return id;
    }

    /**
     * 删除记录
     * @return
     */
    @DeleteMapping("/delete")
    public String delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            ElasticsearchUtil.deleteDataById(indexName, esType, id);
            return "删除id=" + id;
        } else {
            return "id为空";
        }
    }

    /**
     * 更新数据
     * @return
     */
    @PutMapping("/update/{id}")
    public String update(@PathVariable("id") String id) {
        if (StringUtils.isNotBlank(id)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", id);
            jsonObject.put("age", 31);
            jsonObject.put("name", "修改");
            jsonObject.put("date", new Date());
            ElasticsearchUtil.updateDataById(jsonObject, indexName, esType, id);
            return "id=" + id;
        } else {
            return "id为空";
        }
    }

    /**
     * 获取数据
     * http://127.0.0.1:8080/test/getData/id
     * @param id
     * @return
     */
    @GetMapping("/getData/{id}")
    public String getData(@PathVariable("id") String id) {
        if (StringUtils.isNotBlank(id)) {
            Map<String, Object> map = ElasticsearchUtil.searchDataById(indexName, esType, id, null);
            return JSONObject.toJSONString(map);
        } else {
            return "id为空";
        }
    }

    /**
     * 查询数据
     * 模糊查询
     * @return
     */
    @GetMapping("/queryMatchData")
    public String queryMatchData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolean matchPhrase = false;
        if (matchPhrase == Boolean.TRUE) {
            boolQuery.must(QueryBuilders.matchPhraseQuery("name", "修改书名"));
        } else {
            boolQuery.must(QueryBuilders.matchQuery("name", "修改书名"));
        }
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 通配符查询数据
     * 通配符查询 ?用来匹配1个任意字符，*用来匹配零个或者多个字符
     * @return
     */
    @GetMapping("/queryWildcardData")
    public String queryWildcardData() {
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name.keyword", "书名*2");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 正则查询
     * @return
     */
    @GetMapping("/queryRegexpData")
    public String queryRegexpData() {
        QueryBuilder queryBuilder = QueryBuilders.regexpQuery("name.keyword", "书名[0-9]{1,7}");
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, queryBuilder, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询数字范围数据
     * @return
     */
    @GetMapping("/queryIntRangeData")
    public String queryIntRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("price").from(21.0).to(25.0)
                .includeLower(true)  // true  包含下界， false 不包含下界
                .includeUpper(false)); // true  包含下界， false 不包含下界
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询日期范围数据
     * @return
     */
    @GetMapping("/queryDateRangeData")
    public String queryDateRangeData() {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.rangeQuery("creatDate").from("2018-10-17T02:03:08.829Z").to("2018-10-17T02:03:09.727Z").includeLower(true).includeUpper(true));
        List<Map<String, Object>> list = ElasticsearchUtil.searchListData(indexName, esType, boolQuery, 10, null, null, null);
        return JSONObject.toJSONString(list);
    }

    /**
     * 查询分页  高亮   排序
     * @param startPage   第几条记录开始
     * 从0开始
     * @param pageSize    每页大小
     * @return
     */
    @GetMapping("/queryPage")
    public String queryPage(String startPage, String pageSize ,String context) {
        if (StringUtils.isNotBlank(startPage) && StringUtils.isNotBlank(pageSize)) {
            long start = System.currentTimeMillis();
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            boolQuery.must(QueryBuilders.rangeQuery("creatDate").from("2018-12-20T02:03:08.829Z").to("2018-12-21T02:03:09.727Z").includeLower(true).includeUpper(true))
                    .filter(QueryBuilders.matchQuery("name",context));
//            boolQuery.filter(QueryBuilders.matchQuery("name",context));
            EsPage list = ElasticsearchUtil.searchDataPage(indexName, esType, Integer.parseInt(startPage), Integer.parseInt(pageSize), boolQuery, null, "price", "name");
            long end = System.currentTimeMillis();
            System.out.println((end-start)/1000+"s");
            System.out.println((end-start)+"ms");
            return JSONObject.toJSONString(list);
        } else {
            return "startPage或者pageSize缺失";
        }
    }

    /**
     * 深度排序 分页  从当前页为1001开始
     * @param: [startPage, pageSize]
     * @return: com.aqh.utils.EsPage
     * @auther: LHL
     * @date: 2018/10/17 13:45
     */
    @GetMapping("/deepPageing")
    public EsPage deepPageing(int startPage, int pageSize){
        if (startPage<(10000/pageSize)){
            System.out.println("startPage需要>="+(10000/pageSize));
        }
        long start = System.currentTimeMillis();
        EsPage result = ElasticsearchUtil.deepPageing(indexName, esType, startPage, pageSize, "name");
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000+"s");
        System.out.println((end-start)+"ms");
        return result;
    }
    /**
     * 批量添加
     * @param: []
     * @return: org.elasticsearch.rest.RestStatus
     * @auther: LHL
     * @date: 2018/10/15 14:10
     */
    @PostMapping("addBulk")
    public void addBulk(){
        List<Book> bookList = Stream
                .iterate(1, i -> i + 1)
                .limit(1000000L)
                .parallel()
                .map(integer -> new Book(String.valueOf(integer), "书名" + integer, "信息" + integer, Double.valueOf(integer), new Date()))
                .collect(Collectors.toList());
        long start = System.currentTimeMillis();
        ElasticsearchUtil.bulkAddDocument(indexName, esType, bookList);
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000+"s");
        System.out.println((end-start)+"ms");
    }

    /**
     * 批量添加
     * @param: []
     * @return: void
     * @auther: LHL
     * @date: 2018/10/16 13:55
     */
    @PostMapping("/bulkProcessorAdd")
    public  void  bulkProcessorAdd (){
        List<Book> bookList = Stream
                .iterate(1, i -> i + 1)
                .limit(1000000L)
                .parallel()
                .map(integer -> new Book(String.valueOf(integer), "书名" + integer, "信息" + integer, Double.valueOf(integer), new Date()))
                .collect(Collectors.toList());
        long start = System.currentTimeMillis();
        ElasticsearchUtil.bulkProcessorAdd(indexName, esType, bookList);
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000+"s");
        System.out.println((end-start)+"ms");
    }

    /**
     * 批量删除
     * @param: []
     * @return: org.elasticsearch.rest.RestStatus
     * @auther: LHL
     * @date: 2018/10/15 14:18
     */
    @DeleteMapping("deleteBulk")
    public RestStatus deleteBulk(){
        List<String> idsList = Stream
                .iterate(1, i -> i + 1)
                .limit(1000000L)
                .parallel()
                .map(integer -> String.valueOf(integer))
                .collect(Collectors.toList());
        long start = System.currentTimeMillis();
        BulkResponse bulkItemResponses = ElasticsearchUtil.bulkDeleteDocument(indexName, esType, idsList);
        if (bulkItemResponses.hasFailures()){
            System.out.println(bulkItemResponses.buildFailureMessage());
        }
        long end = System.currentTimeMillis();
        System.out.println((end-start)/1000+"s");
        System.out.println((end-start)+"ms");
        return bulkItemResponses.status();
    }


    /**
     * 获取所有
     * @param: []
     * @return: java.lang.String
     * @auther: LHL
     * @date: 2018/10/15 15:03
     */
    @GetMapping("/getAll")
    public  List<String> getAll(){
        return ElasticsearchUtil.searchAll(indexName);
    }

}
