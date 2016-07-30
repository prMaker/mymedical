package com.kaishengit.dao;

import com.kaishengit.pojo.Patient;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 */
public class BaseDao<T,PK> {

    @Inject
    private SessionFactory sessionFactory;
    private Class<?> entityClass;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public BaseDao(){
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types = parameterizedType.getActualTypeArguments();
        entityClass = (Class<?>) types[0];
    }

    public List<T> findAll() {
        Criteria criteria = getSession().createCriteria(entityClass);
        return criteria.list();
    }

    public void saveOrUpdate(Patient patient) {
        getSession().saveOrUpdate(patient);
    }
}
