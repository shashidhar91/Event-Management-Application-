package com.managedbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;

import com.dao.impl.UserDAOImpl;
import com.dao.interfaces.UserDAO;
import com.model.UserLoginModel;
import com.model.UserModel;
import com.util.MyDateFormatter;
import com.util.SessionBean;

@ManagedBean(name="userMB")
@ViewScoped
public class UserManagedBean {
	
	private Integer userId;
	private String firstName;
	private String lastName;
	private String cellPhone;
	private String address;
	private String gender;
	private Date dateOfBirth;
	private String username;
	private String password;
	
	private List<UserManagedBean> userManagedBeans;
	
	@ManagedProperty("#{utilMB}")
	private UtillManagedBean utillManagedBean;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<UserManagedBean> getUserManagedBeans() {
		return userManagedBeans;
	}
	public void setUserManagedBeans(List<UserManagedBean> userManagedBeans) {
		this.userManagedBeans = userManagedBeans;
	}
	public UtillManagedBean getUtillManagedBean() {
		return utillManagedBean;
	}
	public void setUtillManagedBean(UtillManagedBean utillManagedBean) {
		this.utillManagedBean = utillManagedBean;
	}
	public String getAllUserByProperty() {
			
		UserDAO userDAO = new UserDAOImpl();
		userManagedBeans = new ArrayList<UserManagedBean>();
		Integer roleId = 0;
			
	//		if (role != null && !role.equals("")) {
	//			roleId = Integer.parseInt(role);
	//		}
		Integer userId = SessionBean.getUserId();
		List<UserModel> userModels = userDAO.getAllUserByProperty(firstName, null);
			if (userModels != null && userModels.size() > 0) {
				for (int i = 0; i < userModels.size(); i++) {
					UserManagedBean userManagedBean = new UserManagedBean();
					userManagedBean.setUserId(userModels.get(i).getUserId());
					userManagedBean.setFirstName(userModels.get(i).getFirstName());
					userManagedBean.setLastName(userModels.get(i).getLastName());
					userManagedBean.setCellPhone(userModels.get(i).getCellPhone());
					userManagedBean.setAddress(userModels.get(i).getAddress());
					userManagedBean.setGender(userModels.get(i).getGender());
					userManagedBean.setDateOfBirth(userModels.get(i).getDateOfBirth());
					this.userManagedBeans.add(userManagedBean);
				}
			}
			return "";
	}
	
	public String addUser() {
		
		UserDAO userDAO = new UserDAOImpl();
		UserModel userModel = null;
		if(this.utillManagedBean.getMode().equals("Update")) {
			userModel = userDAO.getUserModel(this.utillManagedBean.getID());
		} else {
			userModel = new UserModel();
		}
		userModel.setFirstName(firstName);
		userModel.setLastName(lastName);
		userModel.setCellPhone(cellPhone);
		userModel.setGender(gender);
		userModel.setDateOfBirth(MyDateFormatter.dateToTimeStample(dateOfBirth));
		userModel.setAddress(address);
		if(this.utillManagedBean.getMode().equals("Update")) {
			userDAO.updateUserModel(userModel);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("User", "User Update Success"));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} else {
			userDAO.addUserModel(userModel);
			UserLoginModel userLoginModel = new UserLoginModel();
			
			userLoginModel.setUserModel(userModel);
			userLoginModel.setEmail(username);
			userLoginModel.setPassword(password);
			userDAO.addUserLoginModel(userLoginModel);
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("User", "User Added Success"));
			context.getExternalContext().getFlash().setKeepMessages(true);
			
			HttpSession session = SessionBean.getSession();
			session.setAttribute("userId", userModel.getUserId());
			session.setAttribute("username", userModel.getFirstName());
			
			return "REGISTER-SUCCESS";
			
		}
		
		return "ADD-SUCCESS";
	}
	
	public void getUser(SelectEvent event) {

		Integer editedUserId = ((UserManagedBean)event.getObject()).getUserId();
        this.utillManagedBean.setID(editedUserId);
        this.utillManagedBean.edit();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect("user-detail.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public String getUserByUserId() {
		
		if(this.utillManagedBean.getMode().equals("Update")) {
			UserDAO userDAO = new UserDAOImpl();
			UserModel userModel = userDAO.getUserModel(this.utillManagedBean.getID());
			
			if(userModel != null) {
				this.userId = userModel.getUserId();
				this.firstName = userModel.getFirstName();
				this.lastName = userModel.getLastName();
				this.address = userModel.getAddress();
				this.cellPhone = userModel.getCellPhone();
				this.gender = userModel.getGender();
				this.dateOfBirth = userModel.getDateOfBirth();
			}
			utillManagedBean.setMode("Update");
			utillManagedBean.setIsDelete(true);
			utillManagedBean.setHeading("Update");
			utillManagedBean.setSaveButtonTitle("Update");
		} else {
			utillManagedBean.setMode("Add");
			utillManagedBean.setIsDelete(false);
			utillManagedBean.setHeading("Add");
			utillManagedBean.setSaveButtonTitle("Save");
		}
		
		return "";
	}
	
	public String deleteUser() {
		
		UserDAO userDAO = new UserDAOImpl();
		UserModel userModel = userDAO.getUserModel(this.utillManagedBean.getID());
		userDAO.deleteUserModel(userModel);
		return "DELETE-CANCEL";
	}

    public String clearFields() {
    	
    	this.userId = null;
    	this.firstName = "";
    	this.lastName = "";
    	this.cellPhone = "";
    	this.dateOfBirth = null;
    	this.gender = "";
    	this.address = "";
    	this.utillManagedBean.setMode("Add");
    	this.getAllUserByProperty();
    	return "";
    }
    
    public String cancelUser() {
    	clearFields();
    	this.getAllUserByProperty();
		return "DELETE-CANCEL";
	}
	
	
	
}
