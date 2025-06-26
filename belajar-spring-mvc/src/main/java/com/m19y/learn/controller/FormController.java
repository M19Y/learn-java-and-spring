package com.m19y.learn.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FormController {

  private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @ResponseBody
  @PostMapping(path = "/form/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String createPerson(
          @RequestParam(name = "name") String name,
          @RequestParam(name = "birthDate") Date birthDate,
          @RequestParam(name = "address") String address){

    return String.format("Success create person with\n name : %s\n brithDate: %s\n address : %s\n",
            name, dateFormat.format(birthDate), address);
  }

  @ResponseBody
  @PostMapping(path = "/form/hello",
          consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
          produces = MediaType.TEXT_HTML_VALUE)
  public String hello(@RequestParam(name = "name") String name){
    return """
            <html>
              <title>Hello MVC</title>
              <body>
                <h1>Hello $name</h1>
              </body>
            </html>
            """.replace("$name", name);
  }
}
