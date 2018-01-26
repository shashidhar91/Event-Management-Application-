
package com.managedbean;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ResourceHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.w3c.dom.Document;

import com.dao.impl.EventDAOImpl;
import com.dao.impl.UserDAOImpl;
import com.dao.interfaces.EventDAO;
import com.dao.interfaces.UserDAO;
import com.model.EventModel;
import com.model.EventTypeModel;
import com.model.LocationModel;
import com.model.OrderModel;
import com.model.UserModel;
import com.util.GeneralUtility;
import com.util.MyDateFormatter;
import com.util.SessionBean;

@ManagedBean(name = "eventMB")
@ViewScoped
public class EventManagedBean {

    private Integer eventId;
    private int userId;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
    private Integer userID ;
    private String eventTitle;
    private String eventType;
    private Date startDate;
    private Date endDate;
    private Double ticketPrice;
    private String noOfTickets;
    private String description;
    private Integer locationId;
    private Integer eventTypeId;
    private String longitude;
    private String latitude;
    private String locationName;
    private String address;
    private String orgnizerName;
    private Part part;
	private String encodeImage;
	private String organizerName;
    private String centerGeoMap = "41.850033, -87.6500523";
    private MapModel geoModel;
    private InputStream inputStream;
    private byte[] photo;
    private String startDateForView;
    private String endDateForView;
    private String countryName;
    private String coordinates;
    private String remainingTickets;
    private String successmsg;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSuccessmsg() {
        return successmsg;
    }

    public void setSuccessmsg(String successmsg) {
        this.successmsg = successmsg;
    }
    
	private List<EventManagedBean> eventManagedBeans;
    private List<EventManagedBean> eventTypesManagedBeans;
    private List<EventManagedBean> countryList;
    private List<EventModel> allEventByProperty;

    public void setAllEventByProperty(List<EventModel> allEventByProperty) {
        this.allEventByProperty = allEventByProperty;
    }
    
    @PostConstruct
    public void init() {
    	
    	eventTypesManagedBeans = new ArrayList<EventManagedBean>();
    	countryList = new ArrayList<EventManagedBean>();
        geoModel = new DefaultMapModel();
        
        EventDAO eventDAO = new EventDAOImpl();
		List<EventTypeModel> eventTypeModels = eventDAO.getEventTypes();
		
		if(eventTypeModels != null && eventTypeModels.size() > 0) {
			for(int i=0; i<eventTypeModels.size(); i++) {
				EventManagedBean eventManagedBean = new EventManagedBean();
				EventTypeModel eventTypeModel = eventTypeModels.get(i);
				eventManagedBean.setEventTypeId(eventTypeModel.getEventTypeId());
				eventManagedBean.setEventType(eventTypeModel.getEventType());
				eventTypesManagedBeans.add(eventManagedBean);
			}
		}
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			EventManagedBean eventManagedBean = new EventManagedBean();
			eventManagedBean.setLocationName(obj.getDisplayCountry());
			countryList.add(eventManagedBean);
		}
    }
    
    public void onGeocode(GeocodeEvent event) {
    	
        List<GeocodeResult> results = event.getResults();
         
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            this.longitude = ""+center.getLat();
            this.latitude = ""+center.getLat();
            centerGeoMap = center.getLat() + "," + center.getLng();
             
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
    	
    }
    
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }   

    public String getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(String noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
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

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrgnizerName() {
		return orgnizerName;
	}

	public void setOrgnizerName(String orgnizerName) {
		this.orgnizerName = orgnizerName;
	}

	public Part getPart() {
		
		if(part !=null && part.getSize()>0){
			try {
				inputStream=part.getInputStream();
			} catch (IOException e) {}
		}
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public String getEncodeImage() {
		return encodeImage;
	}

	public void setEncodeImage(String encodeImage) {
		this.encodeImage = encodeImage;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public String getCenterGeoMap() {
		return centerGeoMap;
	}

	public void setCenterGeoMap(String centerGeoMap) {
		this.centerGeoMap = centerGeoMap;
	}

	public MapModel getGeoModel() {
		return geoModel;
	}

	public void setGeoModel(MapModel geoModel) {
		this.geoModel = geoModel;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getStartDateForView() {
		return startDateForView;
	}

	public void setStartDateForView(String startDateForView) {
		this.startDateForView = startDateForView;
	}

	public String getEndDateForView() {
		return endDateForView;
	}

	public void setEndDateForView(String endDateForView) {
		this.endDateForView = endDateForView;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<EventManagedBean> getEventManagedBeans() {
		return eventManagedBeans;
	}

	public void setEventManagedBeans(List<EventManagedBean> eventManagedBeans) {
		this.eventManagedBeans = eventManagedBeans;
	}

	public List<EventManagedBean> getEventTypesManagedBeans() {
		return eventTypesManagedBeans;
	}

	public void setEventTypesManagedBeans(List<EventManagedBean> eventTypesManagedBeans) {
		this.eventTypesManagedBeans = eventTypesManagedBeans;
	}

	public List<EventManagedBean> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<EventManagedBean> countryList) {
		this.countryList = countryList;
	}
	
	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getRemainingTickets() {
		return remainingTickets;
	}

	public void setRemainingTickets(String remainingTickets) {
		this.remainingTickets = remainingTickets;
	}

	public List<EventModel> getAllEventByProperty() {
		
		EventDAO eventDAO = new EventDAOImpl();
		eventManagedBeans = new ArrayList<EventManagedBean>();
		Integer roleId = 0;

		Integer userId = SessionBean.getUserId();
		List<EventModel> eventModels = eventDAO.getEventsByProperty(eventTitle, locationId, eventTypeId, startDate== null? null:MyDateFormatter.formatDate(startDate));
			if (eventModels != null && eventModels.size() > 0) {
				for (int i = 0; i < eventModels.size(); i++) {
					EventManagedBean eventManagedBean = new EventManagedBean();
					EventModel eventModel = eventModels.get(i);
					eventManagedBean.setEventId(eventModel.getEventId());
					eventManagedBean.setEventTitle(eventModel.getTitle());
					eventManagedBean.setStartDate(eventModel.getStartDate());
					eventManagedBean.setEndDate(eventModel.getEndDate());
					eventManagedBean.setTicketPrice(eventModel.getTicketPrice());
					this.eventManagedBeans.add(eventManagedBean);
				}
			}
			return eventModels;
	}
	
	public String searchEventByProperty() {
		
		EventDAO eventDAO = new EventDAOImpl();
		eventManagedBeans = new ArrayList<EventManagedBean>();
		Integer roleId = 0;
		ResourceHandler resourceHandler = FacesContext.getCurrentInstance().getApplication().getResourceHandler();
		List<EventModel> eventModels = eventDAO.getEventsByProperty(eventTitle, locationId, eventTypeId,startDate== null? null:MyDateFormatter.formatDate(startDate));
			if (eventModels != null && eventModels.size() > 0) {
				for (int i = 0; i < eventModels.size(); i++) {
					EventManagedBean eventManagedBean = new EventManagedBean();
					EventModel eventModel = eventModels.get(i);
					
					if(eventModel.getLocationModel() != null) {
						LocationModel locationModel = eventModel.getLocationModel();
						eventManagedBean.setLocationName(locationModel.getVenueName());
						eventManagedBean.setAddress(locationModel.getAddress());
						eventManagedBean.setCoordinates(locationModel.getLongitude()+", "+locationModel.getLatitude());
						eventManagedBean.setLongitude(locationModel.getLongitude());
						eventManagedBean.setLatitude(locationModel.getLatitude());
					}
					
					if(eventModel.getEventTypeModel() != null) {
						EventTypeModel eventTypeModel = eventModel.getEventTypeModel();
						eventManagedBean.setEventType(eventType);
					}
					
					if(eventModel.getOrderModels() != null && eventModel.getOrderModels().size() > 0) {
						eventManagedBean.setRemainingTickets(""+(eventModel.getNoOfTicket() - eventModel.getOrderModels().size()));
					} else 	if(eventModel.getNoOfTicket() != null) {
						eventManagedBean.setRemainingTickets(""+eventModel.getNoOfTicket());
					} else {
						eventManagedBean.setRemainingTickets("0");
					}
					
					eventManagedBean.setEventId(eventModel.getEventId());
					eventManagedBean.setEventTitle(eventModel.getTitle());
					eventManagedBean.setDescription(eventModel.getDescription());
					eventManagedBean.setStartDateForView(MyDateFormatter.timeStampToString(eventModel.getStartDate()));
					eventManagedBean.setEndDateForView(MyDateFormatter.timeStampToString(eventModel.getEndDate()));
					eventManagedBean.setTicketPrice(eventModel.getTicketPrice());
					
					if(this.part == null ) {
						if(eventModel.getEventPhotoName() != null) {
							this.setPhoto(GeneralUtility.readImage("D:/eventImages"+"/"+eventModel.getEventPhotoName()));
						}else {
							try {
								InputStream input = resourceHandler.createResource("theme/images/no-image-big.png").getInputStream();
								if(input != null) {
									this.setPhoto(IOUtils.toByteArray(input));
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					
					if (this.photo != null) {
						this.encodeImage = new String(Base64.encodeBase64(this.photo));
					} else {
//						try {
//							InputStream input = resourceHandler.createResource("theme/images/no-image-big.png").getInputStream();
//							if(input != null) {
//								this.photo = IOUtils.toByteArray(input);
//								this.encodeImage = new String(Base64.encodeBase64(this.photo));
//							}
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
					}
					eventManagedBean.setEncodeImage(encodeImage);
					this.eventManagedBeans.add(eventManagedBean);
				}
			}
			return "";
	}

    
    public String addEvent(){
        
        EventModel eventModel = new EventModel();
        eventModel.setTitle(this.eventTitle);
        eventModel.setStartDate(MyDateFormatter.dateToTimeStample(this.startDate));
        eventModel.setEndDate(MyDateFormatter.dateToTimeStample(this.endDate));
        eventModel.setTicketPrice(this.ticketPrice);
        eventModel.setNoOfTicket(this.noOfTickets);
        eventModel.setDescription(this.description);
        eventModel.setOrganizerName(this.organizerName);
        
        LocationModel locationModel = new LocationModel();
        locationModel.setVenueName(this.locationName);
        locationModel.setAddress(this.address);
        locationModel.setLongitude(longitude);
        locationModel.setLatitude(latitude);
        
        if(part !=null && part.getSize()>0) {
			try {
				inputStream=part.getInputStream();
				String photoName = this.eventTitle+ System.currentTimeMillis() + ".png";
				String path = "D:/eventImages";
				GeneralUtility.uploadImage(path, photoName, inputStream);
				eventModel.setEventPhotoName(photoName);
			} catch (IOException e) {}
		}

        EventDAO eventDAO = new EventDAOImpl();
        eventDAO.addLocation(locationModel);
        eventModel.setLocationModel(locationModel);
        
        EventTypeModel eventTypeModel = eventDAO.getEventTypeModel(eventTypeId);
        if(eventTypeModel != null) {
        	eventModel.setEventTypeModel(eventTypeModel);
        }
        eventDAO.addEvent(eventModel);
        FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Event", "Event Created Success"));
		context.getExternalContext().getFlash().setKeepMessages(true);
        
        return "SUCCESS";
        
               
    }
    
     public String registerEvent()
     {
        EventDAO eventDAO = new EventDAOImpl();
        eventDAO.registerEvent(this.userId);
        successmsg = "Registration Successfull";
        return successmsg;
    }
    
    
	public String getEventByEventId() {
		
		EventDAO eventDAO = new EventDAOImpl();
		List<EventTypeModel> eventTypeModels = eventDAO.getEventTypes();
		
		for(int i=0; i<eventTypeModels.size(); i++) {
			EventManagedBean eventManagedBean = new EventManagedBean();
			EventTypeModel eventTypeModel = eventTypeModels.get(i);
			eventManagedBean.setEventTypeId(eventTypeModel.getEventTypeId());
			eventManagedBean.setEventType(eventTypeModel.getEventType());
			eventTypesManagedBeans.add(eventManagedBean);
		}
		return "";
	}
    
    public String clearFields() {
    	
    	this.getAllEventByProperty();
    	return "";
    }
    
    
    public String orderTicket(){
    	
    	HttpSession session = SessionBean.getSession();
    	if(session.getAttribute("userId") == null ) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    		try {
    			ec.redirect("pages/user-detail.xhtml");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	} else {
    		
    		UserDAO userDAO = new UserDAOImpl();
    		EventDAO eventDAO = new EventDAOImpl();
    		
    		UserModel userModel = userDAO.getUserModel(Integer.parseInt(session.getAttribute("userId").toString()));
    		EventModel eventModel = eventDAO.getEventModel(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("eventId")));
    		OrderModel orderModel = new OrderModel();
    		orderModel.setOrderStatus("Active");
    		orderModel.setEventModel(eventModel);
    		orderModel.setUserModel(userModel);
    		
    		eventDAO.addOrder(orderModel);
    		FacesContext context = FacesContext.getCurrentInstance();
    		context.addMessage(null, new FacesMessage("Order", "Event Ticket Ordered"));
    		context.getExternalContext().getFlash().setKeepMessages(true);
    	}
		
    	return "";
    }
    
   
    public String getCoordinatesByLocation() {
    	
    	try {
			String coordinates[] = getLatLongPositions(this.locationName);
			if(coordinates != null && coordinates.length == 2) {
				this.longitude = coordinates[0];
				this.latitude = coordinates[1];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
    }
    
    public static String[] getLatLongPositions(String address) throws Exception {
    	
	      int responseCode = 0;
	      String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true";
	      URL url = new URL(api);
	      HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	      httpConnection.connect();
	      responseCode = httpConnection.getResponseCode();
	      if(responseCode == 200) {
	    	  DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
	    	  Document document = builder.parse(httpConnection.getInputStream());
	    	  XPathFactory xPathfactory = XPathFactory.newInstance();
	    	  XPath xpath = xPathfactory.newXPath();
	    	  XPathExpression expr = xpath.compile("/GeocodeResponse/status");
	    	  String status = (String)expr.evaluate(document, XPathConstants.STRING);
	    	  if(status.equals("OK")) {
	    		  expr = xpath.compile("//geometry/location/lat");
	    		  String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
	    		  expr = xpath.compile("//geometry/location/lng");
	    		  String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
	    		  return new String[] {latitude, longitude};
	    	  }
	    	  else {
	    		  throw new Exception("Error from the API - response status: "+status);
	    	  }
	      }
	      return null;
    }
    
    
}