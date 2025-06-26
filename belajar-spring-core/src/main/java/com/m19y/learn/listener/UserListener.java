package com.m19y.learn.listener;

import com.m19y.learn.event.LoginSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserListener {

  @EventListener(classes = {LoginSuccessEvent.class})
  public void onLoginSuccessEvent1(LoginSuccessEvent event){
    log.info("Say halo to {}", event.getUser().getUsername());
  }

  @EventListener(classes = {LoginSuccessEvent.class})
  public void onLoginSuccessEvent2(LoginSuccessEvent event){
    log.info("Say welcome to {}", event.getUser().getUsername());
  }
  @EventListener(classes = {LoginSuccessEvent.class})
  public void onLoginSuccessEvent3(LoginSuccessEvent event){
    log.info("Say bye bye to {}", event.getUser().getUsername());
  }

}
