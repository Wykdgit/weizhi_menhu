package com.wizz.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/history")
    public String history()
    {
        return "home";
    }


}