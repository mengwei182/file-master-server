package org.example.filter;

import org.example.user.repository.entity.UserEntity;
import org.example.user.service.UserService;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebFilter(value = "/*")
public class AuthorizationFilter implements Filter {
  @Resource
  private UserService userService;

  private final List<String> ignoreUris = Arrays.asList("/login", "/register");

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    if (!isIgnoreUri(httpServletRequest)) {
      UserEntity user = userService.getUser(httpServletRequest);
      if (user != null) {
        chain.doFilter(request, response);
      } else {
        httpServletRequest.getRequestDispatcher("/login").forward(request, response);
      }
    }
  }

  private boolean isIgnoreUri(HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    for (String uri : ignoreUris) {
      if (requestURI.endsWith(uri)) {
        return true;
      }
    }
    return false;
  }
}
