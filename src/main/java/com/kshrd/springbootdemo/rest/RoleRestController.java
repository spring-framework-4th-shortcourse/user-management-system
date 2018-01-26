package com.kshrd.springbootdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshrd.springbootdemo.model.Role;
import com.kshrd.springbootdemo.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Role", description = "Role Endpoints")
@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value = "View a list of roles",  
			  	  response = Role.class,
			  	  responseContainer = "List")
	@GetMapping
	public List<Role> getRoleById(){
		return roleService.getAllRoles();
	}
	
	@ApiOperation(value = "Get a specific role by role ID",  
		  	  response = Role.class)
	@GetMapping("/{id}")
	public Role getRoleById(@PathVariable Integer id){
		return roleService.getRoleById(id);
	}
	
}
