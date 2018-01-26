
package com.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.interfaces.EventDAO;
import com.model.EventModel;
import com.model.EventTypeModel;
import com.model.Eventregistered;
import com.model.LocationModel;
import com.model.OrderModel;
import com.model.UserModel;
import com.util.HibernateUtil;
import com.util.MyDateFormatter;
import com.util.SessionBean;


public class EventDAOImpl implements EventDAO{
 
    @Override
    public void addEvent(EventModel eventModel) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(eventModel);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public void addLocation(LocationModel locationModel) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(locationModel);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

	@Override
	public List<EventModel> getEventsByProperty(String eventName,Integer locationId, Integer eventType, String eventDate) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuilder hql = new StringBuilder("select distinct em from EventModel em left outer join em.orderModels om left outer join fetch em.locationModel lm left outer join fetch em.eventTypeModel etm where em is not null ");
		
		if(eventName != null && !eventName.equals("")) {
			hql.append(" and em.title like :eventName");
		}
		if(locationId != null && locationId != 0) {
			hql.append(" and lm.locationId = :locationId");
		}
		if(eventType != null && eventType != 0) {
			hql.append(" and etm.eventTypeId = :eventTypeId");
		}
		if(eventDate != null) {
			hql.append(" and em.startDate <=:eventDate and em.endDate >=:eventDate");
		}
		
		Query query = session.createQuery(hql.toString());
		
		if(eventName != null && !eventName.equals("")) {
			query.setParameter("eventName", "%"+eventName+"%");
		}
		if(locationId != null && locationId != 0) {
			query.setParameter("locationId",locationId);
		}
		if(eventType != null && eventType != 0) {
			query.setParameter("eventTypeId",eventType);
		}
		if(eventDate != null) {
			query.setParameter("eventDate",MyDateFormatter.parseDate(eventDate));
		}
		List<EventModel> eventModels = query.list();
		
		if (eventModels.size() > 0) {
			return eventModels;
		} else
			return null;
	}

	@Override
	public List<EventTypeModel> getEventTypes() {
		

		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuilder hql = new StringBuilder("select distinct etm from EventTypeModel etm ");
		Query query = session.createQuery(hql.toString());
		List<EventTypeModel> eventTypeModels = query.list();

		if (eventTypeModels.size() > 0) {
			return eventTypeModels;
		} else
			return null;
	}

	@Override
	public EventTypeModel getEventTypeModel(Integer eventTypeId) {


		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuilder hql = new StringBuilder("select etm from EventTypeModel etm");
		if(eventTypeId != null){
			hql.append(" where etm.eventTypeId=:eventTypeId");
		}
		Query query = session.createQuery(hql.toString());
		if(eventTypeId != null){
			query.setParameter("eventTypeId", eventTypeId);
		}
		EventTypeModel eventTypeModel = (EventTypeModel) query.uniqueResult();
		return eventTypeModel;
	}
	
	@Override
	public EventModel getEventModel(Integer eventId) {


		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuilder hql = new StringBuilder("select em from EventModel em");
		if(eventId != null){
			hql.append(" where em.eventId=:eventId");
		}
		Query query = session.createQuery(hql.toString());
		if(eventId != null){
			query.setParameter("eventId", eventId);
		}
		EventModel eventModel = (EventModel) query.uniqueResult();
		return eventModel;
	}

	@Override
	public void addOrder(OrderModel orderModel) {
		
		Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(orderModel);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        
	}

    @Override
    public void registerEvent(int eventid) {
        try{
            Integer userId = SessionBean.getUserId();
            Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createSQLQuery("insert into eventregistered(userId, eventId) values (?, ?)");
                    query.setParameter(0, userId);
                    query.setParameter(1, 142);
                    query.executeUpdate(); 
                    session.getTransaction().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
}
