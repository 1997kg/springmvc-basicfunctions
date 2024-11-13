package hello.springmvc.basic.request;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("userName={}, age={}", username, age);

        response.getWriter().write("ok");


    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("userName={}, age={}", memberName, memberAge);
        return "ok"; // view조회를 하지않고 html
    }


    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){
        log.info("userName={}, age={}", username, age);
        return "ok"; //변수 명과 파라미터name 같으면 생략 가능
    }


    @ResponseBody
    @RequestMapping("/request-param-v4")
        public String requestParamV4(String username, int age){
            log.info("userName={}, age={}", username, age);
            return "ok"; //요청 파라미터name 같아야함
    }

    /**
     * Integer A = null(0)
     * int A = null (X)
     *
     *
     */

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,  //required = true는 있어야함, false = 없어도됨
             @RequestParam(required = false) Integer age) {

        log.info("userName={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue= "guest") String username,
            @RequestParam(required = false, defaultValue= "-1") Integer age) {

        log.info("userName={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){

        log.info("userName={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}
