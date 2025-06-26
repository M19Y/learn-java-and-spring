package com.m19y.learn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String file = req.getParameter("file");
    Path path = Path.of("upload/" + file);
    if(!Files.exists(path)){
      resp.getWriter().println("gambar tidak ditemukan");
      resp.setStatus(404);
      return;
    }
    byte[] bytes = Files.readAllBytes(path);

    String force = req.getParameter("force");

    if("true".equalsIgnoreCase(force)){
      resp.setHeader("Content-Disposition", "attachment; filename=\"" + path.getFileName() + "\"");
    }

    resp.getOutputStream().write(bytes);
  }
}
