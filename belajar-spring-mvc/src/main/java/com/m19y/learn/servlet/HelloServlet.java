package com.m19y.learn.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// by default servlet tidak akan terbaca oleh spring, harus di registrasikan menggunakan
// @ServletComponentScan pada main classnya
@WebServlet(urlPatterns = "/servlet/hello")
public class HelloServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.getWriter().print("Hello from servlet");
  }
}
