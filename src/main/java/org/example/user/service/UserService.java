package org.example.user.service;

import org.example.common.json.Result;
import org.example.user.controller.vo.UserVo;
import org.example.user.repository.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends UserDetailsService {
  UserEntity getUser(HttpServletRequest request);

  Result login(HttpServletRequest request, UserVo userVo);

  Result registerUser(UserVo userVo);
}
