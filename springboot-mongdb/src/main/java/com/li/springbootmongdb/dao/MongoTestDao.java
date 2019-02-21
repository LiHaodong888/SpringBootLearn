package com.li.springbootmongdb.dao;

import com.li.springbootmongdb.model.MongoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class MongoTestDao {


    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     */
    public void saveTest(MongoTest test) {
        // insert: 若新增数据的主键已经存在，则会抛 org.springframework.dao.DuplicateKeyException 异常提示主键重复，不保存当前数据
        // save: 若新增数据的主键已经存在，则会对当前已经存在的数据进行修改操作。
        mongoTemplate.insert(test);
    }

    /**
     * 根据用户名查询对象
     *
     * @return
     */
    public MongoTest findTestByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        MongoTest mgt = mongoTemplate.findOne(query, MongoTest.class);
        return mgt;
    }

    /**
     * 更新对象
     */
    public void updateTest(MongoTest test) {
        Query query = new Query(Criteria.where("id").is(test.getId()));
        Update update = new Update().set("age", test.getAge()).set("name", test.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, MongoTest.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,TestEntity.class);
    }

    /**
     * 删除对象
     *
     * @param id
     */
    public void deleteTestById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, MongoTest.class);

    }

    // 去重操作
    public void distinct() {
        // 返回list集合，是去重后的结果
        Query query = new Query(Criteria.where("age").is(11));
        List<MongoTest> distinct = mongoTemplate.findDistinct(query, "age", "mongo_test", MongoTest.class);
        System.out.println(distinct);
        System.out.println(distinct.size());

        List<MongoTest> mongoTestList = mongoTemplate.find(Query.query(Criteria.where("create_time")
                .gte(getStartTime().getTime())
                .lte(getEndTime().getTime())), MongoTest.class);
        System.out.println(mongoTestList);
        System.out.println(mongoTestList.size());


        Aggregation agg = Aggregation.newAggregation(
                //Aggregation.match(Criteria.where("groupId").is(5)),
                Aggregation.group("create_time").count().as("age"));
        List<MongoTest> list = mongoTemplate.aggregate(agg, "mongo_test", MongoTest.class).getMappedResults();
        for (MongoTest count : list) {
            System.out.println(count);
            System.out.println(count.getAge());
        }

    }

    private static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format = df.format(todayStart.getTime());
        return todayStart.getTime();
    }

    private static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }


}