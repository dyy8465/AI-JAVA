package com.ggs.traveljava.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggs.traveljava.utils.JwtUtils;
import com.ggs.traveljava.vo.Result;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行 OPTIONS 请求
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 获取请求头中的 token
        String token = request.getHeader("Authorization");

        // 如果 token 以 Bearer 开头，去掉前缀
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 校验 token
        if (StringUtils.hasText(token) && JwtUtils.validateToken(token)) {
            return true; // token 有效，放行
        }

        // token 无效或不存在，返回 401 错误
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Result<Void> result = Result.error("未登录或 token 已过期");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(result));
        return false;
    }
}
