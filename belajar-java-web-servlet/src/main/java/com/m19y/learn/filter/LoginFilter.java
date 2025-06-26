package com.m19y.learn.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LoginFilter extends HttpFilter {

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

    if(req.getRequestURI().equals("/session/login")){
      chain.doFilter(req, res);
    }else{
      HttpSession session = req.getSession(true);
      String username  = (String) session.getAttribute("username");

      if(username != null){
        chain.doFilter(req, res);
      }else{
        res.getWriter().println("You need to login first");
        res.setStatus(401);
      }
    }
  }
}
