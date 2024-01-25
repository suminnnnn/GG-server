package com.example.groundguardians.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class JwtTokenInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if(method.isAnnotationPresent(JwtRequired.class) ||
                handlerMethod.getBeanType().isAnnotationPresent(JwtRequired.class)) {
            String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String requestJwt = authorizationHeader.substring(7);

                if (jwtTokenUtil.validateToken(requestJwt)) {
                    Long id = jwtTokenUtil.getIdFromToken(requestJwt);

                    SecurityContextHolder.getContext().setAuthentication(jwtTokenUtil.getAuthentication(requestJwt));

                    return true;
                }
            }

            // 검증 실패시 401 Unauthorized 에러 반환
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");

            return false;
        }

        return true;

    }

}
