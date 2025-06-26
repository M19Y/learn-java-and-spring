package com.m19y.learn.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@Controller
public class UploadController {

  @ResponseBody
  @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String upload(@RequestParam(name = "name") String name,
                       @RequestPart(name = "profile") MultipartFile profile) throws IOException {
    Path path = Path.of("upload/" + profile.getOriginalFilename());

//    Files.write(path, profile.getBytes());
    profile.transferTo(path);
    return "Success save profile " + name + " to " + path;
  }
}
