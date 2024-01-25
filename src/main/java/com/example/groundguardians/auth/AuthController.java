package com.example.groundguardians.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public void socialLoginRedirect() throws IOException {
        authService.request();
    }

    @ResponseBody
    @GetMapping("/callback")
    public ResponseEntity<GetSocialOAuthRes> callback (
            @RequestParam(name = "code") String code)throws IOException{
        System.out.println(">> 소셜 로그인 API 서버로부터 받은 code :"+ code);

        GetSocialOAuthRes getSocialOAuthRes= authService.oAuthLogin(code);
        return new ResponseEntity<>(getSocialOAuthRes,HttpStatus.OK);
    }
}
