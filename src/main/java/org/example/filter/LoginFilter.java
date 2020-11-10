package org.example.filter;

import org.example.common.global.GlobalStaticObject;
import org.example.common.util.CommonUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(value = "/*")
public class LoginFilter implements Filter {

  private final List<String> ignoreUris = Arrays.asList("/login", "/register");

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    if (!isIgnoreUri(httpServletRequest)) {
      String account = (String) httpServletRequest.getSession(false).getAttribute(GlobalStaticObject.AUTHORITY_OBJECT);
      if (CommonUtils.isEmpty(account)) {
        if (isSessionTimeout(httpServletRequest)) {
          httpServletRequest.getRequestDispatcher("/login").forward(request, response);
        }
      }
    }
    chain.doFilter(request, response);
  }

  private boolean isSessionTimeout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    return session == null;
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
