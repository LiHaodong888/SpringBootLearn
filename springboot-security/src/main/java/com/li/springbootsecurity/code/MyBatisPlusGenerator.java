package com.li.springbootsecurity.code;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


/**
 * @Author 李号东
 * @Description mybatis-plus自动生成
 * @Date 08:07 2019-03-17
 * @Param 
 * @return 
 **/
public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //1. 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("/Volumes/李浩东的移动硬盘/LiHaodong/springboot-security/src/main/java");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setBaseResultMap(true);//生成基本的resultMap
        gc.setBaseColumnList(false);//生成基本的SQL片段
        gc.setAuthor("lihaodong");// 作者
        mpg.setGlobalConfig(gc);

        //2. 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/test");
        mpg.setDataSource(dsc);

        //3. 策略配置globalConfiguration中
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix("");// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setSuperEntityClass("com.li.springbootsecurity.model");
        strategy.setInclude("role"); // 需要生成的表
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        //4. 包名策略配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.li.springbootsecurity");
        pc.setEntity("model");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();

    }
}
