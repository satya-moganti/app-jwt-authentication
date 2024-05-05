package com.jayam.jwtapp.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jayam.jwtapp.dto.UserRequest;
import com.jayam.jwtapp.mysql.entity.Role;
import com.jayam.jwtapp.mysql.entity.User;
import com.jayam.jwtapp.mysql.repo.RoleRepository;
import com.jayam.jwtapp.mysql.repo.UserRepository;
import com.jayam.jwtapp.utility.RoleName;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		System.out.println(user);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(),
					user.getPassword(),
					user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
					);
		} else {
			throw new UsernameNotFoundException("User not found");
		}

	}


	public User getUserDetails(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return user;        
	}

	@Transactional
	public User saveUserDetails(UserRequest userRequest) {

		String username = "J"+""+(userRequest.getFirstname().toLowerCase()
				+ userRequest.getLastname().toLowerCase()).replaceAll("\\s+", "_")
				+ "_" +UUID.randomUUID().toString().substring(0, 6);


		User user = userMapping(userRequest, username);
		List<Role> roleList =roleRepository.findAll();
		Set roles =roleList.stream().collect(Collectors.toSet());
		user.setRoles(roles);
		user = userRepository.save(user);
		return user;        
	}


	private User userMapping(UserRequest userRequest, String username) {
		User user =new User();
		user.setUsername(username);
		user.setFirstname(userRequest.getFirstname());
		user.setLastname(userRequest.getLastname());
		user.setEmail(userRequest.getEmail());

		user.setPassword(userRequest.getPassword());
		LocalDate now = LocalDate.now();
		user.setCreatedDate(Date.valueOf(now));
		user.setModifiedDate(Date.valueOf(now));
		user.setCreatedBy(RoleName.SYSTEM.toString());
		user.setModifiedBy(RoleName.SYSTEM.toString());
		user.setDateOfBirth(userRequest.getDateofbirth());
		return user;
	}


	private Role setRoleData(RoleName roleName) {
		Role role =new Role();
		role.setName(roleName);
		return role;
	}
	
}