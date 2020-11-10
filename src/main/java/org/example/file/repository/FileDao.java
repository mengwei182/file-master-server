package org.example.file.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.file.repository.entity.FileEntity;

import java.util.List;

@Mapper
public interface FileDao {
  boolean addFile(FileEntity fileEntity);

  List<FileEntity> getFiles(@Param("path") String path, @Param("userId") int userId);
}
