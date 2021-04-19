package com.sy.controller;

import com.sy.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName E404Controller
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/4/13 17:01
 * @Version 1.0
 */
@RestController
public class E404Controller {

    @RequestMapping("/404.do")
    public Result handle404(Result result){
        result.setCode(404);
        result.setMsg("NOT FOUND!!");
        return result;
    }
}
