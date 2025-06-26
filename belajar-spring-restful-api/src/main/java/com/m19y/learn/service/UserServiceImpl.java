package com.m19y.learn.service;

import com.m19y.learn.entity.User;
import com.m19y.learn.mapper.UserMapper;
import com.m19y.learn.model.user.RegisterUserRequest;
import com.m19y.learn.model.user.UpdateUserRequest;
import com.m19y.learn.model.user.UserResponse;
import com.m19y.learn.repository.UserRepository;
import com.m19y.learn.security.BCrypt;
import com.m19y.learn.util.UpdateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ValidatorService validator;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, ValidatorService validator) {
    this.userRepository = userRepository;
    this.validator = validator;
  }

  @Override
  @Transactional
  public void register(RegisterUserRequest request) {

    validator.validate(request);

    if(userRepository.existsById(request.getUsername())){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered!");
    }

    User user = new User();
    user.setName(request.getName());
    user.setUsername(request.getUsername());
    user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));

    userRepository.save(user);

  }

  @Override
  public UserResponse get(User user){
    return new UserResponse(user.getUsername(), user.getName());
  }

  @Override
  @Transactional
  public UserResponse update(User user, UpdateUserRequest request) {
    validator.validate(request);

    UpdateHelper.updateIfNotNull(request.name(), user::setName);
    UpdateHelper.updateIfNotNull(request.password(),
            pass -> user.setPassword(BCrypt.hashpw(pass, BCrypt.gensalt())));

    userRepository.save(user);

    return UserMapper.toDto(user);
  }


}
