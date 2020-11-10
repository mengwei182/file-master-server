package org.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.example.user.controller.vo.UserVo;
import org.example.user.repository.UserDao;
import org.example.user.repository.entity.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//@Aspect
//@Component
public class LoginAop {
  @Resource
  private UserDao userDao;

  @AfterReturning(returning = "login", value = "execution(* org.example.user.service.UserService.login(..))")
  public void doLoginAfter(JoinPoint point, boolean login) {
    if (login) {
      Object[] args = point.getArgs();
      UserVo userVo = (UserVo) args[1];
      UserEntity user = userDao.getUserByPhoneNumber(userVo.getPhoneNumber());
      List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
      grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserName()));
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, user.getPassword(), grantedAuthorities);
      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
  }
}
