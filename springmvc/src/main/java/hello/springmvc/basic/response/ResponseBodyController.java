package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
// @Controller 대신에 @RestController 애노테이션을 사용하면, 해당 컨트롤러에 모두@ResponseBody 가 적용되는 효과가 있다.
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        //  HTTP 메시지 바디에 직접 ok 응답 메시지를 전달
        System.out.println("develop branch change!");
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        // HttpEntity는 HTTP 메시지의 헤더, 바디 정보를 가지고 있다.
        // ResponseEntity 는 여기에 더해서 HTTP 응답 코드를 설정할 수 있다.
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    // @ResponseBody 를 사용하면 view를 사용하지 않고, HTTP 메시지 컨버터 를 통해서 HTTP 메시지를 직접 입력
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        // HTTP 메시지 컨버터 를 통해서 JSON 형식으로 변환되어서 반환
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK) // @ResponseStatus(HttpStatus.OK) 애노테이션을 사용하면 응답 코드도 설정
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}
