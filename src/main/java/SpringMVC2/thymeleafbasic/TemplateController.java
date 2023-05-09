package SpringMVC2.thymeleafbasic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {

    //코드 조각 사용
    @GetMapping("/fragment")
    public String template() {
        return "template/fragment/fragmentMain";
    }

    //코드 조각을 레이아웃에 넘겨서 사용
    @GetMapping("/layout")
    public String layout() {
        return "template/layout/layoutMain";
    }

    @GetMapping("/layoutExtend")
    public String layoutExtend() {
        return "template/layoutExtend/layoutExtendMain";
    }
}
