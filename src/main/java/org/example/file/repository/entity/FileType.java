package org.example.file.repository.entity;

public enum FileType {
  VIDEO(1), PICTURE(2), DOCUMENT(3), MUSIC(4), OTHER(5);
  private int type;

  private FileType(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }

  private void setType(int type) {
    this.type = type;
  }
}
