package com.m19y.learn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/header")
public class HeaderServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    Enumeration<String> header = req.getHeaderNames();
    String name;
    while((name = header.nextElement()) != null){
      resp.getWriter().println("Header = " + name + " : Value = " + req.getHeader(name));
    }

  }
}
