package com.m19y.learn.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UploadControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testUploadFile() throws Exception{

    Path path = Paths.get("upload", "profile.jpg");

    mockMvc.perform(
            MockMvcRequestBuilders.multipart("/upload")
                    .file(new MockMultipartFile("profile",
                            "profile.jpg",
                            "image/jpg",
                            getClass().getResourceAsStream("/images/gohan.jpg")))
                    .param("name", "Vro")
    ).andExpectAll(
            MockMvcResultMatchers.status().isOk(),
            MockMvcResultMatchers.content().string(Matchers.containsString("Success save profile Vro to " + path))
    );
  }
}