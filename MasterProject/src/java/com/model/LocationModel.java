package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location", catalog="nt_event_group")
public class LocationModel implements Serializable {

	private static final long serialVersionUID = 8944664172620737510L;

	@GeneratedValue
	@Id
	@Column(name="location_id")
	private Integer locationId;
	
	@Column(name="venue_name")
	private String venueName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city ;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="longitude")
	private String longitude;
	
	@Column(name="latitude")
	private String latitude;
	
	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
}
