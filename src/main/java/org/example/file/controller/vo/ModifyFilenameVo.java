package org.example.file.controller.vo;

import javax.validation.constraints.NotNull;

public class ModifyFilenameVo {
  String path;
  @NotNull
  private String originFilename;
  private String targetFilename;

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getOriginFilename() {
    return originFilename;
  }

  public void setOriginFilename(String originFilename) {
    this.originFilename = originFilename;
  }

  public String getTargetFilename() {
    return targetFilename;
  }

  public void setTargetFilename(String targetFilename) {
    this.targetFilename = targetFilename;
  }
}
