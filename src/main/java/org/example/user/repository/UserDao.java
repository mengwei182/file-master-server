package org.example.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.user.repository.entity.UserEntity;

@Mapper
public interface UserDao {
  boolean addUser(UserEntity userEntity);

  UserEntity getUserByUsername(String userName);

  UserEntity getUserByUsernamePassword(@Param("userName") String userName, @Param("password") String password);

  UserEntity getUserByPhoneNumber(String phoneNumber);
}
