package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    //http://localhost:8080/hello  ?? 로컬호스트 어디서 만들어줌?
    public String hello(Model model){
    model.addAttribute("data","hello!!");   //thymeleaf에서 th:text="${data}"를 사용하면 hello!! data이름의 값 hello!!를 출력
    return"hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
//    ResponseBody html이 아닌 http에서 Body부분의 데이터를 직접 넣어주겠다
    public String helloString(@RequestParam("name")String name){
        return "hello " + name;  //"hello spring" 요청한 클라이언트에 그대로 내려감

    }
//    data를 내보내기
//    http://localhost:8080/hello-api?name=spring!!!! @ResponseBody는 Json 방식: 키와 벨류로 이루어짐
    @GetMapping("hello-api")
    @ResponseBody
    public  Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();  //hello 객체를 넘기면 객체를 보고 기본적으로 JsonConverter로 응답
        hello.setName(name);
        return hello;
        
    }

    static class Hello {
        private String  name;//외부에서 못꺼냄 자바빈 규약 getter setter 메소드를 통해서 접근 (프러퍼티 접근 방식)

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
