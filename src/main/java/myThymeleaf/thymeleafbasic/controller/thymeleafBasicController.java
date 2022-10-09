package myThymeleaf.thymeleafbasic.controller;

import com.sun.deploy.net.HttpResponse;
import lombok.Data;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class thymeleafBasicController {

    @GetMapping("text-basic")
    public String text(Model model){
        model.addAttribute("data", "th:text 태그를 사용하자");
        model.addAttribute("data2", "[[ ]] 를 붙이면 태그 없이 바로 출력 가능");


        model.addAttribute("un", "<b>utext 를 붙이면 unescape<b>");
        model.addAttribute("esc", "<b>utext 를 안붙이면 escape<b>");

        model.addAttribute("em", "<b>바로출력하는 경우 [[..]] vs [(..)]</b>");
        return "basic/text-basic";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("케리건", 20);
        User userB = new User("레이너", 25);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("케리건", userA);
        map.put("레이너", userB);


        model.addAttribute("user", userA);
        model.addAttribute("list", list);
        model.addAttribute("map", map);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String object(HttpSession session, HttpServletRequest req, HttpServletResponse resp){

        session.setAttribute("sessionData", "im Session");
        req.setAttribute("obj", "편의 객체~~~~!!!");

        return "basic/basic-objects";
    }

    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());

        return "basic/date";
    }

    @GetMapping("link")
    public String link(Model model){
        model.addAttribute("param1", "category");
        model.addAttribute("param2", "Front-End/thymeleaf");

        return "/basic/link";
    }

    @GetMapping("literal")
    public String literal(Model model) {
        model.addAttribute("data", "문자는 항상 ' ' 가 필수!!");

        return "/basic/literal";
    }

    @GetMapping("/operation")
    public String oper(Model model){
        model.addAttribute("nulldata", null);
        model.addAttribute("data", "spring");

        return "/basic/oeration";
    }

    @Component("springBean")
    static class springBean {
        public String Bean(String data) {
            return "BeanData : " + data;
        }
    }

    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }

    }

}
