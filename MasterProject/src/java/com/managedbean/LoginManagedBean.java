package com.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.dao.impl.UserDAOImpl;
import com.dao.interfaces.UserDAO;
import com.model.UserLoginModel;
import com.model.UserModel;
import com.util.SessionBean;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginManagedBean {
	// ############################### Start All Mandatory Variables ##########################################################
	private String username;
	private String password;
	// ############################### End All Mandatory Variables ############################################################

	// ############################### Start Injecting Business Objects #######################################################
	// ############################### End Injecting Business Objects #########################################################
	
	//################################ Start Getter and Setter Methods ########################################################
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
	
	// ############################### Start Init(First Execute when ManageBean call) Method #################################
	public String userLogin() {

		UserDAO userDAO = new UserDAOImpl();
		try {
			UserLoginModel userLoginModel =  userDAO.getActiveUsers(username, password);
			if (userLoginModel != null) {
				UserModel userModel = userLoginModel.getUserModel();
				HttpSession session = SessionBean.getSession();
				session.setAttribute("userId", userModel.getUserId());

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Successful", "Welcome"));
				context.getExternalContext().getFlash().setKeepMessages(true);
				session.setAttribute("username", userModel.getFirstName());
				return "SUCCESS";
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Error!!", "Please Provide Valid Credentials"));
		context.getExternalContext().getFlash().setKeepMessages(true);
		return "WRONG";
	}
	
	public void logout(String from) throws Exception {

		HttpSession session = SessionBean.getSession();
		session.invalidate();
		if(from.equalsIgnoreCase("inside")) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
		}
		
	}
	
}
