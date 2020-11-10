package org.example.common.util;

import java.util.Collection;
import java.util.Map;

public class CommonUtils {
  public static boolean isEmpty(String string) {
    return string == null || string.length() == 0;
  }

  public static boolean isEmpty(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  public static boolean isEmpty(Map<?, ?> map) {
    return map == null || map.isEmpty();
  }

  public static boolean isEmpty(Object[] objects) {
    return objects == null || objects.length == 0;
  }
}
