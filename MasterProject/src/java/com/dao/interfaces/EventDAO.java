
package com.dao.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.model.EventModel;
import com.model.EventTypeModel;
import com.model.Eventregistered;
import com.model.LocationModel;
import com.model.OrderModel;


public interface EventDAO {
 
    public void addEvent(EventModel model);
    public void addLocation(LocationModel locationModel);
    public void addOrder(OrderModel orderModel);
    
    public List<EventModel> getEventsByProperty(String eventName, Integer locationId, Integer eventType, String eventDate);
    public List<EventTypeModel> getEventTypes();
    public EventTypeModel getEventTypeModel(Integer eventTypeId);
    public EventModel getEventModel(Integer eventId);
    
    public void registerEvent(int userid);
    
}
