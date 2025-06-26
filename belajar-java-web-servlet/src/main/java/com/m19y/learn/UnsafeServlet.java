package com.m19y.learn;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/unsafe")
public class UnsafeServlet extends HttpServlet {

  // this approach is unsafe
  // String response = "";
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name");
    long sleep = Long.parseLong(req.getParameter("sleep"));
    // unsafe
    // response = "Hello " + name;

    // save
    String response = "Hello " + name;
    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    resp.getWriter().println(response);
  }
}
