package org.example.user.controller;

import org.example.common.json.Result;
import org.example.user.controller.vo.UserVo;
import org.example.user.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserController {
  @Resource
  private UserService userService;

  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
  public Result login(HttpServletRequest request, @Valid @RequestBody UserVo userVo) {
    return userService.login(request, userVo);
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
  public Result registerUser(@Valid @RequestBody UserVo userVo) {
    return userService.registerUser(userVo);
  }
}
