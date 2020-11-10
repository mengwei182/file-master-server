package org.example.file.controller;

import org.example.common.json.Result;
import org.example.file.controller.vo.ModifyFilenameVo;
import org.example.file.repository.entity.FileEntity;
import org.example.file.service.FileService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class FileController {
  @Resource
  private FileService fileService;

  @RequestMapping(value = "/files", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
  public Result getFiles(@RequestParam(defaultValue = FileEntity.DEFAULT_ROOT_PATH) String path) {
    return fileService.getFiles(path);
  }

  @RequestMapping(value = "/file/modify/name", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
  public Result modifyFilename(@RequestBody @Valid ModifyFilenameVo modifyFilenameVo) {
    return fileService.modifyFilename(modifyFilenameVo);
  }
}
