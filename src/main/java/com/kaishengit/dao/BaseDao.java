package com.kaishengit.dao;

import com.google.common.collect.Maps;
import com.kaishengit.pojo.Patient;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.ResultTransformer;

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

    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);
    }

    /**
     * DataTable 数据获取值
     * @param params 包含Map<> params
     *               params中含有有start和length(选择包含或不包含)及其他搜索属性值
     *               key为搜索属性名，value为搜索属性值
     * @return
     */
    public Map<String,Object> findPatientByPage(Map<String,Object> params) {
        Map<String,Object> resultMap = Maps.newHashMap();

        Criteria criteria = getSession().createCriteria(entityClass);

        Object start = null;
        Object length = null;
        if(params.containsKey("start") && params.containsKey("length")){
            start = params.get("start");
            length = params.get("length");
            params.remove("start");
            params.remove("length");
        }

        Disjunction disjunction = getDisjunction(params);
        if(disjunction != null){
            criteria.add(disjunction);
        }

        Long countByParam = countAllByparams(criteria);

        if(start != null && length != null){
            criteria.setFirstResult(Integer.valueOf(start.toString()));
            criteria.setMaxResults(Integer.valueOf(length.toString()));
        }

        criteria.addOrder(Order.desc("id"));
        List<T> tList = criteria.list();

        resultMap.put("tList",tList);
        resultMap.put("countByParam",countByParam);

        return resultMap;
    }

    /**
     * datatable构建Junction
     * @param params
     * @return
     */
    protected Disjunction getDisjunction(Map<String, Object> params) {
        if(params != null){
            Disjunction disjunction = Restrictions.disjunction();
            for(Map.Entry entry : params.entrySet()){
                if(entry.getValue() != null && StringUtils.isNotEmpty(entry.getValue().toString())){
                    disjunction.add(Restrictions.like(entry.getKey().toString(),entry.getValue().toString(), MatchMode.ANYWHERE));
                }
            }
            if(disjunction.conditions().iterator().hasNext()){
                return disjunction;
            }
            return null;
        }
        return null;
    }

    /**
     * DataTable 数据获取搜索数量
     * @return
     */
    public Long countAllByparams(Criteria criteria) {
        ResultTransformer resultTransformer = criteria.ROOT_ENTITY;

        criteria.setProjection(Projections.rowCount());
        Long countAllByParams = (Long) criteria.uniqueResult();
        criteria.setProjection(null);

        criteria.setResultTransformer(resultTransformer);
        return countAllByParams;
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

    public T findByParam(Map<String, Object> param) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for(Map.Entry entry : param.entrySet()){
            criteria.add(Restrictions.eq(entry.getKey().toString(),entry.getValue()));
        }
        return (T) criteria.uniqueResult();
    }
}
