package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_login", catalog="nt_event_group")
public class UserLoginModel implements Serializable {

	private static final long serialVersionUID = -7497448849488642842L;

	@GeneratedValue
	@Id
	@Column(name="user_login_id")
	private Integer userLoginId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;

	@ManyToOne
	@JoinColumn(name="user_id")
	private UserModel userModel;
	
	public Integer getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(Integer userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
}
