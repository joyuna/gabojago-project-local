package com.bitcamp.gabojago.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DaumController {

    @RequestMapping("book")
    public String book() {
        return "book";
    }

    @RequestMapping("local")
    public String local() {
        return "local";
    }
}
