package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    // 톰켓이 요청을 전달 해주고
    // hello라는 url에 모델에 데이터를 담아서 넘겨줌
    // hello.html을 스프링이 찾아서 thymeleaf 템플릿 엔진이 처리함
    // 컨트롤러가 리턴 값으로 문자를 반환하면 뷰 리졸버가 화면을 찾아서 처리함
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "Hello World");
        return "hello";
    }


}
