package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event_type", catalog="nt_event_group")
public class EventTypeModel implements Serializable {

	private static final long serialVersionUID = -2741211131399768557L;

	@GeneratedValue
	@Id
	@Column(name="event_type_id")
	private Integer eventTypeId;
	
	@Column(name="category")
	private String category;
	
	@Column(name="event_type")
	private String eventType;

	public Integer getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	
	

}
