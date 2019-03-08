package com.li.springbootthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class SpringbootThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootThymeleafApplication.class, args);
    }


    @RequestMapping("/index")
    public String index(ModelMap modelMap){
        // 加入一个属性，模板通过这个属性读取对应的值
        modelMap.addAttribute("host","https://www.lhdyx.cn");
        // return模板文件的名称，对应src/main/resources/templates/hello.html
        return "hello";
    }
}
