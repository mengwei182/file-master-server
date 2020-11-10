package org.example.common.global;

import org.springframework.security.core.GrantedAuthority;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GlobalStaticObject {


  public static final String AUTHORITY_OBJECT = "AUTHORITY_OBJECT";
  public static final String INVALID_USERNAME = "INVALID_USERNAME";
  public static final String INVALID_PASSWORD = "INVALID_PASSWORD";
  public static final List<GrantedAuthority> INVALID_AUTHORITIES = new ArrayList<>();
}
