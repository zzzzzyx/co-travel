package cn.edu.nju.co_travel.controller;

import cn.edu.nju.co_travel.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
@ComponentScan(basePackages={"cn.edu.nju.co_travel.service"})
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping(value="/test",method= RequestMethod.GET)
    @ResponseBody
    public String testHelloCoTravel() {
        return searchService.getHelloText();
    }

}


