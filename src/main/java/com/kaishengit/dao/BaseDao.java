package com.kaishengit.dao;

import com.kaishengit.pojo.Patient;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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

    public List<T> findPatientByPage(Map<String,Object> params) {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setFirstResult(Integer.valueOf(params.get("start").toString()));
        criteria.setMaxResults(Integer.valueOf(params.get("length").toString()));

        Disjunction disjunction = getDisjunction(params);

        if(disjunction != null){
            criteria.add(disjunction);
        }

        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }

    protected Disjunction getDisjunction(Map<String, Object> params) {
        String patientname = params.get("patientname").toString();
        String idcard = params.get("idcard").toString();
        String tel = params.get("tel").toString();

        Disjunction disjunction = Restrictions.disjunction();
        if(StringUtils.isNotEmpty(patientname)){
            disjunction.add(Restrictions.like("patientname",patientname, MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotEmpty(idcard)){
            disjunction.add(Restrictions.like("idcard",idcard,MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotEmpty(tel)){
            disjunction.add(Restrictions.like("tel",tel,MatchMode.ANYWHERE));
        }
        return disjunction;
    }

    public Long countAll() {
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public T findById(Integer id) {
        return (T) getSession().get(entityClass,id);
    }

    public void delById(Integer id) {
        T t = findById(id);
        getSession().delete(t);
    }
}
