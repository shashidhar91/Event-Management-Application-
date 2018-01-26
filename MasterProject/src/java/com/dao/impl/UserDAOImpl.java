package com.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.interfaces.UserDAO;
import com.model.UserLoginModel;
import com.model.UserModel;
import com.util.HibernateUtil;

public class UserDAOImpl implements UserDAO{

	@Override
	public void addUserModel(UserModel userModel) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction  transaction =  session.beginTransaction();
		try{
			session.save(userModel);
			transaction.commit();
		} catch(Exception e){
			transaction.rollback();
			session.close();
		} finally {
			session.close();
		}
	}

	@Override
	public void updateUserModel(UserModel userModel) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction  transaction =  session.beginTransaction();
		try{
			session.update(userModel);
			transaction.commit();
		} catch(Exception e){
			transaction.rollback();
			session.close();
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteUserModel(UserModel userModel) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction  transaction =  session.beginTransaction();
		try{
			session.delete(userModel);
			transaction.commit();
		} catch(Exception e){
			transaction.rollback();
			session.close();
		} finally {
			session.close();
		}
	}

	@Override
	public UserModel getUserModel(Integer userId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuilder hql = new StringBuilder("select um from UserModel um");
		if(userId != null){
			hql.append(" where um.userId=:userId");
		}
		Query query = session.createQuery(hql.toString());
		if(userId!=null){
			query.setParameter("userId", userId);
		}
		UserModel userModel = (UserModel) query.uniqueResult();
		return userModel;
	}

	@Override
	public UserLoginModel getActiveUsers(String email, String password) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuilder hql = new StringBuilder("select distinct ulm from UserLoginModel ulm ");
		
		if(email != null && !"".equals(email)) {
			hql.append("where ulm.email=:email ");
		}
		if(password != null && !"".equals(password)) {
			hql.append("and ulm.password=:password ");
		}
		Query query = session.createQuery(hql.toString());
		
		if(email != null && !"".equals(email)) {
			query.setParameter("email", email);
		}
		if(password != null && !"".equals(password)) {
			query.setParameter("password", password);
		}
		UserLoginModel userLoginModel = (UserLoginModel) query.uniqueResult();

		return userLoginModel;
	}

	@Override
	public UserModel getAllPropertiesOfUserByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserModel> getAllUserByProperty(String name, Integer role) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		StringBuilder hql = new StringBuilder("select distinct um from UserModel um ");
		
		if(name != null && !name.equals("")) {
			hql.append(" where um.name like :name");
		}
//		if(role != null && role!=0) {
//			hql.append(" and rm.roleId = :role");
//		}
		Query query = session.createQuery(hql.toString());
		
		if(name != null && !name.equals("")) {
			query.setParameter("name", "%"+name+"%");
		}
//		if(role != null && role!=0) {
//			query.setParameter("role", role);
//		}
		List<UserModel> userModels = query.list();
		
		if (userModels.size() > 0) {
			return userModels;
		} else
			return null;
	}

	@Override
	public List<UserModel> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel getUserByEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel getUserDetailsByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserModel> getAllUserByLocation(Integer location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUserLoginModel(UserLoginModel userLoginModel) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction  transaction =  session.beginTransaction();
		try{
			session.save(userLoginModel);
			transaction.commit();
		} catch(Exception e){
			transaction.rollback();
			session.close();
		} finally {
			session.close();
		}
		
	}

}
