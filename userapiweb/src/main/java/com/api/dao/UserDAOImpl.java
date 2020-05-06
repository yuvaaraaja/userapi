package com.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.api.model.User;

public class UserDAOImpl implements UserDAO {
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	public void save(User u) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(u);
		tx.commit();
		session.close();
	}
	
	public void update(User u) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(u);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<User> listAll() {
		Session session = this.sessionFactory.openSession();
		Criteria cr = session.createCriteria(User.class)
			    .setProjection(Projections.projectionList()
					      .add(Projections.property("user_id"), "user_id")
					      .add(Projections.property("firstname"), "firstname")
					      .add(Projections.property("lastname"), "lastname"))
			    .setResultTransformer(Transformers.aliasToBean(User.class));
		List<User> personList = cr.list();
		session.close();
		return personList;
	}
	
	public User listByID(String user_id) {
		Session session = this.sessionFactory.openSession();
		Criteria cr = session.createCriteria(User.class)
			    .setProjection(Projections.projectionList()
			      .add(Projections.property("user_id"), "user_id")
			      .add(Projections.property("firstname"), "firstname")
			      .add(Projections.property("lastname"), "lastname"))
			      .add(Restrictions.eq("user_id",user_id))
			    .setResultTransformer(Transformers.aliasToBean(User.class));
	    return (User) cr.uniqueResult();
	}

	public User listByIDWithPassword(String user_id) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from User where user_id = :userid ");
	    query.setParameter("userid", user_id);
	    return (User) query.uniqueResult();
	}

	
	public void delete(String user_id) {
		Session session = this.sessionFactory.openSession();
		User u=this.listByID(user_id);
		Transaction tx = session.beginTransaction();
		session.delete(u);
		tx.commit();
		System.out.println("Deleted successfully");
		session.close();
		
	}

}
