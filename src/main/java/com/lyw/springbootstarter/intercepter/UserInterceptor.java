package com.lyw.springbootstarter.intercepter;

import com.lyw.springbootstarter.common.UserHolder;
import com.lyw.springbootstarter.model.entity.User;
import com.lyw.springbootstarter.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {

    private final UserService userService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User loginUser = userService.getLoginUser(request);

        UserHolder.setUserId(loginUser.getId());
        UserHolder.setUserRole(loginUser.getUserRole());
        return true;
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.remove();
    }
    
}