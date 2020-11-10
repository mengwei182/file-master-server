package org.example.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class I18NUtils {

  public static String getI18NMsg(String i18nKey) {
    try {
      RequestContext requestContext = getRequestContext();
      if (requestContext != null) {
        return requestContext.getMessage(i18nKey);
      }
      return null;
    } catch (Exception e) {
      return i18nKey;
    }
  }

  public static String getI18NMsg(String i18nKey, String... msgArgs) {
    try {
      RequestContext requestContext = getRequestContext();
      if (requestContext != null) {
        return requestContext.getMessage(i18nKey, msgArgs);
      }
      return null;
    } catch (Exception e) {
      return i18nKey;
    }
  }

  public static RequestContext getRequestContext() {
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (servletRequestAttributes == null) {
      return null;
    }
    return getRequestContext(servletRequestAttributes);
  }

  public static RequestContext getRequestContext(HttpServletRequest httpServletRequest) {
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (servletRequestAttributes == null) {
      return new RequestContext(httpServletRequest);
    }
    return getRequestContext(servletRequestAttributes);
  }

  private static RequestContext getRequestContext(ServletRequestAttributes servletRequestAttributes) {
    HttpServletRequest request = servletRequestAttributes.getRequest();
    try {
      return new RequestContext(request);
    } catch (Exception e) {
      return null;
    }
  }

}
