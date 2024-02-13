package com.example.groundguardians.auth;

import com.example.groundguardians.user.User;
import com.example.groundguardians.user.UserRepository;
import com.example.groundguardians.jwt.JwtTokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final GoogleOauth googleOauth;
    private final HttpServletResponse response;
    private final JwtTokenUtil jwtTokenUtil;

    public String request() throws IOException {

        return googleOauth.getOauthRedirectURL();
    }

    public GetSocialOAuthRes oAuthLogin(String code) throws IOException {
        //구글로 일회성 코드를 보내 액세스 토큰이 담긴 응답객체를 받아옴
        ResponseEntity<String> accessTokenResponse= googleOauth.requestAccessToken(code);
        //응답 객체가 JSON형식으로 되어 있으므로, 이를 deserialization해서 자바 객체에 담을 것이다.
        GoogleOAuthToken oAuthToken=googleOauth.getAccessToken(accessTokenResponse);

        //액세스 토큰을 다시 구글로 보내 구글에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
        ResponseEntity<String> userInfoResponse=googleOauth.requestUserInfo(oAuthToken);
        //다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화한다.
        GoogleUser googleUser= googleOauth.getUserInfo(userInfoResponse);

        Optional<User> optionalUser = userRepository.findByEmail(googleUser.getEmail());
        User user;

        if(optionalUser.isEmpty()){

            user = User.builder()
                    .email(googleUser.getEmail())
                    .name(googleUser.getName())
                    .build();

            userRepository.save(user);
        }else{
            user = optionalUser.get();
        }

        String jwtToken=this.generateJwt(user);
        //액세스 토큰과 jwtToken, 이외 정보들이 담긴 자바 객체를 다시 전송한다.
        GetSocialOAuthRes getSocialOAuthRes=new GetSocialOAuthRes(jwtToken,user.getId(),oAuthToken.getAccess_token(),oAuthToken.getToken_type());
        return getSocialOAuthRes;
    }

    public String generateJwt(User user){
        return jwtTokenUtil.generateToken(user);
    }


}
