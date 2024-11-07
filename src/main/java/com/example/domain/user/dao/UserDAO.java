package com.example.domain.user.dao;

import com.example.domain.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserDAO {
    public UserDTO getExistId(String id);
}
