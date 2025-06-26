package com.m19y.learn;

import com.m19y.learn.model.SayHelloRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@WebServlet("/api/say-hello")
public class ApiServlet extends HttpServlet {


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    SayHelloRequest request = JsonUtil.getObjectMapper().readValue(req.getReader(), SayHelloRequest.class);
    String sayHello = "Hello " + request.getFirstName() + " " + request.getLastName();

    Map<String, String> response = Map.of("data", sayHello);

    String jsonResponse = JsonUtil.getObjectMapper().writeValueAsString(response);

    resp.setHeader("Content-Type", "application/json");
    resp.setHeader("X_API_Token", UUID.randomUUID().toString());
    resp.getWriter().println(jsonResponse);
  }
}
