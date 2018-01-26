
package com.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order", catalog = "nt_event_group")
public class OrderModel implements Serializable {

	private static final long serialVersionUID = 669445920318321956L;

	@Id
    @GeneratedValue()
    @Column(name = "order_id")
    private Integer orderId;
    
    @Column(name = "no_of_tickets")
    private Integer noOfTickets;
    
    @Column(name = "order_status")
    private String orderStatus;
    
    
    @ManyToOne
	@JoinColumn(name="event_id")
	private EventModel eventModel;
    
    @ManyToOne
	@JoinColumn(name="user_id")
	private UserModel userModel;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(Integer noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public EventModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(EventModel eventModel) {
		this.eventModel = eventModel;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

}
