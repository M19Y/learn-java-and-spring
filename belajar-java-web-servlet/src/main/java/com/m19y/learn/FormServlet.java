package com.m19y.learn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(urlPatterns = "/form")
public class FormServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // use get resource as stream (karena filenya kecil kita bisa menggunakan readAllBytes
    try(InputStream inputStream = FormServlet.class.getResourceAsStream("/html/form.html")){
      if(inputStream == null){
        throw new IllegalArgumentException("File is not found!");
      }
      String html = new String(inputStream.readAllBytes());
      resp.getWriter().println(html);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String firstName = req.getParameter("firstName");
    String lastName = req.getParameter("lastName");
    String fullName = firstName + " " + lastName;
    resp.getWriter().println("Hello " + fullName);
  }
}
