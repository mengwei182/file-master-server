package org.example.common.json;

/**
 * Create by wudi@seczone.cn on 2017/8/9 设置http响应状态码常量
 */
public enum ResponseCode {
  // 返回状态正常的状态码
  ERROR(0, "ERROR"),
  SUCCESS(1, "SUCCESS"),
  VALID_TIP(2, "MESSAGE TIP"),
  LDAP_CREATE(5, "INIT_LDAP"),
  NEED_LOGIN(10, "NEED_LOGIN"),
  //授权问题
  ILLEGAL_ARGUMENT(20, "ILLEGAL_ARGUMENT"),
  ILLEGAL_APP_ASSIGN_AUTH(21, "ILLEGAL_APP_ASSIGN_AUTH"),
  LICESE_ERROR(30, "LICENSE_ERROR"),
  AGENT_ERROR(40, "AGENT_DETECT_DISABLE"),
  AGENT_GLOBAL_ERROR(50, "AGENT_GLOBAL_DISABLE"),
  DB_GLOBAL_ERROR(6, "SYSTEM_START_CHECK"),
  REQUEST_MISSING(404, "REQUEST_MISSING");

  private final int code;
  private final String description;

  ResponseCode(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

}
