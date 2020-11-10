package org.example.user.service.impl;

import org.example.common.global.GlobalStaticObject;
import org.example.common.json.Result;
import org.example.user.controller.vo.UserVo;
import org.example.user.repository.UserDao;
import org.example.user.repository.entity.UserEntity;
import org.example.user.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
  @Resource
  private UserDao userDao;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = userDao.getUserByUsername(username);
    if (user != null) {
      List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
      grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserName()));
      return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
    return new User(GlobalStaticObject.INVALID_USERNAME, GlobalStaticObject.INVALID_PASSWORD, GlobalStaticObject.INVALID_AUTHORITIES);
  }

  @Override
  public UserEntity getUser(HttpServletRequest request) {
    String account = (String) request.getSession(false).getAttribute(GlobalStaticObject.AUTHORITY_OBJECT);
    if (account != null) {
      return userDao.getUserByUsername(account);
    }
    return null;
  }

  public Result login(HttpServletRequest request, UserVo userVo) {
    String phoneNumber = userVo.getPhoneNumber();
    if (!checkPhoneNumberExist(phoneNumber)) {
      return Result.failure(Result.ResultCode.USER_NOT_EXIST);
    }
    String password = userVo.getPassword();
    password = Base64.getEncoder().encodeToString(password.getBytes());
    UserEntity user = userDao.getUserByPhoneNumber(phoneNumber);
    UserEntity userByUsernamePassword = userDao.getUserByUsernamePassword(user.getUserName(), password);
    if (userByUsernamePassword == null) {
      return Result.failure(Result.ResultCode.PASSWORD_ERROR);
    }
    request.getSession().setAttribute(GlobalStaticObject.AUTHORITY_OBJECT, userByUsernamePassword.getUserName());
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserName()));
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, user.getPassword(), grantedAuthorities);
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    return Result.success();
  }

  public boolean checkUserNameExist(String userName) {
    UserEntity user = userDao.getUserByUsername(userName);
    return user != null;
  }

  public boolean checkPhoneNumberExist(String phoneNumber) {
    UserEntity user = userDao.getUserByPhoneNumber(phoneNumber);
    return user != null;
  }

  @Transactional
  public Result registerUser(UserVo userVo) {
    if (checkUserNameExist(userVo.getPhoneNumber())) {
      return Result.failure(Result.ResultCode.USER_NAME_EXIST);
    }
    if (checkPhoneNumberExist(userVo.getPhoneNumber())) {
      return Result.failure(Result.ResultCode.PHONE_NUMBER_EXIST);
    }
    userVo.setPassword(Base64.getEncoder().encodeToString(userVo.getPassword().getBytes()));
    UserEntity userEntity = new UserEntity();
    userEntity.setUserName(userVo.getUserName());
    userEntity.setPhoneNumber(userVo.getPhoneNumber());
    userEntity.setPassword(userVo.getPassword());
    userDao.addUser(userEntity);
    return Result.success();
  }
}
