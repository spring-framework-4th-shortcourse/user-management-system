package com.kshrd.springbootdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshrd.springbootdemo.model.User;
import com.kshrd.springbootdemo.model.response.Response;
import com.kshrd.springbootdemo.model.response.ResponseList;
import com.kshrd.springbootdemo.model.response.ResponseSingle;
import com.kshrd.springbootdemo.service.UserService;
import com.kshrd.springbootdemo.utility.Paging;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "User", description = "User Endpoints")
@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@ApiOperation(value = "View a list of users",  
				  notes = "Multiple status values can be provided with comma seperated strings",
				  response = ResponseList.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "Page number", required = false, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "limit", value = "Record per page", required = false, dataType = "int", paramType = "query")
	})
	@ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved list of user", response = ResponseList.class),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource", response = Response.class),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden", response = Response.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found", response = Response.class)
    })
	@GetMapping
	public Response getAllUsers(@ApiIgnore Paging paging){	
		System.out.println(paging);
		List<User> users = userService.findWithPagination(paging);
		if(users.isEmpty())
			return new ResponseList("No users!", users, paging);
		
		return new ResponseList("Get users successfully!", users, paging);
	}
	
	@ApiOperation(value = "View specific user by ID", response = ResponseSingle.class)
	@GetMapping("/{id}")
	public Response getUserById(@PathVariable Integer id){
		User user = userService.searchById(id);
		if( user == null)
			return new ResponseSingle<User>("User not found!", user);
		
		return new ResponseSingle<User>("User found!", user);
	}
	
	@ApiOperation(value = "Create user", response = Response.class)
	@PostMapping
	public Response createUser(@RequestBody User user){
		boolean status = userService.createUser(user);
		if(status)
			return new Response("User has been created successfully!");
		return new Response("Can't create user!");
	}
	
	@ApiOperation(value = "Remove user by ID", response = Response.class)
	@DeleteMapping("/{id}")
	public Response removeUser(@PathVariable Integer id){
		boolean status = userService.removeUser(id);
		if(status)
			return new Response("User has been removed successfully!");
		return new Response("Can't remove user!");
	}
	
	@ApiOperation(value = "Update user by ID", response = Response.class)
	@PutMapping
	public Response updateUser(@RequestBody User user){
		boolean status = userService.updateUser(user);
		if(status)
			return new Response("User has been updated successfully!");
		return new Response("Can't update user with id " + user.getId() + " !");
	}
	
}
