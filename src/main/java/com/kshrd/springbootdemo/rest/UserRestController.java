package com.kshrd.springbootdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kshrd.springbootdemo.model.User;
import com.kshrd.springbootdemo.model.response.Response;
import com.kshrd.springbootdemo.model.response.ResponseList;
import com.kshrd.springbootdemo.model.response.ResponseSingle;
import com.kshrd.springbootdemo.service.UserService;
import com.kshrd.springbootdemo.utility.Paging;

@RestController
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/api/users")
	public Response getAllUsers(Paging paging){	
		System.out.println(paging);
		List<User> users = userService.findWithPagination(paging);
		if(users.isEmpty())
			return new ResponseList("No users!", users, paging);
		
		return new ResponseList("Get users successfully!", users, paging);
	}
	
	@GetMapping("/api/users/{id}")
	public Response getUserById(@PathVariable Integer id){
		User user = userService.searchById(id);
		if( user == null)
			return new ResponseSingle("User not found!", user);
		
		return new ResponseSingle("User found!", user);
	}
	
	@PostMapping("/api/users")
	public Response createUser(@RequestBody User user){
		boolean status = userService.createUser(user);
		if(status)
			return new Response("User has been created successfully!");
		return new Response("Can't create user!");
	}
	
	@DeleteMapping("/api/users/{id}")
	public Response removeUser(@PathVariable Integer id){
		boolean status = userService.removeUser(id);
		if(status)
			return new Response("User has been removed successfully!");
		return new Response("Can't remove user!");
	}
	
	@PutMapping("/api/users")
	public Response updateUser(@RequestBody User user){
		boolean status = userService.updateUser(user);
		if(status)
			return new Response("User has been updated successfully!");
		return new Response("Can't update user with id " + user.getId() + " !");
	}
	
}
