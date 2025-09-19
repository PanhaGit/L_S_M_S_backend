package com.team_yerng.l_s_m_s;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    @GetMapping("/api/user")
    public String user(){
        return "Hello User!!!";
    }

    @GetMapping("/api/sum")
    public String sum(){
        int a=10;
        int b=2021;
        int sum=a+b;
        return String.valueOf(sum);
    }
}
