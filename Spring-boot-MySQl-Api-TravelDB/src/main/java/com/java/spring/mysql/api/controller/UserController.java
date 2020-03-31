package com.java.spring.mysql.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.java.spring.mysql.api.dao.UserDao;
import com.java.spring.mysql.api.model.User;
import com.java.spring.mysql.api.model.login;
@RestController
@RequestMapping("/travel")
public class UserController{
	@Autowired
	private UserDao dao;
	
	@CrossOrigin
	@PostMapping("/newuser")
	public String adduser(@RequestBody List<User> users) {
		dao.save(users);
		for(User s : users) {
			System.out.println(s.getId()+" "+s.getName()+" "+s.getEmail()+" "+s.getPhonenumber()+" "+s.getPassword());
		}
		return "Number of User Added : " + users.size();
	}
	
	@CrossOrigin
	@GetMapping("/getallusers")
	public List<User> getallusers() {
		return (List<User>) dao.findAll();
	}
	
	@CrossOrigin
	@PostMapping("/getuser/login")
	public boolean getuser(@RequestBody List<login> login_details) {
		if (login_details == null) {
			return false;
		}
		//System.out.println(login_details);
		String login_email=null,login_password=null;
		for(login iterator : login_details) {
			login_email = iterator.getEmail();
			login_password = iterator.getPassword();
		}
		
		
		
		/*
		for(User s : (List<User>) dao.findAll()) {
			//System.out.println(s.getEmail()+" "+s.getPassword());
			if(s.getEmail().trim().equals(login_email.trim()) && s.getPassword().trim().equals(login_password.trim()) == true) {
				flag = true;
				//break;
			}
		}*/
		
		boolean flag = false;
		List<User> list_usr=dao.getuserbyemail(login_email);
		int count =0;
		for(User usr_temp : list_usr) {
			count++;
		    if(usr_temp.getEmail().trim().equals(login_email.trim()) && usr_temp.getPassword().equals(login_password.trim()) == true) {
			flag = true;
		   }
		}
		
		
		//System.out.println(flag);
		
		/*
		
		if(list_usr.getEmail().trim().equals(login_email.trim()) && list_usr.getPassword().equals(login_password.trim()) == true) {
			flag = true;
		}
		System.out.println(flag);
		*/
		System.out.println(count);
		return flag;
		
	}
}
