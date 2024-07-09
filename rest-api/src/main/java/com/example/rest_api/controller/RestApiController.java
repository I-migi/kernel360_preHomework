package com.example.rest_api.controller;


import com.example.rest_api.model.BookQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/hello")
    public String hello(){
        var html = "<html> <body><h1>Hello Spring Boot</h1> </body></html>";
        return html;
    }

    @GetMapping("/echo/{message}/age/{age}/is-man/{isMan}")
    public String echo(
            @PathVariable String message,
            @PathVariable int age,
            @PathVariable boolean isMan
    ){
        System.out.println("echo message" + message);

        //대문자로 변환해서 return > message.toUpperCase();

        // String 타입의 변수 외에 다른 타입 받아보기

        return message;
    }

    @GetMapping("/book")
    public void queryParam(
            @RequestParam String category,
            @RequestParam String issuedYear,
            @RequestParam(name="issued-month") String issuedMonth,
            @RequestParam String issued_day
    ){
        System.out.println(category);
        System.out.println(issuedYear);
        System.out.println(issuedMonth);
        System.out.println(issued_day);

    }

    @GetMapping("/book2")
    public void queryParamDto(
            BookQueryParam bookQueryParam
    ){
        System.out.println(bookQueryParam);
    }
    
    //Parameter 2가지를 int를 받은후 두 수의 덧셈, 곱셈

    @DeleteMapping(path = {
                    "/user/{userName}/delete",
                    "/user/{userName}/del"})
    public void delete(@PathVariable String userName){
        log.info("user-name: {}",userName);
    }

}
