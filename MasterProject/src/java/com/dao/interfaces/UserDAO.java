package com.dao.interfaces;

import java.util.List;

import com.model.UserLoginModel;
import com.model.UserModel;

public interface UserDAO {
	
	public void addUserModel(UserModel userModel);
	
	public void addUserLoginModel(UserLoginModel userLoginModel);
	
	public void updateUserModel(UserModel userModel);
	
	public void deleteUserModel(UserModel userModel);
	
	public UserModel getUserModel(Integer userId);
	
	public UserLoginModel getActiveUsers(String name, String password); 
	
	public UserModel getAllPropertiesOfUserByUserId(Integer userId);
	
	public List<UserModel> getAllUserByProperty(String name, Integer role);
	
	public List<UserModel> getAllUsers();
	
	public UserModel getUserByEmployee(Integer employeeId);
	
	public UserModel getUserByUserName(String userName);
	
	public UserModel getUserDetailsByUserName(String userName);
	
	public List<UserModel> getAllUserByLocation(Integer location);
}

