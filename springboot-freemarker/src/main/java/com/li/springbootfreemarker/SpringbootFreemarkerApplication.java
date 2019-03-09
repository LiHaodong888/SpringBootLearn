package com.li.springbootfreemarker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class SpringbootFreemarkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFreemarkerApplication.class, args);
    }

    @RequestMapping("/index")
    public String index(ModelMap modelMap){

        modelMap.addAttribute("host","www.lhdyx.cn");

        return "hello";
    }


}
