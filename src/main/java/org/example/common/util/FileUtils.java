package org.example.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
  private static final String[] DRIVES = new String[]{"A:\\", "B:\\", "C:\\", "D:\\", "E:\\", "F:\\", "G:\\", "H:\\", "I:\\", "J:\\", "K:\\", "L:\\", "M:\\", "N:\\", "O:\\", "P:\\", "Q:\\", "R:\\", "S:\\", "T:\\", "U:\\", "V:\\", "W:\\", "X:\\", "Y:\\", "Z:\\"};

  public static boolean renameFiles(File[] files, String oldName, String newName) {
    return rename(files, oldName, newName);
  }

  public static File[] getRootFile() {
    return File.listRoots();
  }

  public static boolean checkRepeatName(File[] files, String oldName, String newName) {
    if (files == null) {
      return false;
    }
    List<String> filenames = getFilenames(files);
    for (File file : files) {
      String name = file.getName();
      Matcher matcher = Pattern.compile(".*" + oldName + ".*").matcher(name);
      if (!matcher.matches()) {
        continue;
      }
      String transName = name.replace(oldName, newName);
      if (filenames.contains(transName)) {
        return false;
      }
    }
    return true;
  }

  private static List<String> getFilenames(File[] files) {
    List<String> filenames = new ArrayList<>();
    if (files != null) {
      for (File file : files) {
        filenames.add(file.getName());
      }
    }
    return filenames;
  }

  public static boolean autoModifyFileName(File[] files) {
    if (files != null) {
      files = sortFile(files);
      for (int i = 0; i < files.length; i++) {
        if (!rename(files[i], files[i].getName(), String.valueOf(i + 1))) {
          return false;
        }
      }
    }
    return true;
  }

  private static File[] sortFile(File[] files) {
    Map<String, File> originFileMap = new HashMap<>();
    for (File file : files) {
      originFileMap.put(file.getName(), file);
    }
    List<String> filenames = new ArrayList<>(originFileMap.keySet());
    filenames.sort(String::compareTo);
    List<File> newFileList = new ArrayList<>();
    for (String filename : filenames) {
      newFileList.add(originFileMap.get(filename));
    }
    return newFileList.toArray(new File[]{});
  }

  private static boolean rename(File[] files, String oldName, String newName) {
    for (File file : files) {
      if (!rename(file, oldName, newName)) {
        return false;
      }
    }
    return true;
  }

  private static boolean rename(File file, String oldName, String newName) {
    String fileName = file.getName();
    String parentPath = file.getParent();
    if (CommonUtils.isEmpty(parentPath)) {
      return false;
    }
    String newFileName = fileName.replace(oldName, newName);
    return file.renameTo(new File(parentPath + File.separator + newFileName));
  }

  private String getFileSuffixName(File file) {
    String fileName = file.getName();
    String[] split = fileName.split("\\.");
    return split[split.length - 1].toLowerCase();
  }

  private String removeSuffixName(File file) {
    String fileSuffixName = getFileSuffixName(file);
    return file.getName().replace("." + fileSuffixName, "");
  }

  public static Map<String, String> getRootFileNamePaths() {
    File[] files = File.listRoots();
    if (!CommonUtils.isEmpty(files)) {
      Map<String, String> fileNamePaths = new HashMap<>();
      for (File file : files) {
        fileNamePaths.put(file.getAbsolutePath(), file.getAbsolutePath());
      }
      return fileNamePaths;
    }
    return null;
  }

  public static List<String> getRootFileNames() {
    File[] files = File.listRoots();
    if (!CommonUtils.isEmpty(files)) {
      return handleWindowsRootFilename(files);
    }
    return null;
  }

  public static List<String> getFileNames(String path) {
    if (CommonUtils.isEmpty(path)) {
      return getRootFileNames();
    }
    File parentFile = new File(path);
    if (!parentFile.exists()) {
      return null;
    }
    File[] files = parentFile.listFiles();
    if (CommonUtils.isEmpty(files)) {
      return null;
    }
    List<String> fileNames = new ArrayList<>();
    for (File file : files) {
      if (!file.isHidden()) {
        fileNames.add(file.getName());
      }
    }
    return fileNames;
  }

  public static Map<String, String> getFileNamePaths(String path) {
    if (!CommonUtils.isEmpty(path)) {
      File file = new File(path);
      if (file.isDirectory()) {
        File[] files = file.listFiles();
        if (files != null) {
          Map<String, String> fileNamePaths = new HashMap<>();
          for (File f : files) {
            fileNamePaths.put(f.getName(), f.getAbsolutePath());
          }
          return fileNamePaths;
        }
      }
    }
    return null;
  }

  public static File[] getFiles(String path) {
    File file = new File(path);
    return file.listFiles();
  }

  public static List<String> handleWindowsRootFilename(File[] files) {
    List<String> rootFilenames = new ArrayList<>();
    for (File file : files) {
      rootFilenames.add(handleWindowsRootFilename(file));
    }
    return rootFilenames;
  }

  public static String handleWindowsRootFilename(File file) {
    for (String drive : DRIVES) {
      if (file.getAbsolutePath().startsWith(drive) && file.isDirectory() && file.getParentFile() == null) {
        return drive;
      }
    }
    return null;
  }

}
