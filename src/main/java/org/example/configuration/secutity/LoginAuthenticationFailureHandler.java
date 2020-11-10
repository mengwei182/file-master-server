package org.example.configuration.secutity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
  private final Logger logger = LoggerFactory.getLogger(LoginAuthenticationFailureHandler.class);

  @Override
  public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
    logger.info("login failure:" + e.getMessage());
  }
}
