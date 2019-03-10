package com.wanwj1.xw.controller;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//页面跳转
@EnableAutoConfiguration
//@RestController//json格式输出
public class IndexController {
	private Logger logger = Logger.getLogger(this.getClass());	//log4j日志

    @RequestMapping({"/","/index"})
    public String index(Model model){
    	model.addAttribute("hello","wellcome");
    	logger.info("/index");
        return"/index";
    }
}
