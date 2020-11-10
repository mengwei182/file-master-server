package org.example.file.repository.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.File;
import java.sql.Timestamp;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileEntity {
  private int id;
  private String name;
  private String suffixName;
  private int type;
  private String absolutePath;
  private int userId;
  private long size;
  private boolean deleted;
  private boolean file;
  private Timestamp createTime;
  private Timestamp modifyTime;

  public static final String DEFAULT_ROOT_PATH = "/";
  public static final String ROOT_PATH = File.separator;
  public static final String DEFAULT_FILE_NAME = "我的资源";
  public static final String NULL_FILE_NAME = "";
  public static final String NULL_SUFFIX_NAME = "";

  public static FileEntity buildInitialFile() {
    FileEntity fileEntity = new FileEntity();
    fileEntity.setAbsolutePath(ROOT_PATH);
    fileEntity.setName(DEFAULT_FILE_NAME);
    fileEntity.setFile(false);
    return fileEntity;
  }

  public static FileEntity buildInitialFile(int userId) {
    FileEntity fileEntity = new FileEntity();
    fileEntity.setUserId(userId);
    fileEntity.setSuffixName(NULL_SUFFIX_NAME);
    fileEntity.setAbsolutePath(ROOT_PATH);
    fileEntity.setName(DEFAULT_FILE_NAME);
    fileEntity.setSize(0);
    fileEntity.setFile(false);
    fileEntity.setType(FileType.OTHER.getType());
    return fileEntity;
  }

  public static FileEntity buildFileEntity(File file) {
    FileEntity fileEntity = FileEntity.buildInitialFile();
    fileEntity.setName(file.getName());
    fileEntity.setAbsolutePath(file.getAbsolutePath());
    fileEntity.setFile(file.isFile());
    fileEntity.setSize(file.isFile() ? file.length() : -1);
    fileEntity.setCreateTime(new Timestamp(file.lastModified()));
    return fileEntity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSuffixName() {
    return suffixName;
  }

  public void setSuffixName(String suffixName) {
    this.suffixName = suffixName;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getAbsolutePath() {
    return absolutePath;
  }

  public void setAbsolutePath(String absolutePath) {
    this.absolutePath = absolutePath;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public boolean isFile() {
    return file;
  }

  public void setFile(boolean file) {
    this.file = file;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public Timestamp getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(Timestamp modifyTime) {
    this.modifyTime = modifyTime;
  }
}
