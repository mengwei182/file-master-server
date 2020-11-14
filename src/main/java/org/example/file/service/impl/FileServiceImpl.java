package org.example.file.service.impl;

import org.example.common.json.Result;
import org.example.common.util.CommonUtils;
import org.example.common.util.FileUtils;
import org.example.file.controller.vo.ModifyFilenameVo;
import org.example.file.repository.entity.FileEntity;
import org.example.file.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements FileService {
  @Override
  public Result getFiles(String path) {
    try {
      List<FileEntity> fileEntities = new ArrayList<>();
      if (CommonUtils.isEmpty(path)) {
        fileEntities.add(FileEntity.buildInitialFile());
        return Result.success(fileEntities);
      }
      if (path.equals(FileEntity.DEFAULT_ROOT_PATH)) {
        File[] rootFile = FileUtils.getRootFile();
        if (!CommonUtils.isEmpty(rootFile)) {
          for (File file : rootFile) {
            FileEntity fileEntity = FileEntity.buildFileEntity(file);
            fileEntity.setName(FileUtils.handleWindowsRootFilename(file));
            fileEntities.add(fileEntity);
          }
        }
        return Result.success(fileEntities);
      }
      File[] files = FileUtils.getFiles(path);
      if (!CommonUtils.isEmpty(files)) {
        for (File file : files) {
          fileEntities.add(FileEntity.buildFileEntity(file));
        }
      }
      return Result.success(fileEntities);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Result.success();
  }

  @Override
  public Result modifyFilename(ModifyFilenameVo modifyFilenameVo) {
    File[] files = FileUtils.getFiles(modifyFilenameVo.getPath());
    if (!CommonUtils.isEmpty(files)) {
      if (FileUtils.checkRepeatName(files, modifyFilenameVo.getOriginFilename(), modifyFilenameVo.getTargetFilename())) {
        if (FileUtils.renameFiles(files, modifyFilenameVo.getOriginFilename(), modifyFilenameVo.getTargetFilename())) {
          return Result.success();
        }
      }
    }
    return Result.failure();
  }
}
