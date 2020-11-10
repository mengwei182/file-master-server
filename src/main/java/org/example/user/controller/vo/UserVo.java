package org.example.user.controller.vo;

import javax.validation.constraints.*;

public class UserVo {
  @NotNull
  @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,7}$|^[\\dA-Za-z_]{1,14}$", message = "最长14个英文或7个汉字")
  private String userName;
  @NotNull
  @Pattern(regexp = "^1[0-9]{10}$", message = "手机号不正确")
  private String phoneNumber;
  @NotNull
  @Pattern(regexp = "^(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^[^\\s\\u4e00-\\u9fa5]{8,14}$", message = "手机号不正确")
  private String password;
  @NotNull
  @Pattern(regexp = "^\\d{6}$", message = "手机号不正确")
  private String captcha;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCaptcha() {
    return captcha;
  }

  public void setCaptcha(String captcha) {
    this.captcha = captcha;
  }
}
