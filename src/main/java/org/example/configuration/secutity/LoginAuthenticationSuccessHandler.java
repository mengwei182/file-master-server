package org.example.configuration.secutity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final Logger logger = LoggerFactory.getLogger(LoginAuthenticationSuccessHandler.class);

  @Override
  public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
    User user = (User) authentication.getPrincipal();
    logger.info(user.getUsername() + " login success");
  }
}
