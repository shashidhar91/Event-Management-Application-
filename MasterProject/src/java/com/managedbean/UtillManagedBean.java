package com.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="utilMB")
@SessionScoped
public class UtillManagedBean {
	
	// ############################### Start All Mandatory Variables ######################################
	private String mode = "Add";
	private Boolean isSave = true;
	private Boolean isEdit = false;
	private Boolean isCancel = false;
	private Boolean isDelete = false;
	private String  saveButtonTitle = "Save";
	private String heading = "Add ";
	private Integer ID ;
	// ############################### End All Mandatory Variables ########################################
	
	// ############################### Start Getter & Setter Methods ######################################
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Boolean getIsSave() {
		return isSave;
	}
	public void setIsSave(Boolean isSave) {
		this.isSave = isSave;
	}
	public Boolean getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}
	public Boolean getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	;public String getSaveButtonTitle() {
		return saveButtonTitle;
	}
	public void setSaveButtonTitle(String saveButtonTitle) {
		this.saveButtonTitle = saveButtonTitle;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	// ############################### End Getter & Setter Methods ######################################
	
	// ############################### Start Edit Location ##############################################
	public String edit() {

		this.isEdit = false;
		this.isDelete = false;
		this.isCancel = true;
		this.isSave = true;
		
		this.mode = "Update";
		saveButtonTitle = "Update";
		setHeading("Update ");
		return "EDIT";
	}
	// ############################### End Edit Location #################################################
	
	// ############################### Start View Location ###############################################
	public String view() {
		
		this.isEdit = true;
		this.isDelete = true;
		this.isCancel = false;
		this.isSave = false;
		this.mode = "View";
		this.heading = "View";
		return "";
	}
	// ############################### End View Location #################################################
	
	// ############################### Start Cancel from Edit and View Location ##########################
	public String cancel(String toPage) {
		
		if(toPage != null && toPage.equalsIgnoreCase("view")) {
			
			this.isEdit = true;
			this.isDelete = true;
			this.isCancel = false;
			this.isSave = false;
			heading = "View";
			return "CANCEL";
		} else if(toPage != null && toPage.equalsIgnoreCase("search")) {
			
			this.isEdit = false;
			this.isDelete = false;
			this.isCancel = true;
			this.isSave = true;
			heading = "Add";
			return "CANCEL-VIEW";
		}
		return "";
	}
	// ############################### End Cancel from Edit and View Location ###########################
	
	// ############################### Start Clear Fields of Location ###################################
	public String clearFields(String clear) {
		
		if(clear.equalsIgnoreCase("clear")) {
			
			this.isEdit = false;
			this.isDelete = false;
			this.isSave = true;
			this.isCancel = false;
			heading = "Add";
			this.mode = "Add";
			saveButtonTitle = "Save";
			return "SUCCESS";
		}
		return "";
	}
	// ############################### Start Clear Fields of Location ###################################
}
