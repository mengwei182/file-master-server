package org.example.file.service;

import org.example.common.json.Result;
import org.example.file.controller.vo.ModifyFilenameVo;

public interface FileService {
  Result getFiles(String path);

  Result modifyFilename(ModifyFilenameVo modifyFilenameVo);
}
