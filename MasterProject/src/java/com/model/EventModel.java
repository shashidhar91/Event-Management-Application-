
package com.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event", catalog = "nt_event_group")
public class EventModel implements Serializable {

	private static final long serialVersionUID = 669445920318321956L;

	@Id
    @GeneratedValue()
    @Column(name = "event_id")
    private Integer eventId;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "start_date")
    private Timestamp startDate;
    
    @Column(name = "end_date")
    private Timestamp endDate;
    
    @Column(name = "ticket_price")
    private Double ticketPrice;
    
    @Column(name = "no_of_ticket")
    private Integer noOfTicket;
    
    @Column(name="event_photo_name")
    private String eventPhotoName;
    
    @Column(name="organizer_name")
    private String organizerName;
    
    @ManyToOne
	@JoinColumn(name="location_id")
	private LocationModel locationModel;
    
    @ManyToOne
	@JoinColumn(name="event_type")
	private EventTypeModel eventTypeModel;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="eventModel")
	private Set<OrderModel> orderModels  = new HashSet<OrderModel>(0);
    

    public EventModel() { }

    public EventModel(Integer eventId, String title, String description, Timestamp startDate, Timestamp endDate, Double ticketPrice) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketPrice = ticketPrice;
    }
    
    public EventModel(String title, String description, Timestamp startDate, Timestamp endDate, Double ticketPrice) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ticketPrice = ticketPrice;
    }
    
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

	public Integer getNoOfTicket() {
		return noOfTicket;
	}

	public void setNoOfTicket(Integer noOfTicket) {
		this.noOfTicket = noOfTicket;
	}

	public String getEventPhotoName() {
		return eventPhotoName;
	}

	public void setEventPhotoName(String eventPhotoName) {
		this.eventPhotoName = eventPhotoName;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public LocationModel getLocationModel() {
		return locationModel;
	}

	public void setLocationModel(LocationModel locationModel) {
		this.locationModel = locationModel;
	}

	public EventTypeModel getEventTypeModel() {
		return eventTypeModel;
	}

	public void setEventTypeModel(EventTypeModel eventTypeModel) {
		this.eventTypeModel = eventTypeModel;
	}

	public Set<OrderModel> getOrderModels() {
		return orderModels;
	}

	public void setOrderModels(Set<OrderModel> orderModels) {
		this.orderModels = orderModels;
	}

    public void setNoOfTicket(String noOfTickets) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
    }

}
