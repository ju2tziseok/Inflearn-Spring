package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // /는 도메인 첫번째가 호출됨
    @GetMapping("/")
    public String home(){
        return "home";
        // home.html이 호출됨
    }
}
