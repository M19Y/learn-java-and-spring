package com.m19y.learn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@WebServlet(urlPatterns = "/form-upload")
@MultipartConfig
public class MultipartServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    try(InputStream stream = MultipartServlet.class.getResourceAsStream("/html/upload.html")){
      if(stream == null){
        throw new IllegalArgumentException("html file is not found");
      }
      String html = new String(stream.readAllBytes());
      resp.getWriter().println(html);
      resp.setHeader("Content-Type", "text/html");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String name = req.getParameter("name");
    Part profile = req.getPart("profile");

    Path uploadPath = Path.of("upload/" + UUID.randomUUID() + "-" + profile.getSubmittedFileName());
    Files.copy(profile.getInputStream(), uploadPath);

//    String response = "Hello " + name + ", your profile saved in " + uploadPath.toAbsolutePath();
//    resp.getWriter().println(response);

    String html = """
            <html>
              <body>
                Name : $name
                <br>
                Profile : <img src="/download?file=$profile" width="400px" height="400px">
              </body>
            </html
            """
            .replace("$name", name)
            .replace("$profile", uploadPath.getFileName().toString());

    resp.getWriter().println(html);
  }
}
