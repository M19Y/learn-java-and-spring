package com.m19y.learn.service;

import com.m19y.learn.entity.User;
import com.m19y.learn.model.auth.LoginUserRequest;
import com.m19y.learn.model.auth.TokenResponse;
import com.m19y.learn.repository.UserRepository;
import com.m19y.learn.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.Period;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final ValidatorService validator;

  @Autowired
  public AuthServiceImpl(UserRepository userRepository, ValidatorService validator) {
    this.userRepository = userRepository;
    this.validator = validator;
  }

  @Override
  @Transactional
  public TokenResponse login(LoginUserRequest request) {
    validator.validate(request);

    User user = userRepository.findById(request.getUsername())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong!"));

    if(!BCrypt.checkpw(request.getPassword(), user.getPassword())){
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password wrong!");
    }

    user.setToken(UUID.randomUUID().toString());
    user.setTokenExpiredAt(next30Days());
    userRepository.save(user);

    return TokenResponse.builder()
            .token(user.getToken())
            .expiredAt(user.getTokenExpiredAt())
            .build();
  }

  @Override
  @Transactional
  public void logout(User user) {
    user.setToken(null);
    user.setTokenExpiredAt(null);

    userRepository.save(user);
  }
  private Long next30Days(){
//    return System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30);
    return Instant.now()
            .plus(Period.ofDays(30)).toEpochMilli();
  }
}

