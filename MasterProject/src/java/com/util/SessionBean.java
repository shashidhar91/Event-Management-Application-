package com.util;



import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionBean {
	 
	 public static HttpSession getSession() {
	        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	    }
	 
	    public static HttpServletRequest getRequest() {
	        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    }
	 
	    public static String getUserName() {
	        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
	                .getExternalContext().getSession(false);
	        return session.getAttribute("username").toString();
	    }
	    
	    public static Integer getUserId() {
	    	
	    	 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		     return Integer.valueOf(session.getAttribute("userId").toString());
		     
	    }
	    public static Integer[] getUserIdAndRoleId() {
	    	
	    	 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	    	 Integer userData[] = new Integer[2];
	    	 userData[0] = Integer.valueOf(session.getAttribute("userId").toString());
	    	 userData[1] = Integer.valueOf(session.getAttribute("userRoleId").toString());
		     return userData;
		     
	    }
	 
	

}
