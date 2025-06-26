package com.m19y.learn.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController {

  private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

  /*
  // with more effort
  @GetMapping(path = "/date")
  public void getDate(@RequestParam(name = "date") Date date, HttpServletResponse response) throws IOException {
    response.getWriter().println("Date : " + DATE_FORMAT.format(date));
  }*/

  // using response body
  @GetMapping(path = "/date")
  @ResponseBody
  public String getDate(@RequestParam(name = "date") Date date) throws IOException {
    return "Date : " + DATE_FORMAT.format(date);
  }


}
