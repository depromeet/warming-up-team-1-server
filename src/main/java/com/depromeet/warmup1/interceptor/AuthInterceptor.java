package com.depromeet.warmup1.interceptor;

import com.depromeet.warmup1.dto.TokenDao;
import com.depromeet.warmup1.exception.UnauthorizedException;
import com.depromeet.warmup1.service.impl.JwtFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private final JwtFactory jwtFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info(request.getHeader("authorization"));

        String token = request.getHeader("authorization");

        TokenDao claims = jwtFactory.getTokenClaim(token)
                .orElseThrow(() -> new UnauthorizedException("Token is invalid."));


        request.setAttribute("id", claims.getMemberId());
        request.setAttribute("connectKey", claims.getConnectKey());

        return super.preHandle(request, response, handler);
    }

}
