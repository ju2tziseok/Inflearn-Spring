package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//MVC : Model, View, Controller
//
//Controller : 비즈니스 로직 처리
//
//View : 오로지 화면에만 보이는데 집중
//
//Model : 화면에 필요한 데이터를 담아서 넘겨 줌


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


    //http://localhost:8080/hello-mvc?name=spring!!!!!
    // hello-mvc를 웹 브라우저에 넘기면 톰캣이 일단 받음
    // 톰캣은 그걸 스프링에게 던져줌
    // 스프링은 매핑이 된 매서드를 호출해줌
    // 모델에 데이터를 담아서 해당하는 곳에 리턴해줌
    // 뷰리졸버가 뷰를 찾아 템플릿 엔진 처리를 해줌
    // html에서 ${name}곳에 치환되어서 넘겨줌
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name",name);
        return"hello-template";
    }


    @GetMapping("hello-string")
    @ResponseBody
    // 스프링 컨테이너에 던져주고 스프링이 확인하니 responsebody가 있음 (없으면 뷰 리졸버에 던져줌)
    // http 응답에 그냥 넘겨줘야겠구나 라고 생각
    // html에 나오는 바디 부분이 아니라
    // http 헤더부와 바디부에서 바디 부에 이 데이터를 직접 내가 넣어주겠다는 의미
    // 뷰 없이 그냥 문자 그대로 내려감 : StringConverter
    // 이러면 페이지 소스 보기를 해도 html이 보이지 않음
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    }


    // 문자를 달라는게 아니라 데이터를 직접 내놓으려고 하면 API 방식을 사용
    // 객체를 리턴해주니 json 방식으로 데이터를 넘겨줌 : JsonConverter
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloAPi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
