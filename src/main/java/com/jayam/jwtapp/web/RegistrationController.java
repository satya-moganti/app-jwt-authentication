
package com.jayam.jwtapp.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayam.jwtapp.config.filter.JwtTokenUtil;
import com.jayam.jwtapp.dto.UserRequest;
import com.jayam.jwtapp.dto.UserResponse;
import com.jayam.jwtapp.mysql.entity.User;
import com.jayam.jwtapp.service.JwtUserDetailsService;
import com.jayam.jwtapp.utility.AppConstants;

import io.swagger.annotations.Api;

@CrossOrigin
@RestController
@RequestMapping(AppConstants.PATH_REGISTER_USER)
@Api(value = "Registration controller to create or modify users	", protocols = "http")
public class RegistrationController {
	public static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	
	@PostMapping(value=AppConstants.PATH_EMPTY,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser( @Valid @RequestBody UserRequest userRequest) {
		UserResponse response = null;		
		if(null!=userRequest){
			String password= passwordEncoder.encode(userRequest.getPassword());
			userRequest.setPassword(password);			
			User user= jwtUserDetailsService.saveUserDetails(userRequest);
			
			return new  ResponseEntity<String>("User Creation Succesful: "+user.getUsername(),HttpStatus.CREATED);	
		}else{
			return new  ResponseEntity<String>("Not Valid Request",HttpStatus.BAD_REQUEST);
		}
			
	}
	



}