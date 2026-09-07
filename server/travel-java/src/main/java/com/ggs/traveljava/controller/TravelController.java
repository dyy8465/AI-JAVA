package com.ggs.traveljava.controller;

import com.ggs.traveljava.dto.ChatRequestDTO;
import com.ggs.traveljava.dto.LoginRequestDTO;
import com.ggs.traveljava.dto.RegisterRequestDTO;
import com.ggs.traveljava.dto.TravelRequestDTO;
import com.ggs.traveljava.service.TravelService;
import com.ggs.traveljava.service.UserService;
import com.ggs.traveljava.vo.LoginVO;
import com.ggs.traveljava.vo.TravelRecommendVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.ggs.traveljava.vo.Result;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RestController
@RequestMapping("/api/travel")
@RequiredArgsConstructor
public class TravelController {
    private final TravelService travelService;
    private final UserService userService;

//    public TravelController(TravelService travelService) {
//        this.travelService = travelService;
//    }

    @GetMapping("/hello")
    public Result<String> hello() {

        return Result.ok("hello world");
    }

//    @valid开启参数校验
    @PostMapping("/recommend")
    public Result<TravelRecommendVO> recommend(@Valid @RequestBody TravelRequestDTO travelRequestDTO) {

        System.out.println(travelRequestDTO.getCity());
        System.out.println(travelRequestDTO.getDays());
        System.out.println(travelRequestDTO.getBudget());
        TravelRecommendVO travelRecommendVO = travelService.recommend(travelRequestDTO.getCity(), travelRequestDTO.getDays(), travelRequestDTO.getBudget());
        return Result.ok(travelRecommendVO);
    }
    @PostMapping(value = "/chat", produces = "text/event-stream")
    public SseEmitter chat(@Valid @RequestBody ChatRequestDTO chatRequestDTO){
        return travelService.chat(chatRequestDTO.getMessage());
    }

    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginVO loginVO = userService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        return Result.ok(loginVO);
    }

    @PostMapping("/register")
    public Result<LoginVO> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        LoginVO loginVO = userService.register(
                registerRequestDTO.getUsername(),
                registerRequestDTO.getPassword(),
                registerRequestDTO.getConfirmPassword()
        );
        return Result.ok(loginVO);
    }

}
