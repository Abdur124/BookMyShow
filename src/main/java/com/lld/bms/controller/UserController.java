package com.lld.bms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lld.bms.dto.UserLoginResDTO;
import com.lld.bms.dto.UserSignUpReqDTO;
import com.lld.bms.mapper.UserMapperUtil;
import com.lld.bms.models.User;
import com.lld.bms.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<UserLoginResDTO> createUser(@RequestBody UserSignUpReqDTO userReqDTO) {
		User user = UserMapperUtil.dtoToModel(userReqDTO);
		User savedUser = userService.saveUser(user);
		UserLoginResDTO loginResDTO = UserMapperUtil.modelToDto(savedUser);
		return ResponseEntity.ok(loginResDTO);
	}

}
