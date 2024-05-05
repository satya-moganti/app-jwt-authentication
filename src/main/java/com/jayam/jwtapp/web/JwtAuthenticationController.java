package com.jayam.jwtapp.web;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jayam.jwtapp.config.filter.JwtTokenUtil;
import com.jayam.jwtapp.dto.JwtRequest;
import com.jayam.jwtapp.dto.UserData;
import com.jayam.jwtapp.dto.UserResponse;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	public static final Logger REG_LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@PostMapping(value = "authenticate",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {
		UserResponse response = null;
		authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());

		final UserDetails userDetails = jwtUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		if(null!=userDetails) {
			final String token = jwtTokenUtil.generateToken(userDetails);
			List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
			UserData userData = new UserData(userDetails.getUsername(),roles,token);
			REG_LOGGER.info("UserData response  :"+userData);

			return new  ResponseEntity<UserData>(userData,HttpStatus.OK);
		}else {
			return new  ResponseEntity<String>("Username or password doesn't match",HttpStatus.BAD_REQUEST);

		}
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
