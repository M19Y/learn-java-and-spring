package com.m19y.learn.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@NoArgsConstructor
@ConfigurationProperties("database")
public class DatabaseProperties {

  @NotBlank
  private String username;
  @NotBlank
  private String password;
}
