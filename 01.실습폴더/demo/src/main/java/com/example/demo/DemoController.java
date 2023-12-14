package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo") // 디스패쳐 서블릿이 경로 설정
public class DemoController {
    
    // demo라는 경로에 get을 요청하면
    @GetMapping 
    public String demoMethod(Model model) { // View 리졸버로 thymeleaf 사용할 것이므로 경로의 파일명 (MVC에서 실제로 View한테 넘길 모델)
        model.addAttribute("text", "Hello World"); // text형태로 Hello World를 넘길것임
        return "demo"; // 6분 21초
    }
}
