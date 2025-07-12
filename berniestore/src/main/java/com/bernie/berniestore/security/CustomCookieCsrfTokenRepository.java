package com.bernie.berniestore.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;

public class CustomCookieCsrfTokenRepository implements CsrfTokenRepository {
    private static final String COOKIE_NAME = "XSRF-TOKEN";
    private static final String HEADER_NAME = "X-XSRF-TOKEN";
    private static final String PARAMETER_NAME = "_csrf";
    private static final String COOKIE_PATH = "/";

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String token = UUID.randomUUID().toString();
        return new DefaultCsrfToken(HEADER_NAME, PARAMETER_NAME, token);
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
        String value = token != null ? token.getToken() : "";
        Cookie cookie = new Cookie(COOKIE_NAME, value);
        cookie.setPath(COOKIE_PATH);
        cookie.setSecure(true);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(-1);


        StringBuilder sb = new StringBuilder();
        sb.append(COOKIE_NAME).append("=").append(value).append("; Path=").append(COOKIE_PATH)
                .append("; SameSite=None; Secure");
        response.addHeader("Set-Cookie", sb.toString());
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    return new DefaultCsrfToken(HEADER_NAME, PARAMETER_NAME, cookie.getValue());
                }
            }
        }
        return null;
    }
}
