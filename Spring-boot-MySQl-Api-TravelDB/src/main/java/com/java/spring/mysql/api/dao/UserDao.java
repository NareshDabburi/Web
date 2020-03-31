package com.java.spring.mysql.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.java.spring.mysql.api.model.User;

public interface UserDao extends CrudRepository<User, Integer>{
	@Query(value = "select * from user where email = ?1",nativeQuery=true)
	List<User> getuserbyemail(String email);

}
