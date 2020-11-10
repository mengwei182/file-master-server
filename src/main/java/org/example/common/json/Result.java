package org.example.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.common.global.GlobalMassage;
import org.example.common.util.I18NUtils;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result implements Serializable {
  private static final Integer FAILURE_CODE = 0;
  private static final Integer SUCCESS_CODE = 1;
  private Integer code;
  private String message;
  private Object data;

  private Result() {
  }

  private Result(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  private Result(Integer code, String massage, Object data) {
    this(code, massage);
    this.data = data;
  }

  public static Result success() {
    return new Result(ResultCode.SUCCESS.code, ResultCode.SUCCESS.massage);
  }

  public static Result success(Object data) {
    return new Result(ResultCode.SUCCESS.code, ResultCode.SUCCESS.massage, data);
  }

  public static Result failure() {
    return new Result(ResultCode.FAILURE.code, ResultCode.FAILURE.massage);
  }

  public static Result failure(ResultCode resultCode) {
    return new Result(resultCode.code, resultCode.massage);
  }

  public static Result failure(ResultCode resultCode, Object data) {
    return new Result(resultCode.code, resultCode.massage, data);
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public enum ResultCode {
    SUCCESS(SUCCESS_CODE, I18NUtils.getI18NMsg(GlobalMassage.SUCCESS)),
    FAILURE(FAILURE_CODE, I18NUtils.getI18NMsg(GlobalMassage.FAILURE)),
    USER_EXIST(FAILURE_CODE, I18NUtils.getI18NMsg(GlobalMassage.USER_EXIST)),
    USER_NAME_EXIST(FAILURE_CODE, I18NUtils.getI18NMsg(GlobalMassage.USER_NAME_EXIST)),
    PHONE_NUMBER_EXIST(FAILURE_CODE, I18NUtils.getI18NMsg(GlobalMassage.PHONE_NUMBER_EXIST)),
    USER_NOT_EXIST(FAILURE_CODE, I18NUtils.getI18NMsg(GlobalMassage.USER_NOT_EXIST)),
    PASSWORD_ERROR(FAILURE_CODE, I18NUtils.getI18NMsg(GlobalMassage.PASSWORD_ERROR)),
    AUTHORITY_ERROR(FAILURE_CODE, I18NUtils.getI18NMsg(GlobalMassage.AUTHORITY_ERROR));

    private Integer code;
    private String massage;

    ResultCode(Integer code, String massage) {
      this.code = code;
      this.massage = massage;
    }

    private Integer getCode() {
      return code;
    }

    private void setCode(Integer code) {
      this.code = code;
    }

    private String getMassage() {
      return massage;
    }

    private void setMassage(String massage) {
      this.massage = massage;
    }
  }
}
