
package com.jayam.jwtapp.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jayam.jwtapp.dto.UserMiniProfile;
import com.jayam.jwtapp.mysql.entity.User;
import com.jayam.jwtapp.service.JwtUserDetailsService;
import com.jayam.jwtapp.utility.AppConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping(AppConstants.PATH_USER_PROFILE)
@Api(value = "Api to get userprofile using authorization token	", protocols = "http")
public class UserProfileController {
	public static final Logger LOGGER = LoggerFactory.getLogger(UserProfileController.class);
	
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	@GetMapping(value="{username}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Request user details by passing valid username", response = String.class, 
	code = 200, notes = " Example : Jmram_bc599c")
	@ApiResponses(value = {    
	        @ApiResponse(code = 200, message = """
	        		{"username": "Jmram_bc599c","firstname": "M", "lastname": "RAM",  "email": "mram@gmail.com", "dateofbirth": "1990-12-22"}
	        		"""),
	        @ApiResponse(code = 400, message = "Not Valid Request"),
	        @ApiResponse(code = 401, message = "You are not authorized to view "),
	        @ApiResponse(code = 403, message = "Accessing the Userprofile Api Controller, you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "Not found")
	})
	public ResponseEntity<?> userProfile( @Valid @PathVariable("username") String username ,HttpRequest request) {
		UserMiniProfile userMiniProfile =null;
		
		String reqUser = (String)getRequesterDetails(request,"j-user");
		String isSD = (String) getRequesterDetails(request,"j-sd");
		
	  User user = jwtUserDetailsService.getUserDetails(username);
	  if(null!=user ) {
		   if(user.getUsername().equalsIgnoreCase(reqUser) || Boolean.valueOf(isSD)) {
			   
				 userMiniProfile =new UserMiniProfile(username, user.getFirstname(), user.getLastname(), user.getEmail(), user.getDateOfBirth());
				return new ResponseEntity<UserMiniProfile>(userMiniProfile,HttpStatus.OK);
		   }
			  return new ResponseEntity<String>("You are not allowed to access User details",HttpStatus.UNAUTHORIZED);
	  }
	  return new ResponseEntity<String>("User Details Not Found",HttpStatus.OK);

	  }

	private Object getRequesterDetails(HttpRequest request,String key) {
		return request.getHeaders().containsKey(key)?request.getHeaders().getFirst(key):null;
	}
	 


}