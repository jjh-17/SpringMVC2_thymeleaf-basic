package SpringMVC2.thymeleafbasic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.expression.spel.ast.Literal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/basic")
public class BasicController {

    //=========================================================================
    //텍스트


    //순수 텍스트 출력
    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "text-basic");
        return "text/text-basic";
    }

    //<b></b>를 이용한 텍스트 강조 테스트
    //웹 브라우저는 '<'를 HTML 태그의 시작으로 인식 ==> 태그가 아닌 문자로 표현할 수 있는 방법 필요
    @GetMapping("text-basic2")
    public String textBasic2(Model model) {
        model.addAttribute("data", "text-<b>basic</b>");
        return "text/text-basic";
    }

    //Unescape : HTML에서 사용하는 특수 문자를 HTML 엔티티로 변경하는 것(excape) 방지
    @GetMapping("text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "text-<b>basic</b>");
        return "text/text-unescaped";
    }

    //============================================================================
    //표현식

    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    //SpringEL 표현식
    @GetMapping("variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        ArrayList<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        HashMap<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "expression/variable";
    }

    //타임리프가 제공하는 기본 객체
    //${#request}, $(#response), $(#session), $(#servletContext)는 스프링 부트 3.0 이후부터 제공하지 않음
    @GetMapping("/basic-objects")
    public String basicObjects(Model model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) {

        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        session.setAttribute("sessionData", "Session Data");

        return "expression/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "hello " + data;
        }
    }

    //유틸리티 객체와 날짜
    @GetMapping("/date")
    public String data(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "expression/date";
    }

    //URL 링크
    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "expression/link";
    }

    //리터럴 : 소스 코드상에 고정된 값
    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "literal");
        return "expression/literal";
    }

    //연산
    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "operation");
        return "expression/operation";
    }

    //===================================================================================

    //속성값 설정
    @GetMapping("/attribute")
    public String attribute() {
        return "attribute/attribute";
    }

    //==============================================================================

    //반복
    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "iteration/each";
    }

    private void addUsers(Model model) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("UserA", 10));
        users.add(new User("UserB", 20));
        users.add(new User("UserC", 30));

        model.addAttribute("users", users);
    }

    //=================================================================================

    //조건부 평가
    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "condition/condition";
    }

    //=================================================================================
    //주석 및 블럭

    //주석
    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "주석");
        return "annotation_and_block/comments";
    }

    //블럭
    @GetMapping("/block")
    public String block(Model model) {
        addUsers(model);
        return "annotation_and_block/block";
    }

    //자바스크립트 인라인
    @GetMapping("javascript")
    public String javascipt(Model model) {
        model.addAttribute("user", new User("uesrA", 10));
        addUsers(model);

        return "javascript_inline/javascript";
    }
}
