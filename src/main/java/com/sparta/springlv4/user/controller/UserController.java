package com.sparta.springlv4.user.controller;

import com.sparta.springlv4.common.dto.ApiResponseDto;
import com.sparta.springlv4.user.dto.UserRequestDto;
import com.sparta.springlv4.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.ActionMapUIResource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    //------------회원가입
    @PostMapping("/user/signup")
    public ResponseEntity<ApiResponseDto> signup(@Valid @RequestBody UserRequestDto requestDto, BindingResult bindingResult){

        ResponseEntity<ApiResponseDto> result =  checkUserRequestDto(bindingResult);
        if (result!=null) return result;

        try{
            userService.signup(requestDto);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(new ApiResponseDto(400,e.getMessage()));
        }

        return ResponseEntity.ok().body(new ApiResponseDto(200,"SUCCESS_SIGN_UP"));
    }
//------------회원가입
//------------로그인
    @PostMapping("/user/login")
    public ResponseEntity<ApiResponseDto> login(@Valid @RequestBody UserRequestDto requestDto, BindingResult bindingResult, HttpServletResponse res){
        //validation 예외 처리
        ResponseEntity<ApiResponseDto> result = checkUserRequestDto(bindingResult);
        if (result!=null)return result;

        try {
            userService.login(requestDto, res);
        }catch (IllegalArgumentException e){
            log.error(e.getMessage());
            return  ResponseEntity.badRequest().body(new ApiResponseDto(400,e.getMessage()));
        }

        return ResponseEntity.ok().body(new ApiResponseDto(200, "로그인 성공"));

    }
//------------로그인
    private ResponseEntity<ApiResponseDto> checkUserRequestDto(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size()>0){
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                log.error(fieldError.getField()+"필드 : "+fieldError.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new ApiResponseDto(400L,"INVALID_TYPE_VALUE"));
        }

        return null;
    }
}
