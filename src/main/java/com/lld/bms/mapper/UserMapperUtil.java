package com.lld.bms.mapper;

import com.lld.bms.dto.UserLoginResDTO;
import com.lld.bms.dto.UserSignUpReqDTO;
import com.lld.bms.models.User;

public class UserMapperUtil {
	
	public static User dtoToModel(UserSignUpReqDTO dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserLoginResDTO modelToDto(User user){
        UserLoginResDTO dto = new UserLoginResDTO();
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getName());
        return dto;
    }

}
