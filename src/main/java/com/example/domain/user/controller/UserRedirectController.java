package com.example.domain.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "user")
public class UserRedirectController {
    @GetMapping("writeForm")
    public String writeForm() {
        return "/user/writeForm";
    }
}
