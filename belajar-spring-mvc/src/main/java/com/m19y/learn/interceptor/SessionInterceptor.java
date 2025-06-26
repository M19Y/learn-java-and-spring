package com.m19y.learn.interceptor;

import com.m19y.learn.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
public class SessionInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HttpSession session = request.getSession(true);
    User user = (User) session.getAttribute("user");

    if(Objects.isNull(user)){
      response.sendRedirect("/login");
      return false;
    }
    return true;
  }
}
